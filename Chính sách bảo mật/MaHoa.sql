create type resultRec is object(MaSV CHAR(5),MaLop CHAR(5), Diem varchar2(200)); --tạo kiểu record
create type myTable is table of resultRec; --tạo kiểu bảng mới
/
show errors

--hàm mã hóa
CREATE OR REPLACE FUNCTION ENCRYPT(plainText in varchar2, keyRaw in raw )
RETURN raw
IS
      encryption_type    PLS_INTEGER :=  DBMS_CRYPTO.ENCRYPT_AES256 + DBMS_CRYPTO.CHAIN_CBC + DBMS_CRYPTO.PAD_PKCS5;
      iv_raw             RAW (16);
      cipherText raw(2000);
BEGIN
   iv_raw        := DBMS_CRYPTO.RANDOMBYTES (16);
   cipherText := DBMS_CRYPTO.ENCRYPT
      (
         src => UTL_I18N.STRING_TO_RAW (plainText,  'AL32UTF8'),
         typ => encryption_type,
         key => keyRaw,
         iv  => iv_raw
      );
    cipherText := UTL_RAW.CONCAT(iv_raw, cipherText);
    RETURN cipherText;
END ENCRYPT;
/
show errors

--hàm giải mã
CREATE OR REPLACE FUNCTION DECRYPT(cipherText in raw, keyRaw in raw)
RETURN varchar2
IS
     encryption_type    PLS_INTEGER :=  DBMS_CRYPTO.ENCRYPT_AES256 + DBMS_CRYPTO.CHAIN_CBC + DBMS_CRYPTO.PAD_PKCS5;
    plainText varchar2(200);
     decrypted_raw raw(2000);
     iv_raw             RAW (16);
     cipher             RAW(2000);
BEGIN
    IF (cipherText is not null) THEN
       iv_raw := UTL_RAW.SUBSTR(cipherText, 0,  16);
       cipher:= UTL_RAW.SUBSTR(cipherText, 17,  UTL_RAW.LENGTH( cipherText) - 16);
       decrypted_raw := DBMS_CRYPTO.DECRYPT
      (
         src => cipher,
         typ => encryption_type,
         key => keyRaw,
         iv  => iv_raw
      );
      plainText  := UTL_I18N.RAW_TO_CHAR (decrypted_raw, 'AL32UTF8');
    ELSE
      plainText   := null;
    END IF;
  
  RETURN  plainText;
END;
/
show errors

--hàm kiểm tra sinh viên có thuộc lớp mà giáo viên này dạy hay không
CREATE OR REPLACE FUNCTION laSV(mssv char, lop char)
RETURN BOOLEAN
AS
  res boolean;
  countt integer;
  userName char(5);
BEGIN
  res := false;
  SELECT SYS_CONTEXT('userenv', 'SESSION_USER') INTO userName FROM DUAL;
  SELECT COUNT(*) INTO countt FROM LICHDAY LD, SINHVIEN_LOP SL WHERE LD.MaLop = SL.MaLop AND SL.MaSV = mssv AND LD.MaGV = userName;
  IF (countt > 0) THEN
    res := true;
  ELSE
    res := false;
  END IF;
  return res;
END;
  
--hàm nhập điểm
CREATE OR REPLACE PROCEDURE nhapDiem(mssv char, lop char, diemNhap int)
AS
  keyModified raw(2000);
  uName char(5);
  countt integer;
  subject char(5);
BEGIN
    IF (QTV.laSV(mssv, lop) = true) THEN
      select KHOA into keyModified from KHOA_NGUOIDUNG where MAND = mssv;
      update QTV.SINHVIEN_LOP set Diem = QTV.ENCRYPT(to_char(diemNhap), UTL_RAW.BIT_XOR(keyModified, UTL_I18N.STRING_TO_RAW(mssv))) where MASV = mssv and MALOP = lop;
      SELECT MonHoc INTO subject FROM QTV.LOP WHERE MaLop = lop;
      SELECT COUNT(MaSV) INTO countt FROM QTV.SINHVIEN_MONHOC WHERE MaSV = mssv AND MaMon = subject;
      IF (diemNhap < 5) THEN
        IF (countt > 0) THEN
            UPDATE QTV.SINHVIEN_MONHOC SET TinhTrang = 'Rớt' WHERE MaMon = subject AND MaSV = mssv;
          ELSE
            INSERT INTO QTV.SINHVIEN_MONHOC VALUES(mssv, subject, 'Rớt');
        END IF;
      ELSE
         IF (countt > 0) THEN
            UPDATE QTV.SINHVIEN_MONHOC SET TinhTrang = 'Đậu' WHERE MaMon = subject AND MaSV = mssv;
          ELSE
            INSERT INTO QTV.SINHVIEN_MONHOC VALUES(mssv, subject, 'Đậu');
        END IF;
      END IF;  
    END IF;
END;
/
show errors


--hàm xem danh sách sinh viên với điểm được mã hóa
CREATE OR REPLACE FUNCTION xemDSSinhVien
RETURN myTable
IS
  res myTable := myTable();
  userName VARCHAR2(6);
  title varchar2(20);
