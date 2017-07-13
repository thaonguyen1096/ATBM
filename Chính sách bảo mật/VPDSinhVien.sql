--0) Người dùng chỉ được xem và chỉnh sửa thông tin của chính họ, giáo viên được xem thông tin của mình và sinh viên nhưng chỉ được chỉnh sửa thông tin cá nhân của mình, thông tin của sinh viên chỉ được sửa mã sinh viên, ngành học
GRANT SELECT ON NGUOIDUNG TO SinhVien, GiaoVien, GiaoVu;
GRANT UPDATE(NgaySinh, DiaChi, SoDT) ON NGUOIDUNG TO SinhVien;
GRANT UPDATE(NgaySinh, DiaChi, SoDT) ON NGUOIDUNG TO GiaoVien;
GRANT UPDATE(NgaySinh, DiaChi, SoDT) ON NGUOIDUNG TO GiaoVu;

CREATE VIEW SINHVIEN AS SELECT * FROM NGUOIDUNG WHERE LOAINGUOIDUNG = 2;
GRANT UPDATE(MaND, Nganh) ON SINHVIEN TO GiaoVu;
GRANT SELECT ON SINHVIEN TO GiaoVu;

CREATE OR REPLACE FUNCTION auth_nguoidung( 
  schema_var IN VARCHAR2,
  table_var  IN VARCHAR2
 )
 RETURN VARCHAR2
 IS
  return_val VARCHAR2 (500);
  userName VARCHAR2(6);
  title varchar2(20);
 BEGIN
  SELECT SYS_CONTEXT('userenv', 'SESSION_USER') INTO userName FROM DUAL;
  
  IF (userName != 'QTV') then
      SELECT DISTINCT GRANTED_ROLE INTO title FROM DBA_ROLE_PRIVS WHERE GRANTEE = userName;
      return_val := 'MAND = '''  || userName || ''' ';
      RETURN return_val;
  END IF;
    RETURN return_val;
 END auth_nguoidung;
/
show errors
 
  --tạo policy
 BEGIN
  DBMS_RLS.ADD_POLICY (
    object_schema    => 'QTV',
    object_name      => 'NGUOIDUNG',
    policy_name      => 'nguoidung_policy',
    function_schema  => 'QTV',
    policy_function  => 'auth_nguoidung',
    statement_types  => 'select, update'
   );
 END;
/
show errors

--1) Sinh viên chỉ được phép xem các môn học của chỉ khoa mình trong một học kỳ và năm học cụ thể
GRANT SELECT ON LOP TO SinhVien;
--tạo policy function
 CREATE OR REPLACE FUNCTION auth_lop_select( 
  schema_var IN VARCHAR2,
  table_var  IN VARCHAR2
 )
 RETURN VARCHAR2
 IS
  return_val VARCHAR2 (500);
  userName VARCHAR2(6);
  title varchar2(20);
  countt integer;
 BEGIN
  SELECT SYS_CONTEXT('userenv', 'SESSION_USER') INTO userName FROM DUAL;
  IF (userName != 'QTV') THEN
    SELECT DISTINCT GRANTED_ROLE INTO title FROM DBA_ROLE_PRIVS WHERE GRANTEE = userName;
    IF (title = 'SINHVIEN') THEN
        return_val := 'Khoa IN (SELECT Khoa FROM NGANH WHERE MaNganh  =  (SELECT Nganh FROM NGUOIDUNG WHERE MaND  =  ''' || userName || '''))';
        DBMS_OUTPUT.PUT_LINE(return_val);
        RETURN return_val;
    END IF;
  END IF;
   RETURN return_val;
 END auth_lop_select;
/
show errors
 --tạo policy
 BEGIN
  DBMS_RLS.ADD_POLICY (
    object_schema    => 'QTV',
    object_name      => 'LOP',
    policy_name      => 'lop_policy',
    function_schema  => 'QTV',
    policy_function  => 'auth_lop_select',
    statement_types  => 'select'
   );
 END;
/
--exec DBMS_RLS.DROP_POLICY ('QTV', 'LOP', 'lop_policy');  

--3) Xóa, sửa thông tin đăng ký môn học của mình trong một thời hạn nhất định
GRANT SELECT, UPDATE ON NGUOIDUNG TO SinhVien;
GRANT SELECT, DELETE ON SINHVIEN_LOP TO SinhVien;

--tạo policy function
 CREATE OR REPLACE FUNCTION auth_dsLop_update( 
  schema_var IN VARCHAR2,
  table_var  IN VARCHAR2
 )
 RETURN VARCHAR2
 IS
  return_val VARCHAR2 (500);
  userName VARCHAR2(6);
  title varchar2(20);
  countt integer;
 BEGIN
  SELECT SYS_CONTEXT('userenv', 'SESSION_USER') INTO userName FROM DUAL;
  IF (userName != 'QTV') THEN
    SELECT DISTINCT GRANTED_ROLE INTO title FROM DBA_ROLE_PRIVS WHERE GRANTEE = userName;
    IF (title = 'SINHVIEN') THEN
        return_val := 'MaSV = ''' || userName || ''' ';
        DBMS_OUTPUT.PUT_LINE(return_val);
        RETURN return_val;
    END IF;
  END IF;
   RETURN return_val;
 END auth_dsLop_update;
/
show errors
 --tạo policy
 BEGIN
  DBMS_RLS.ADD_POLICY (
    object_schema    => 'QTV',
    object_name      => 'SINHVIEN_LOP',
    policy_name      => 'sinhvien_lop_policy_update',
    function_schema  => 'QTV',
    policy_function  => 'auth_dsLop_update',
    statement_types  => 'select, delete'
   );
 END;
/

--select * from QTV.sinhvien_lop


--4) Chỉ được đăng ký môn mà mình chưa đậu
GRANT INSERT ON SINHVIEN_LOP TO SinhVien;

CREATE OR REPLACE FUNCTION auth_dsLop_insert( 
  schema_var IN VARCHAR2,
  table_var  IN VARCHAR2
 )
 RETURN VARCHAR2
 IS
  return_val VARCHAR2 (500);
  userName VARCHAR2(6);
  title varchar2(20);
 BEGIN
  SELECT SYS_CONTEXT('userenv', 'SESSION_USER') INTO userName FROM DUAL;
  IF (userName != 'QTV') THEN
      SELECT DISTINCT GRANTED_ROLE INTO title FROM DBA_ROLE_PRIVS WHERE GRANTEE = userName;
      DBMS_OUTPUT.PUT_LINE(title);
      IF (title = 'SINHVIEN') THEN
          return_val := 'MaSV = ''' || userName || ''' AND NOT EXISTS (SELECT * FROM LOP L, SINHVIEN_MONHOC SM WHERE L.MaLop = SINHVIEN_LOP.MaLop AND L.MonHoc = SM.MaMon AND SINHVIEN_LOP.MaSV = SM.MaSV AND SM.TinhTrang = ''' || 'Đậu' || ''')';
    
          DBMS_OUTPUT.PUT_LINE(return_val);
          RETURN return_val;
      END IF;
  END IF;
   RETURN return_val;
 END auth_dsLop_insert;
/
show errors
--tạo policy
 BEGIN
  DBMS_RLS.ADD_POLICY (
    object_schema    => 'QTV',
    object_name      => 'SINHVIEN_LOP',
    policy_name      => 'sinhvien_lop_policy',
    function_schema  => 'QTV',
    policy_function  => 'auth_dsLop_insert',
    statement_types  => 'INSERT',
    update_check => TRUE
   );
 END;
--exec DBMS_RLS.DROP_POLICY ('QTV', 'SINHVIEN_LOP', 'sinhvien_lop_policy');  

delete from sinhvien_lop where malop ='LOP29'

select * from sinhvien_lop

--insert into QTV.sinhvien_lop values('11001', 'LOP29', null);
--phần test
--select * from QTV.sinhvien_lop
--SET SERVEROUTPUT ON
--select * from QTV.lop;
--SELECT * FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'QTV';
--select * from QTV.sinhvien_lop SL1 where SL1.masv = '11001' and NOT EXISTS (SELECT * FROM LOP L1, LOP L2, SINHVIEN_LOP SL WHERE L1.MaLop = SL1.MaLop AND L1.Khoa = L2.Khoa AND L2.MaLop = SL.MaLop AND Diem < 5 and SL.MaSV = '11001');