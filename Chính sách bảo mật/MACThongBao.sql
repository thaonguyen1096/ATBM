--MAC các thông báo
--khi QTV tạo chính sách thì quyền thực thi mặc định được giao cho lbacsys
BEGIN
  SA_SYSDBA.CREATE_POLICY (
    policy_name => 'ThongBao_policy',
    column_name => 'ThongBao_label'
  );
END;
/

--phải gán role ThongBao_policy_DBA cho QTV thì user này mới có thể quản lý policy này (dùng lbacsys gán)
GRANT ThongBao_policy_DBA TO QTV;


--tạo các thành phần của nhãn
--mức độ bí mật, vừa là cho trưởng ngành, thấp là tất cả các giáo viên của khoa, cao là cho các trưởng phó khoa
EXECUTE SA_COMPONENTS.CREATE_LEVEL('ThongBao_policy',60,'C','Cao');  --trưởng phó khoa nào đó thừa lệnh hiệu trưởng báo lại các trưởng phó khoa còn lại
EXECUTE SA_COMPONENTS.CREATE_LEVEL('ThongBao_policy',40,'V','Vừa');
EXECUTE SA_COMPONENTS.CREATE_LEVEL('ThongBao_policy',20,'T','Thấp');

EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('ThongBao_policy',100,'KH01','Công nghệ thông tin');
EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('ThongBao_policy',120,'KH02','Công nghệ sinh học');
EXECUTE SA_COMPONENTS.CREATE_COMPARTMENT('ThongBao_policy',130,'KH03','Hóa học');

EXECUTE SA_COMPONENTS.CREATE_GROUP('ThongBao_policy',1,'CN01','Chi nhánh 1');
EXECUTE SA_COMPONENTS.CREATE_GROUP('ThongBao_policy',2,'CN02','Chi nhánh 2');

EXECUTE SA_USER_ADMIN.SET_USER_PRIVS('ThongBao_policy','QTV','FULL,PROFILE_ACCESS');

--tạo các label sẽ sử dụng
BEGIN
  --tất cả trưởng khoa
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'THONGBAO_POLICY', label_tag => 1, label_value => 'C');
  --tất cả giáo viên của khoa CNTT
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 100, label_value => 'T:KH01:CN01');
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 101, label_value => 'T:KH01:CN02');
  --tất cả giáo viên của khoa CNSH
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 110, label_value => 'T:KH02:CN01');
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 111, label_value => 'T:KH02:CN02');
  --tất cả giáo viên của khoa Hóa
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 120, label_value => 'T:KH03:CN01');
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 121, label_value => 'T:KH03:CN02');
  --tất cả trưởng ngành của khoa CNTT
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 200, label_value => 'V:KH01:CN01');
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 201, label_value => 'V:KH01:CN02');
  --tất cả trưởng ngành của khoa CNSH
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 210, label_value => 'V:KH02:CN01');
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 211, label_value => 'V:KH02:CN02');
  --tất cả trưởng ngành của khoa Hóa
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 220, label_value => 'V:KH03:CN01');
  SA_LABEL_ADMIN.CREATE_LABEL(policy_name => 'ThongBao_policy', label_tag => 221, label_value => 'V:KH03:CN02');
END;
/
show errors

--áp dụng policy vào bảng để thêm vào cột label
--phải có no_control nếu không sẽ trả về null
BEGIN
  SA_POLICY_ADMIN.APPLY_TABLE_POLICY(
    policy_name   => 'ThongBao_policy',
    schema_name   => 'QTV',
    table_name    => 'THONGBAO',
    table_options => 'NO_CONTROL');
END;
/


--cập nhật label number cho bảng (phải có mới chạy được)
--UPDATE ThongBao SET ThongBao_label = CHAR_TO_LABEL('ThongBao_policy','T');

--apply policy vào bảng 1 lần nữa
BEGIN
  SA_POLICY_ADMIN.REMOVE_TABLE_POLICY('ThongBao_policy','QTV','THONGBAO');
  SA_POLICY_ADMIN.APPLY_TABLE_POLICY (
    policy_name => 'ThongBao_policy',
    schema_name => 'QTV',
    table_name  => 'THONGBAO',
    table_options => 'READ_CONTROL,WRITE_CONTROL');
END;
/
show errors;

--gán nhãn cho giáo viên
BEGIN
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV001', 'C:KH01:CN01'); -- trưởng phó khoa CNTT
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV002', 'V:KH01:CN01'); --trưởng ngành HTTT
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV005', 'V:KH01:CN01'); --trưởng ngành CNPM
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV008', 'V:KH01:CN01'); --trưởng ngành KHMT
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV003', 'T:KH01:CN01'); -- GV CNTT
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV004', 'T:KH01:CN01'); -- GV CNTT
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV006', 'T:KH01:CN01'); -- GV CNTT
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV007', 'T:KH01:CN01'); -- GV CNTT
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV009', 'T:KH01:CN01'); -- GV CNTT
   
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV010', 'C:KH02:CN01'); -- trưởng phó khoa CNSH
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV011', 'V:KH02:CN01'); --trưởng ngành di truyền
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV014', 'V:KH02:CN01'); --trưởng ngành hóa sinh
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV017', 'V:KH02:CN01'); --trưởng ngành vi sinh
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV012', 'T:KH02:CN01'); -- GV CNSH
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV013', 'T:KH02:CN01'); -- GV CNSH
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV015', 'T:KH02:CN01'); -- GV CNSH
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV016', 'T:KH02:CN01'); -- GV CNSH
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV018', 'T:KH02:CN01'); -- GV CNSH
  
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV019', 'C:KH03:CN01'); --trưởng phó khoa Hóa học
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV020', 'V:KH03:CN01'); --trưởng ngành hóa dược
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV023', 'V:KH03:CN01'); --trưởng ngành hóa phân tích
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV026', 'V:KH03:CN01'); --trưởng ngành hóa polyme
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV021', 'T:KH03:CN01'); -- GV Hóa học
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV022', 'T:KH03:CN01'); -- GV Hóa học
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV024', 'T:KH03:CN01'); -- GV Hóa học
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV025', 'T:KH03:CN01'); -- GV Hóa học
  SA_USER_ADMIN.SET_USER_LABELS('ThongBao_policy', 'GV027', 'T:KH03:CN01'); -- GV Hóa học
END;

GRANT SELECT, INSERT, UPDATE ON THONGBAO TO TruongPhoKhoa;
GRANT SELECT, INSERT, UPDATE ON THONGBAO TO TruongNganh;
GRANT SELECT, INSERT, UPDATE ON THONGBAO TO GiaoVien;

GRANT EXECUTE ON CHAR_TO_LABEL TO TruongPhoKhoa;
GRANT EXECUTE ON CHAR_TO_LABEL TO TruongNganh;
GRANT EXECUTE ON CHAR_TO_LABEL TO GiaoVien;

select * from QTV.THONGBAO;

--GV001 chạy cái này
insert into QTV.THONGBAO values (1, 'Các khoa phải phấn đấu thi đua để trường ta được công nhận', CHAR_TO_LABEL('ThongBao_policy', 'C'));
insert into QTV.THONGBAO values (2, 'Chào mừng bạn Nguyễn Thị Chân Ái - thực tập sinh mới nhất của khoa chúng ta', CHAR_TO_LABEL('ThongBao_policy', 'T:KH01:CN01'));
