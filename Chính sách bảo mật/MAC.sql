ALTER USER lbacsys IDENTIFIED BY lbacsys ACCOUNT UNLOCK;
CONN lbacsys/lbacsys

EXEC LBACSYS.CONFIGURE_OLS;
EXEC LBACSYS.OLS_ENFORCEMENT.ENABLE_OLS;

--quyền tạo các thành phần của table
GRANT EXECUTE ON sa_components TO QTV WITH GRANT OPTION;
--quyền gán label cho tài khoản
GRANT EXECUTE ON sa_user_admin TO QTV WITH GRANT OPTION;
--quyền tạo các label
GRANT EXECUTE ON sa_label_admin TO QTV WITH GRANT OPTION;
--quyền gán policy cho các bảng
GRANT EXECUTE ON sa_policy_admin TO QTV WITH GRANT OPTION;
--chuyển chuỗi thành số của label
GRANT EXECUTE ON CHAR_TO_LABEL TO QTV WITH GRANT OPTION;

GRANT LBAC_DBA TO QTV;
--gán quyền thực thi các hàm của sa_sysdbs (tạo policy)
GRANT EXECUTE ON sa_sysdba TO QTV;
GRANT EXECUTE ON to_lbac_data_label TO QTV;

--MAC các dự án hợp tác tổ chức các cuộc thi học thuật (cấp trường, cấp quốc gia, quốc tế)
BEGIN
  SA_SYSDBA.CREATE_POLICY (
    policy_name => 'DuAnHocThuat_policy',
    column_name => 'DuAnHocThuat_label'
  );
END;
/

--phải gán role DuAnHocThua_policy_DBA cho QTV thì user này mới có thể quản lý policy này
GRANT DuAnHocThuat_policy_DBA TO QTV

EXECUTE SA_COMPONENTS.CREATE_LEVEL('DuAnHocThuat_policy',20,'N','Nhỏ');
EXECUTE SA_COMPONENTS.CREATE_LEVEL('DuAnHocThuat_policy',40,'V','Vừa');
EXECUTE SA_COMPONENTS.CREATE_LEVEL('DuAnHocThuat_policy',60,'L','Lớn');

EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('DuAnHocThuat_policy',100,'NG01','Hệ thống thông tin');
EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('DuAnHocThuat_policy',120,'NG02','Công nghệ phần mềm');
EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('DuAnHocThuat_policy',130,'NG03','Khoa học máy tính');
EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('DuAnHocThuat_policy',140,'NG04','Di truyền');
EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('DuAnHocThuat_policy',150,'NG05','Hóa sinh');
EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('DuAnHocThuat_policy',160,'NG06','Vi sinh');
EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('DuAnHocThuat_policy',170,'NG07','Hóa dược');
EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('DuAnHocThuat_policy',180,'NG08','Hóa phân tích');
EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('DuAnHocThuat_policy',190,'NG09','Hóa học Polyme');

EXECUTE SA_COMPONENTS.CREATE_GROUP('DuAnHocThuat_policy',1,'CN01','Chi nhánh 1');
EXECUTE SA_COMPONENTS.CREATE_GROUP('DuAnHocThuat_policy',2,'CN02','Chi nhánh 2');

EXECUTE SA_USER_ADMIN.SET_USER_PRIVS('DuAnHocThuat_policy','QTV','FULL,PROFILE_ACCESS');

--hàm trả về nhãn
CREATE OR REPLACE FUNCTION get_program_label 
(
  scopes IN NVARCHAR2,
  major IN CHAR,
  branch IN CHAR
)
RETURN LBACSYS.LBAC_LABEL
AS 
  v_label VARCHAR2(100);
BEGIN
  --gán level cho label
  IF (scopes = 'Quốc tế') THEN
    v_label := 'L:';
  ELSIF (scopes = 'Quốc gia') THEN
    v_label := 'V:';
  ELSIF (scopes = 'Trường') THEN
    v_label := 'N:';
  END IF;
  
  --gán compartment
  v_label := v_label || major || ':'; 
  
  --gán group
  v_label := v_label || branch; 
  
  RETURN TO_LBAC_DATA_LABEL('DuAnHocThuat_policy',v_label);
