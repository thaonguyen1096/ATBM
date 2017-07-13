﻿CREATE TABLE LOP
(
	MALOP CHAR(5),
	HOCKY INT,
	NAM INT,
  GIAOVU CHAR(5),
  TPKHOA CHAR(5),
  SOSVTD INT,
  MONHOC CHAR(5),
  NGANH CHAR(4),
  KHOA CHAR(4),
  PRIMARY KEY(MALOP)
);

--nếu là giáo vụ thì ngành là null
CREATE TABLE NGUOIDUNG
(
	MAND CHAR(5),
	HOTEN NVARCHAR2(100),
	NGAYSINH DATE,
	DIACHI NVARCHAR2(100),
	SODT VARCHAR2(15),
	NGANH CHAR(4),
	LOAINGUOIDUNG INT,
	PRIMARY KEY(MAND)
);

CREATE TABLE SINHVIEN_LOP
(
	MASV CHAR(5),
	MALOP CHAR(5),
	DIEM RAW(2000),
	PRIMARY KEY(MASV, MALOP)
);


CREATE TABLE LICHDAY
(
	MAGV CHAR(5),
  MALOP CHAR(5),
  THU INT CHECK (THU <= 6 AND THU >= 0),
  TIET VARCHAR2(10),
	PRIMARY KEY(MAGV, MALOP)
);

CREATE TABLE KHOA
(
  MAKHOA CHAR(4),
  TENKHOA NVARCHAR2(50),
  TRUONGPHOKHOA CHAR(5),
  PRIMARY KEY(MAKHOA)
);

CREATE TABLE MONHOC
(
  MAMH CHAR(5),
  TENMH NVARCHAR2(30),
  PRIMARY KEY(MAMH)
);

CREATE TABLE MONHOCTRUOC
(
  MAMH CHAR(5),
  MONHOCTRUOC CHAR(5),
  TIENQUYET RAW(1),
  NGAYBD DATE,
  PRIMARY KEY(MAMH, MONHOCTRUOC, NGAYBD)
);

CREATE TABLE NGANH
(
  MANGANH CHAR(4),
  TENNGANH NVARCHAR2(50),
  TSSV INT,
  SOMON INT,
  KHOA CHAR(4),
  TRUONGNGANH CHAR(5),
  PRIMARY KEY(MANGANH)
);

CREATE TABLE NGANH_MONHOC
(
  MANGANH CHAR(4),
  MAMH CHAR(5),
  BATBUOC RAW(1),
  PRIMARY KEY(MANGANH, MAMH)
);

CREATE TABLE SINHVIEN_MONHOC
(
  MASV CHAR(5),
	MAMON CHAR(5),
  TINHTRANG VARCHAR2(6),
  PRIMARY KEY(MASV, MAMON)
);

CREATE TABLE KHOA_NGUOIDUNG (
  MAND CHAR(5),
  KHOA RAW(2000),
  PRIMARY KEY (MAND)
);

CREATE TABLE DUANHOCTHUAT
(
  MADUAN CHAR(5),
  TENDUAN NVARCHAR2(50),
  KHUVUC NVARCHAR2(20), --khu vực là trường (Nhỏ), quốc gia (Vừa), quốc tế (Lớn)
  NGANH CHAR(4),
  CHINHANH CHAR(4),
  PRIMARY KEY(MaDuAn)
);

CREATE TABLE CHINHANH
(
  MACN CHAR(4),
  TENCN NVARCHAR2(6),
  PRIMARY KEY(MACN)
);

ALTER TABLE DUANHOCTHUAT
ADD CONSTRAINT FK_DUANHOCTHUAT_CHINHANH
FOREIGN KEY (CHINHANH)
REFERENCES CHINHANH(MACN); 

ALTER TABLE DUANHOCTHUAT
ADD CONSTRAINT FK_DUANHOCTHUAT_NGANH
FOREIGN KEY (NGANH)
REFERENCES NGANH(MANGANH); 

ALTER TABLE KHOA_NGUOIDUNG
ADD CONSTRAINT FK_KHOA_NGUOIDUNG__NGUOIDUNG
FOREIGN KEY (MAND)
REFERENCES NGUOIDUNG(MAND);

ALTER TABLE SINHVIEN_MONHOC
ADD CONSTRAINT FK_SINHVIEN_MONHOC__NGUOIDUNG
FOREIGN KEY (MASV)
REFERENCES NGUOIDUNG(MAND);

ALTER TABLE SINHVIEN_MONHOC
ADD CONSTRAINT FK_SINHVIEN_MONHOC__MONHOC
FOREIGN KEY (MAMON)
REFERENCES MONHOC(MAMH);

ALTER TABLE LOP
ADD CONSTRAINT FK_LOP_NGUOIDUNG1
FOREIGN KEY (GIAOVU)
REFERENCES NGUOIDUNG(MAND);

ALTER TABLE LOP
ADD CONSTRAINT FK_LOP_NGUOIDUNG
FOREIGN KEY (TPKHOA)
REFERENCES NGUOIDUNG(MAND);

ALTER TABLE LOP
ADD CONSTRAINT FK_LOP_MON
FOREIGN KEY (MONHOC)
REFERENCES MONHOC(MAMH);

ALTER TABLE NGUOIDUNG
ADD CONSTRAINT FK_NGUOIDUNG_NGANH
FOREIGN KEY (NGANH)
REFERENCES NGANH(MANGANH);

ALTER TABLE SINHVIEN_LOP
ADD CONSTRAINT FK_SINHVIEN_LOP__LOP
FOREIGN KEY (MALOP)
REFERENCES LOP(MALOP);

ALTER TABLE SINHVIEN_LOP
ADD CONSTRAINT FK_SINHVIEN_LOP__SINHVIEN
FOREIGN KEY (MASV)
REFERENCES NGUOIDUNG(MAND);

ALTER TABLE LICHDAY
ADD CONSTRAINT FK_LICHDAY_NGUOIDUNG
FOREIGN KEY (MAGV)
REFERENCES NGUOIDUNG(MAND);

ALTER TABLE LICHDAY
ADD CONSTRAINT FK_LICHDAY_LOP
FOREIGN KEY (MALOP)
REFERENCES LOP(MALOP);

ALTER TABLE KHOA
ADD CONSTRAINT FK_KHOA_NGUOIDUNG
FOREIGN KEY (TRUONGPHOKHOA)
REFERENCES NGUOIDUNG(MAND);

ALTER TABLE MONHOCTRUOC
ADD CONSTRAINT FK_MONHOCTRUOC_MONHOC1
FOREIGN KEY (MAMH)
REFERENCES MONHOC(MAMH);

ALTER TABLE MONHOCTRUOC
ADD CONSTRAINT FK_MONHOCTRUOC_MONHOC2
FOREIGN KEY (MONHOCTRUOC)
REFERENCES MONHOC(MAMH);

ALTER TABLE NGANH
ADD CONSTRAINT FK_NGANH_NGUOIDUNG
FOREIGN KEY (TRUONGNGANH)
REFERENCES NGUOIDUNG(MAND);

ALTER TABLE NGANH
ADD CONSTRAINT FK_NGANH_KHOA
FOREIGN KEY (KHOA)
REFERENCES KHOA(MAKHOA);

ALTER TABLE NGANH_MONHOC
ADD CONSTRAINT FK_NGANH_MONHOC__NGANH
FOREIGN KEY (MANGANH)
REFERENCES NGANH(MANGANH);

ALTER TABLE NGANH_MONHOC
ADD CONSTRAINT FK_NGANH_MONHOC__MONHOC
FOREIGN KEY (MAMH)
REFERENCES MONHOC(MAMH);