BEGIN
  
  SELECT SYS_CONTEXT('userenv', 'SESSION_USER') INTO userName FROM DUAL;
  --SELECT resultRec(MaSV, MaLop, QTV.DECRYPT(Diem, UTL_RAW.BIT_XOR(Khoa, UTL_I18N.STRING_TO_RAW(MaND)) )) BULK COLLECT INTO res FROM QTV.SINHVIEN_LOP SL, QTV.KHOA_NGUOIDUNG KN WHERE SL.MaSV = KN.MaND;
  IF (userName != 'QTV') then
      SELECT DISTINCT GRANTED_ROLE INTO title FROM DBA_ROLE_PRIVS WHERE GRANTEE = userName;
      IF (title = 'GIAOVU') THEN
        SELECT resultRec(MaSV, MaLop, QTV.DECRYPT(Diem, UTL_RAW.BIT_XOR(Khoa, UTL_I18N.STRING_TO_RAW(MaND)) )) BULK COLLECT INTO res FROM QTV.SINHVIEN_LOP SL, QTV.KHOA_NGUOIDUNG KN WHERE SL.MaSV = KN.MaND;
      ELSIF ((title = 'GIAOVIEN') OR (title = 'TRUONGPHOKHOA') OR (title = 'TRUONGNGANH')) THEN
        SELECT resultRec(MaSV, MaLop, QTV.DECRYPT(Diem, UTL_RAW.BIT_XOR(Khoa, UTL_I18N.STRING_TO_RAW(MaND)) )) BULK COLLECT INTO res FROM QTV.SINHVIEN_LOP SL, QTV.KHOA_NGUOIDUNG KN WHERE SL.MaSV = KN.MaND AND SL.MaLop IN (SELECT MaLop FROM QTV.LICHDAY LD WHERE LD.MaGV = userName);
      ELSIF (title = 'SINHVIEN') THEN
        FOR rec IN (select MaSV, MaLop, Diem, Khoa FROM QTV.SINHVIEN_LOP SL LEFT OUTER JOIN (SELECT * FROM QTV.KHOA_NGUOIDUNG WHERE MaND = userName ) KN ON SL.MaSV = KN.MaND WHERE SL.MaLop IN (SELECT MaLop FROM QTV.SINHVIEN_LOP SL1 WHERE SL1.MaSV = userName))
        LOOP
          IF (rec.Khoa is null) THEN
           res.extend;
           res(res.last) := resultRec(rec.MaSV, rec.MaLop, rec.Diem);
          ELSE
            res.extend; 
            IF (rec.Diem is null) THEN
              res(res.last) := resultRec(rec.MaSV, rec.MaLop, rec.Diem);
            ELSE
              res(res.last) := resultRec(rec.MaSV, rec.MaLop, QTV.DECRYPT(rec.Diem, UTL_RAW.BIT_XOR(rec.Khoa, UTL_I18N.STRING_TO_RAW(rec.MaSV)) ));
            END IF;
          END IF;
        END LOOP;       
      END if;
  END IF;
  --lấy từng record vào bảng
    return res;
END;
/
show errors

select * from sinhvien_lop where MaSV = '11003'

SELECT DISTINCT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'GV003';

--xem kỹ lại lý thuyết table function để ra thi cô hỏi
select * from table(QTV.xemDSSinhVien);

--cấp quyền chạy function xemDSSinhVien
grant execute on xemDSSinhVien to SinhVien;
GRANT EXECUTE ON nhapDiem TO GiaoVien;
GRANT EXECUTE ON xemDSSinhVien TO GiaoVien;
GRANT EXECUTE ON xemDSSinhVien TO GiaoVu;

--tạo khóa người dùng
CREATE OR REPLACE PROCEDURE TaoKhoa (maND char)
AS
    keyRaw raw(32);
BEGIN
    keyRaw := DBMS_CRYPTO.RANDOMBYTES (32);
    INSERT INTO KHOA_NGUOIDUNG VALUES(maND, UTL_RAW.BIT_XOR(keyRaw, UTL_I18N.STRING_TO_RAW(maND)));
END;
/
show errors

select * from nguoidung
select * from lichday where MaGV = 'GV003'
select * from sinhvien_lop
select * from khoa_nguoidung
select * from nguoidung ND, lichday LD where ND.MaND = LD.MaGV AND LD.MaLop = SL.MaLop AND SL.MaSV = '11001'

SET SERVEROUTPUT ON

exec TaoKhoa('11001');
exec TaoKhoa('11002');
exec TaoKhoa('11003');
exec TaoKhoa('11004');
exec TaoKhoa('11005');
exec TaoKhoa('11006');
exec TaoKhoa('11007');
exec TaoKhoa('11008');
exec TaoKhoa('11009');
exec TaoKhoa('11010');
exec TaoKhoa('11011');
exec TaoKhoa('11012');
exec TaoKhoa('11013');
exec TaoKhoa('11014');
exec TaoKhoa('11015');
exec TaoKhoa('11016');
exec TaoKhoa('11017');
exec TaoKhoa('11018');
exec TaoKhoa('11019');
exec TaoKhoa('11020');
exec TaoKhoa('11021');
exec TaoKhoa('11022');
exec TaoKhoa('11023');
exec TaoKhoa('11024');
exec TaoKhoa('11025');
exec TaoKhoa('11026');
exec TaoKhoa('11027');
exec QTV.NhapDiem('11001', 'LOP01', 9);
exec QTV.nhapDiem('11002', 'LOP01', 8);
exec QTV.nhapDiem('11003', 'LOP01', 4);
exec QTV.nhapDiem('11001', 'LOP02', 7);
exec QTV.nhapDiem('11002', 'LOP02', 6);
exec QTV.nhapDiem('11003', 'LOP02', 9);
exec QTV.nhapDiem('11001', 'LOP03', 8);
exec QTV.nhapDiem('11002', 'LOP03', 7.5);
exec QTV.nhapDiem('11003', 'LOP03', 8.5);

select * from lichday

--select * from KHOA_NGUOIDUNG
--select * from sinhvien_lop;
--select * from sinhvien_monhoc;