END;
/
show errors

--phải gán quyền cho lbacsys thực thi hàm tạo nhãn
grant execute on get_program_label to lbacsys


--áp dụng policy vào bảng để thêm vào cột label
--phải có no_control nếu không sẽ trả về null
BEGIN
  SA_POLICY_ADMIN.APPLY_TABLE_POLICY(
    policy_name   => 'DuAnHocThuat_policy',
    schema_name   => 'QTV',
    table_name    => 'DUANHOCTHUAT',
    table_options => 'NO_CONTROL');
END;
/

--cập nhật label number cho bảng (phải có mới chạy được)
UPDATE DuAnHocThuat SET DuAnHocThuat_label = CHAR_TO_LABEL('DuAnHocThuat_policy','N');

--apply policy vào bảng 1 lần nữa
BEGIN
  SA_POLICY_ADMIN.REMOVE_TABLE_POLICY('DuAnHocThuat_policy','QTV','DUANHOCTHUAT');
  SA_POLICY_ADMIN.APPLY_TABLE_POLICY (
    policy_name => 'DuAnHocThuat_policy',
    schema_name => 'QTV',
    table_name  => 'DUANHOCTHUAT',
    table_options => 'READ_CONTROL,WRITE_CONTROL,CHECK_CONTROL',
    label_function => 'QTV.get_program_label(:new.khuvuc,:new.nganh,:new.chinhanh)',
    predicate => NULL);
END;
/
show errors;

--cập nhật lại nhãn trên dòng
update DuAnHocThuat SET MaDuAn = MaDuAn;

--cấp quyền select Trưởng phó khoa, giáo viên
GRANT SELECT, INSERT ON DuAnHocThuat TO TruongPhoKhoa;
GRANT SELECT, INSERT ON DuAnHocThuat TO TruongNganh;
GRANT SELECT, INSERT ON DuAnHocThuat TO GiaoVien;

BEGIN
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV001','L:NG01,NG02,NG03:CN01'); --trưởng phó khoa CNTT
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV002','V:NG01:CN01'); -- trưởng ngành HTTT
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV003','N:NG01:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV005','V:NG02:CN01'); -- trưởng ngành CNPM
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV004','N:NG02:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV006','N:NG02:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV008','V:NG03:CN01'); -- trưởng ngành KHMT
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV007','N:NG03:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV009','N:NG03:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV010','L:NG04,NG05,NG06:CN01'); --trưởng phó khoa CNSH
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV011','V:NG04:CN01'); -- trưởng ngành di truyền
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV012','N:NG04:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV014','V:NG05:CN01'); -- trưởng ngành hóa sinh
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV013','N:NG05:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV015','N:NG05:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV017','V:NG06:CN01'); -- trưởng ngành vi sinh
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV016','N:NG06:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV018','N:NG06:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV019','L:NG07,NG08,NG09:CN01'); --trưởng phó khoa hóa học
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV020','V:NG07:CN01'); -- trưởng ngành hóa dược
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV021','N:NG07:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV023','V:NG08:CN01'); -- trưởng ngành hóa phân tích
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV022','N:NG08:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV024','N:NG08:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV026','V:NG09:CN01'); -- trưởng ngành hóa học Polyme
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV025','N:NG09:CN01');
  SA_USER_ADMIN.SET_USER_LABELS('DuAnHocThuat_policy','GV027','N:NG09:CN01');
END;
/

select * from QTV.DuAnHocThuat;
delete from QTV.DuAnHocThuat where MaDuAn = 'DA016';
insert into QTV.DuAnHocThuat(maduan, tenduan, khuvuc, nganh, chinhanh) values ('DA016', 'Be A Professional', 'Trường', 'NG02', 'CN01');