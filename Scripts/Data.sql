--GIAOVU
Insert into NGUOIDUNG values ('GVU01', 'Nguyễn Công Thành',  TO_DATE( '23/06/1985', 'dd/mm/yyyy'), '1 Cộng Hòa, phường 1, quận Tân Bình, TP.HCM', '0922221111', null, 0);
Insert into NGUOIDUNG values ('GVU02', 'Nguyễn Minh Tâm', TO_DATE( '15/05/1986', 'dd/mm/yyyy'), '12 Trường Chinh, phường 13, quận Tân Bình, TP.HCM', '0922221112', null, 0);
Insert into NGUOIDUNG values ('GVU03', 'Nguyễn Thịnh Phát', TO_DATE( '21/10/1987','dd/mm/yyyy'), '118 Lê Lợi, phường Nguyễn Cư Trinh, quận 1, TP.HCM', '0922221113', null, 0);
Insert into NGUOIDUNG values ('GVU04', 'Trần Tố Nhiên', TO_DATE('25/03/1984', 'dd/mm/yyyy'), '89 Xô Viết Nghệ Tĩnh, phường 17, quận Bình Thạnh, TP.HCM', '0922221114', null, 0);
Insert into NGUOIDUNG values ('GVU05', 'Lê Thanh', TO_DATE('08/01/1988', 'dd/mm/yyyy'), '534 Nguyễn Trãi, phường 9, quận 5, TP.HCM', '0922221115', null, 0);

-- KHOA
Insert into KHOA values ('KH01', 'Công nghệ thông tin',null);
Insert into KHOA values ('KH02', 'Công nghệ sinh học', null);
Insert into KHOA values ('KH03', 'Hóa học', null);

-- NGANH
Insert into NGANH values ('NG01', 'Hệ thống thông tin', 3, 3, 'KH01', null);
Insert into NGANH values ('NG02', 'Công nghệ phần mềm', 3, 3, 'KH01', null);
Insert into NGANH values ('NG03', 'Khoa học máy tính', 3, 3, 'KH01', null);
Insert into NGANH values ('NG04', 'Di truyền', 3, 3, 'KH02', null);
Insert into NGANH values ('NG05', 'Hóa sinh', 3, 3, 'KH02', null);
Insert into NGANH values ('NG06', 'Vi sinh', 3, 3, 'KH02', null);
Insert into NGANH values ('NG07', 'Hóa dược', 3, 3, 'KH03', null);
Insert into NGANH values ('NG08', 'Hóa phân tích', 3, 3, 'KH03', null);
Insert into NGANH values ('NG09', 'Hóa học Polyme', 3, 3, 'KH03', null);

-- GIAOVIEN
Insert into NGUOIDUNG values ('GV001', 'Ngô Hồng Phúc', TO_DATE('15/05/1991', 'dd/mm/yyyy'), '123 Nguyễn Huệ, phường Đa Kao, quận 1, TP.HCM', '0988775501', 'NG01', 1);
Insert into NGUOIDUNG values ('GV002', 'Nguyễn Hồng Vân', TO_DATE('25/03/1984', 'dd/mm/yyyy'), '24 Trần Hưng Đạo, phường 19, quận 5, TP.HCM', '0988775502', 'NG01', 1);
Insert into NGUOIDUNG values ('GV003', 'Lê Uyên', TO_DATE('10/03/1989', 'dd/mm/yyyy'), '983 Kinh Dương Vương, phường 17, quận Bình Tân, TP.HCM', '0988775503', 'NG01', 1);
Insert into NGUOIDUNG values ('GV004', 'Nguyễn Văn Lợi', TO_DATE('07/10/1989', 'dd/mm/yyyy'), '65 Lê Quang Định, phường 25, quận Bình Thạnh, TP.HCM', '0988775504', 'NG02', 1);
Insert into NGUOIDUNG values ('GV005', 'Huỳnh Điệp', TO_DATE('09/03/1986', 'dd/mm/yyyy'), '37 Âu Cơ, phường 14, quận Tân Phú, TP.HCM', '0988775505', 'NG02', 1);
Insert into NGUOIDUNG values ('GV006', 'Võ Minh Long', TO_DATE('27/02/1985', 'dd/mm/yyyy'), '93 Hoàng Diệu, phường 11, quận 4, TP.HCM', '0988775506', 'NG02', 1);
Insert into NGUOIDUNG values ('GV007', 'Ngô Mộc Đức', TO_DATE('19/02/1985', 'dd/mm/yyyy'), '423 Phan Văn Trị, phường 21, quận Gò Vấp, TP.HCM', '0988775507', 'NG03', 1);
Insert into NGUOIDUNG values ('GV008', 'Vũ Hoài', TO_DATE('11/04/1987', 'dd/mm/yyyy'), '551 Tô Hiến Thành, phường 9, quận 10, TP.HCM', '0988775508', 'NG03', 1);
Insert into NGUOIDUNG values ('GV009', 'Cao Đức Tâm', TO_DATE('28/01/1988', 'dd/mm/yyyy'), '201 Thành Thái, phường 13, quận 10, TP.HCM', '0988775509', 'NG03', 1);
Insert into NGUOIDUNG values ('GV010', 'Nguyễn Hải Yến', TO_DATE('16/11/1984', 'dd/mm/yyyy'), '85 Điện Biên Phủ, phường Đa Kao, quận 3, TP.HCM', '0988775510', 'NG04', 1);
Insert into NGUOIDUNG values ('GV011', 'Cao Huy Dũng', TO_DATE('24/12/1990', 'dd/mm/yyyy'), '23 Nam Kì Khởi Nghĩa, phường 2, quận 3, TP.HCM', '0988775511', 'NG04', 1);
Insert into NGUOIDUNG values ('GV012', 'Ngô Thành', TO_DATE('12/05/1989', 'dd/mm/yyyy'), '823 Hai Bà Trưng, phường Đa Kao, quận 1, TP.HCM', '0988775512', 'NG04', 1);
Insert into NGUOIDUNG values ('GV013', 'Võ Thiên Duy', TO_DATE('19/08/1985', 'dd/mm/yyyy'), '53 Tự Do, phường 29, quận Tân Phú, TP.HCM', '0988775513', 'NG05', 1);
Insert into NGUOIDUNG values ('GV014', 'Nguyễn Nhạc', TO_DATE('12/03/1987', 'dd/mm/yyyy'), '17 Hòa Bình, phường 2, quận 11, TP.HCM', '0988775514', 'NG05', 1);
Insert into NGUOIDUNG values ('GV015', 'Nguyễn Công Vũ', TO_DATE('05/10/1986', 'dd/mm/yyyy'), '91 Lũy Bán Bích, phường 27, quận Tân Phú, TP.HCM', '0988775515', 'NG05', 1);
Insert into NGUOIDUNG values ('GV016', 'Trần Ngọc Quế', TO_DATE('22/06/1988', 'dd/mm/yyyy'), '69 Lý Thường Kiệt, phường 16, quận 10, TP.HCM', '0988775516', 'NG06', 1);
Insert into NGUOIDUNG values ('GV017', 'Trần Thanh Nhàn', TO_DATE('20/03/1986', 'dd/mm/yyyy'), '110 Cao Thắng, phường 10, quận 3, TP.HCM', '0988775517', 'NG06', 1);
Insert into NGUOIDUNG values ('GV018', 'Nguyễn Thanh Sơn', TO_DATE('04/01/1988', 'dd/mm/yyyy'), '372 Đề Thám, phường Nguyễn Cư Trinh, quận 1, TP.HCM', '0988775518', 'NG06', 1);
Insert into NGUOIDUNG values ('GV019', 'Ngô Tiến Minh', TO_DATE('07/12/1987', 'dd/mm/yyyy'), '389 Hoàng Hoa Thám, phường 7, quận Tân Bình, TP.HCM', '0988775519', 'NG07', 1);
Insert into NGUOIDUNG values ('GV020', 'Lê Quang Tiến', TO_DATE('08/06/1985', 'dd/mm/yyyy'), '426 Phan Đăng Lưu, phường Đa 04, quận Phú Nhuận, TP.HCM', '0988775520', 'NG07', 1);
Insert into NGUOIDUNG values ('GV021', 'Lê Thị Diệu', TO_DATE('05/10/1990', 'dd/mm/yyyy'), '929 Lê Văn Lương, phường 15, quận 7, TP.HCM', '0988775521', 'NG07', 1);
Insert into NGUOIDUNG values ('GV022', 'Nguyễn Sinh', TO_DATE('12/09/1991', 'dd/mm/yyyy'), '13 Hồng Bàng, phường 03, quận 5, TP.HCM', '0988775522', 'NG08', 1);
Insert into NGUOIDUNG values ('GV023', 'Đỗ Hoàng', TO_DATE('11/11/1992', 'dd/mm/yyyy'), '45 Nguyễn Chí Thanh, phường 23, quận 5, TP.HCM', '0988775523', 'NG08', 1);
Insert into NGUOIDUNG values ('GV024', 'Dương Minh Phú', TO_DATE('04/08/1990', 'dd/mm/yyyy'), '435 Phạm Văn Đồng, phường 18, quận Gò Vấp, TP.HCM', '0988775524', 'NG08', 1);
Insert into NGUOIDUNG values ('GV025', 'Phan Trung Hậu', TO_DATE('28/06/1987', 'dd/mm/yyyy'), '346 Bạch Đằng, phường 24, quận Bình Thạnh, TP.HCM', '0988775525', 'NG09', 1);
Insert into NGUOIDUNG values ('GV026', 'Phạm Hải Triều', TO_DATE('27/04/1989', 'dd/mm/yyyy'), '256 Nguyễn Thượng Hiền, phường 13, quận Phú Nhuận, TP.HCM', '0988775526', 'NG09', 1);
Insert into NGUOIDUNG values ('GV027', 'Phan Tâm Nhân', TO_DATE('29/10/1986', 'dd/mm/yyyy'), '87 Lê Lai, phường Đa Kao, quận 1, TP.HCM', '0988775527', 'NG09', 1);

-- SINHVIEN	
insert into NGUOIDUNG values ('11001', 'Nguyễn Hoài An', TO_DATE('22/01/1994', 'dd/mm/yyyy'), '25/3 Lạc Long Quân, Q10, TPHCM', '0977339901', 'NG01', 2);
insert into NGUOIDUNG values ('11002', 'Nguyễn Quốc An', TO_DATE('09/12/1994', 'dd/mm/yyyy'), 'Trần Hưng Đạo, Q1, TPHCM', '0977339902', 'NG01', 2);
insert into NGUOIDUNG values ('11003', 'Nguyễn Ngọc Anh', TO_DATE('09/10/1994', 'dd/mm/yyyy'), '12/11 Võ Văn Ngân, Thủ Đức, TPHCM', '0977339903', 'NG01', 2);
insert into NGUOIDUNG values ('11004', 'Nguyễn Bảo Anh', TO_DATE('10/04/1994', 'dd/mm/yyyy'), '221, Hùng Vương, Q5, TPHCM', '0977339904', 'NG02', 2);
insert into NGUOIDUNG values ('11005', 'Nguyễn Quốc Anh', TO_DATE('04/02/1994', 'dd/mm/yyyy'), 'Lý Thái Tổ, Q3, TPHCM', '0977339905', 'NG02', 2);
insert into NGUOIDUNG values ('11006', 'Trần Ngọc Ánh', TO_DATE('01/09/1994', 'dd/mm/yyyy'), 'Bà Hạt, Q10, TPHCM', '0977339906', 'NG02', 2);
insert into NGUOIDUNG values ('11007', 'Nguyễn Mỹ Anh', TO_DATE('01/03/1994', 'dd/mm/yyyy'), 'Thành Thái, Q10, TPHCM', '0977339907', 'NG03', 2);
insert into NGUOIDUNG values ('11008', 'Trần Hoài An', TO_DATE('12/03/1994', 'dd/mm/yyyy'), '225, An Dương Vương, Q5, TPHCM', '0977339908', 'NG03', 2);
insert into NGUOIDUNG values ('11009', 'Nguyễn Thúy An', TO_DATE('05/01/1994', 'dd/mm/yyyy'), 'Đề Thám, Q1, TPHCM', '0977339909', 'NG03', 2);
insert into NGUOIDUNG values ('11010', 'Trần Hoài Bảo', TO_DATE('03/12/1994', 'dd/mm/yyyy'), 'Trần Hưng Đạo, Q1, TPHCM', '0977339910', 'NG04', 2);
insert into NGUOIDUNG values ('11011', 'Nguyễn Hoài Thu', TO_DATE('29/09/1994', 'dd/mm/yyyy'), 'Lý Thái Tổ, Q3, TPHCM', '0977339911', 'NG04', 2);
insert into NGUOIDUNG values ('11012', 'Nguyễn Anh Thư', TO_DATE('02/11/1994', 'dd/mm/yyyy'), 'Võ Văn Tần, Q3, TPHCM', '0977339912', 'NG04', 2);
insert into NGUOIDUNG values ('11013', 'Võ Hoài Anh', TO_DATE('09/06/1994', 'dd/mm/yyyy'), '15/11 Võ Văn Ngân, Thủ Đức, TPHCM', '0977339913', 'NG05', 2);
insert into NGUOIDUNG values ('11014', 'Lưu Đăng Anh', TO_DATE('11/11/1994', 'dd/mm/yyyy'), '223, An Dương Vương, Q5, TPHCM', '0977339914', 'NG05', 2);
insert into NGUOIDUNG values ('11015', 'Trần Minh Tâm', TO_DATE('12/04/1994', 'dd/mm/yyyy'), 'Thành Thái, Q10, TPHCM', '0977339915', 'NG05', 2);
insert into NGUOIDUNG values ('11016', 'Huỳnh Yến Linh', TO_DATE('17/6/1994', 'dd/mm/yyyy'), 'Hàm Nghi, Q1, TPHCM', '0977339916', 'NG06', 2);
insert into NGUOIDUNG values ('11017', 'Nguyễn Tiến Đạt', TO_DATE('24/05/1994', 'dd/mm/yyyy'), '12/11 Hoàng Diệu, Thủ Đức, TPHCM', '0977339917', 'NG06', 2);
insert into NGUOIDUNG values ('11018', 'Nguyễn Ngọc Trân', TO_DATE('29/06/1994', 'dd/mm/yyyy'), 'Cô Bắc, Q1, TPHCM', '0977339918', 'NG06', 2);
insert into NGUOIDUNG values ('11019', 'Nguyễn Hoàng Anh', TO_DATE('02/10/1994', 'dd/mm/yyyy'), 'Đề Thám, Q1, TPHCM', '0977339919', 'NG07', 2);
insert into NGUOIDUNG values ('11020', 'Trần Minh Hoàng', TO_DATE('26/06/1994', 'dd/mm/yyyy'), 'Trần Hưng Đạo, Q1, TPHCM', '0977339920', 'NG07', 2);
insert into NGUOIDUNG values ('11021', 'Trần Minh Ngọc', TO_DATE('13/04/1994', 'dd/mm/yyyy'), 'Trần Hưng Đạo, Q1, TPHCM', '0977339921', 'NG07', 2);
insert into NGUOIDUNG values ('11022', 'Nguyễn Hoàng Minh', TO_DATE('24/5/1994', 'dd/mm/yyyy'), 'Đề Thám, Q1, TPHCM', '0977339922', 'NG08', 2);
insert into NGUOIDUNG values ('11023', 'Nguyễn Lan Ngọc', TO_DATE('20/07/1994', 'dd/mm/yyyy'), 'Phạm Ngũ Lão, Q1, TPHCM', '0977339923', 'NG08', 2);
insert into NGUOIDUNG values ('11024', 'Lê Quang Minh', TO_DATE('03/02/1994', 'dd/mm/yyyy'), 'Lê Lai, Q1, TPHCM', '0977339924', 'NG08', 2);
insert into NGUOIDUNG values ('11025', 'Đinh Quốc Sơn', TO_DATE('22/02/1994', 'dd/mm/yyyy'), 'Hùng Vương, Q5, TPHCM', '0977339925', 'NG09', 2);
insert into NGUOIDUNG values ('11026', 'Huỳnh Bảo Trân', TO_DATE('25/11/1994', 'dd/mm/yyyy'), 'Thành Thái, Q10, TPHCM', '0977339926', 'NG09', 2);
insert into NGUOIDUNG values ('11027', 'Đỗ Bích Ngọc', TO_DATE('18/01/1994', 'dd/mm/yyyy'), 'Điện Biên Phủ, Q10, TPHCM', '0977339927', 'NG09', 2);

--set TRUONGPHOKHOA
update KHOA set TRUONGPHOKHOA = 'GV001' where MAKHOA = 'KH01';
update KHOA set TRUONGPHOKHOA = 'GV010' where MAKHOA = 'KH02';
update KHOA set TRUONGPHOKHOA = 'GV019' where MAKHOA = 'KH03';

--set TRUONGNGANH
update NGANH set TRUONGNGANH = 'GV002' where MANGANH = 'NG01';
update NGANH set TRUONGNGANH = 'GV005' where MANGANH = 'NG02';
update NGANH set TRUONGNGANH = 'GV008' where MANGANH = 'NG03';
update NGANH set TRUONGNGANH = 'GV011' where MANGANH = 'NG04';
update NGANH set TRUONGNGANH = 'GV014' where MANGANH = 'NG05';
update NGANH set TRUONGNGANH = 'GV017' where MANGANH = 'NG06';
update NGANH set TRUONGNGANH = 'GV020' where MANGANH = 'NG07';
update NGANH set TRUONGNGANH = 'GV023' where MANGANH = 'NG08';
update NGANH set TRUONGNGANH = 'GV026' where MANGANH = 'NG09';

-- MONHOC
Insert into MONHOC values ('MON01', 'Cơ sở dữ liệu');
Insert into MONHOC values ('MON02', 'Cơ sở dữ liệu nâng cao');
Insert into MONHOC values ('MON03', 'Hệ quản trị cơ sở dữ liệu');
Insert into MONHOC values ('MON04', 'Nhập môn công nghê phần mềm');
Insert into MONHOC values ('MON05', 'Công nghệ XML và ứng dụng');
Insert into MONHOC values ('MON06', 'Kiểm chứng phần mềm');
Insert into MONHOC values ('MON07', 'Cơ sở trí tuệ nhân tạo');
Insert into MONHOC values ('MON08', 'Khai thác dữ liệu và ứng dụng');
Insert into MONHOC values ('MON09', 'Lập trình song song trên GPU');
Insert into MONHOC values ('MON10', 'Di truyền gen');
Insert into MONHOC values ('MON11', 'Di truyền tổ hợp');
Insert into MONHOC values ('MON12', 'Di truyền nhân tạo');
Insert into MONHOC values ('MON13', 'Sinh học thực vật');
Insert into MONHOC values ('MON14', 'Sinh học động vật');
Insert into MONHOC values ('MON15', 'Sinh học tế bào');
Insert into MONHOC values ('MON16', 'Vi khuẩn');
Insert into MONHOC values ('MON17', 'Sinh học kí sinh');
Insert into MONHOC values ('MON18', 'Sinh học vi sinh');
Insert into MONHOC values ('MON19', 'Hóa hữu cơ đại cương');
Insert into MONHOC values ('MON20', 'Hóa học thực vật');
Insert into MONHOC values ('MON21', 'Hóa học y sinh');
Insert into MONHOC values ('MON22', 'Hóa phân tích vô cơ');
Insert into MONHOC values ('MON23', 'Hóa phân tích hữu cơ');
Insert into MONHOC values ('MON24', 'Hóa học vi sinh');
Insert into MONHOC values ('MON25', 'Hóa vô cơ');
Insert into MONHOC values ('MON26', 'Hóa lý');
Insert into MONHOC values ('MON27', 'Hóa học nhân tạo');

-- LOP
Insert into LOP values ('LOP01', 1, 2017, null , 'GV001', 40, 'MON01', 'NG01', 'KH01');
Insert into LOP values ('LOP02', 1, 2017, null , 'GV001', 50, 'MON02', 'NG01', 'KH01');
Insert into LOP values ('LOP03', 1, 2017, null , 'GV001', 70, 'MON03', 'NG01', 'KH01');
Insert into LOP values ('LOP04', 1, 2017, null , 'GV001', 30, 'MON04', 'NG02', 'KH01');
Insert into LOP values ('LOP05', 1, 2017, 'GVU01', null, 20, 'MON05', 'NG02', 'KH01');
Insert into LOP values ('LOP06', 1, 2017, 'GVU01', null, 90, 'MON06', 'NG02', 'KH01');
Insert into LOP values ('LOP07', 1, 2017, 'GVU01', null, 40, 'MON07', 'NG03', 'KH01');
Insert into LOP values ('LOP08', 1, 2017, 'GVU01', null, 40, 'MON08', 'NG03', 'KH01');
Insert into LOP values ('LOP09', 1, 2017,  null, 'GV001', 40, 'MON09', 'NG03', 'KH01');
Insert into LOP values ('LOP10', 1, 2017, null, 'GV010', 40, 'MON10', 'NG04', 'KH02');
Insert into LOP values ('LOP11', 1, 2017, null, 'GV010', 40, 'MON11', 'NG04', 'KH02');
Insert into LOP values ('LOP12', 1, 2017, null, 'GV010', 40, 'MON12', 'NG04', 'KH02');
Insert into LOP values ('LOP13', 1, 2017, null, 'GV010', 40, 'MON13', 'NG05', 'KH02');
Insert into LOP values ('LOP14', 1, 2017, null, 'GV010', 40, 'MON14', 'NG05', 'KH02');
Insert into LOP values ('LOP15', 1, 2017, null, 'GV010', 40, 'MON15', 'NG05', 'KH02');
Insert into LOP values ('LOP16', 1, 2017, 'GVU02', null, 40, 'MON16', 'NG06', 'KH02');
Insert into LOP values ('LOP17', 1, 2017, 'GVU02', null, 40, 'MON17', 'NG06', 'KH02');
Insert into LOP values ('LOP18', 1, 2017, 'GVU02', null, 40, 'MON18', 'NG06', 'KH02');
Insert into LOP values ('LOP19', 1, 2017, 'GVU03', null, 60, 'MON19', 'NG07', 'KH03');
Insert into LOP values ('LOP20', 1, 2017, null, 'GV019', 48, 'MON20', 'NG07', 'KH03');
Insert into LOP values ('LOP21', 1, 2017, null, 'GV019', 63, 'MON21', 'NG07', 'KH03');
Insert into LOP values ('LOP22', 1, 2017, null, 'GV019', 40, 'MON22', 'NG08', 'KH03');
Insert into LOP values ('LOP23', 1, 2017, null, 'GV019', 40, 'MON23', 'NG08', 'KH03');
Insert into LOP values ('LOP24', 1, 2017, null, 'GV019', 40, 'MON24', 'NG08', 'KH03');
Insert into LOP values ('LOP25', 1, 2017, 'GVU03', null, 40, 'MON25', 'NG09', 'KH03');
Insert into LOP values ('LOP26', 1, 2017, 'GVU03', null, 50, 'MON26', 'NG09', 'KH03');
Insert into LOP values ('LOP27', 1, 2017, 'GVU03', null, 80, 'MON27', 'NG09', 'KH03');

Insert into LOP values ('LOP28', 1, 2017, null, 'GV001', 55, 'MON02', 'NG01', 'KH01');
Insert into LOP values ('LOP29', 1, 2017, null, 'GV001', 40, 'MON01', 'NG01', 'KH01');

-- SINHVIEN_LOP
insert into SINHVIEN_LOP values ('11001', 'LOP01', NULL);
insert into SINHVIEN_LOP values ('11002', 'LOP01', NULL);
insert into SINHVIEN_LOP values ('11003', 'LOP01', NULL);
insert into SINHVIEN_LOP values ('11001', 'LOP02', NULL);
insert into SINHVIEN_LOP values ('11002', 'LOP02', NULL);
insert into SINHVIEN_LOP values ('11003', 'LOP02', NULL);
insert into SINHVIEN_LOP values ('11001', 'LOP03', NULL);
insert into SINHVIEN_LOP values ('11002', 'LOP03', NULL);
insert into SINHVIEN_LOP values ('11003', 'LOP03', NULL);
 			
insert into SINHVIEN_LOP values ('11004', 'LOP04', NULL); 
insert into SINHVIEN_LOP values ('11005', 'LOP04', NULL); 
insert into SINHVIEN_LOP values ('11006', 'LOP04', NULL);
insert into SINHVIEN_LOP values ('11004', 'LOP05', NULL); 
insert into SINHVIEN_LOP values ('11005', 'LOP05', NULL); 
insert into SINHVIEN_LOP values ('11006', 'LOP05', NULL);
insert into SINHVIEN_LOP values ('11004', 'LOP06', NULL); 
insert into SINHVIEN_LOP values ('11005', 'LOP06', NULL); 
insert into SINHVIEN_LOP values ('11006', 'LOP06', NULL);
 												
insert into SINHVIEN_LOP values ('11007', 'LOP07', NULL); 
insert into SINHVIEN_LOP values ('11008', 'LOP07', NULL); 
insert into SINHVIEN_LOP values ('11009', 'LOP07', NULL); 
insert into SINHVIEN_LOP values ('11007', 'LOP08', NULL); 
insert into SINHVIEN_LOP values ('11008', 'LOP08', NULL); 
insert into SINHVIEN_LOP values ('11009', 'LOP08', NULL); 
insert into SINHVIEN_LOP values ('11007', 'LOP09', NULL); 
insert into SINHVIEN_LOP values ('11008', 'LOP09', NULL); 
insert into SINHVIEN_LOP values ('11009', 'LOP09', NULL); 
												
insert into SINHVIEN_LOP values ('11010', 'LOP10', NULL); 
insert into SINHVIEN_LOP values ('11011', 'LOP10', NULL); 
insert into SINHVIEN_LOP values ('11012', 'LOP10', NULL); 
insert into SINHVIEN_LOP values ('11010', 'LOP11', NULL); 
insert into SINHVIEN_LOP values ('11011', 'LOP11', NULL); 
insert into SINHVIEN_LOP values ('11012', 'LOP11', NULL); 
insert into SINHVIEN_LOP values ('11010', 'LOP12', NULL); 
insert into SINHVIEN_LOP values ('11011', 'LOP12', NULL); 
insert into SINHVIEN_LOP values ('11012', 'LOP12', NULL); 
											
insert into SINHVIEN_LOP values ('11013', 'LOP13', NULL);  
insert into SINHVIEN_LOP values ('11014', 'LOP13', NULL); 
insert into SINHVIEN_LOP values ('11015', 'LOP13', NULL); 
insert into SINHVIEN_LOP values ('11013', 'LOP14', NULL); 
insert into SINHVIEN_LOP values ('11014', 'LOP14', NULL); 
insert into SINHVIEN_LOP values ('11015', 'LOP14', NULL); 
insert into SINHVIEN_LOP values ('11013', 'LOP15', NULL); 
insert into SINHVIEN_LOP values ('11014', 'LOP15', NULL); 
insert into SINHVIEN_LOP values ('11015', 'LOP15', NULL); 
											
insert into SINHVIEN_LOP values ('11016', 'LOP16', NULL); 
insert into SINHVIEN_LOP values ('11017', 'LOP16', NULL);  
insert into SINHVIEN_LOP values ('11018', 'LOP16', NULL); 
insert into SINHVIEN_LOP values ('11016', 'LOP17', NULL);  
insert into SINHVIEN_LOP values ('11017', 'LOP17', NULL);  
insert into SINHVIEN_LOP values ('11018', 'LOP17', NULL);  
insert into SINHVIEN_LOP values ('11016', 'LOP18', NULL);  
insert into SINHVIEN_LOP values ('11017', 'LOP18', NULL);  
insert into SINHVIEN_LOP values ('11018', 'LOP18', NULL);  
											
insert into SINHVIEN_LOP values ('11019', 'LOP19', NULL);  
insert into SINHVIEN_LOP values ('11020', 'LOP19', NULL); 
insert into SINHVIEN_LOP values ('11021', 'LOP19', NULL); 
insert into SINHVIEN_LOP values ('11019', 'LOP20', NULL); 
insert into SINHVIEN_LOP values ('11020', 'LOP20', NULL); 
insert into SINHVIEN_LOP values ('11021', 'LOP20', NULL);  
insert into SINHVIEN_LOP values ('11019', 'LOP21', NULL);  
insert into SINHVIEN_LOP values ('11020', 'LOP21', NULL); 
insert into SINHVIEN_LOP values ('11021', 'LOP21', NULL);  
											
insert into SINHVIEN_LOP values ('11022', 'LOP22', NULL); 
insert into SINHVIEN_LOP values ('11023', 'LOP22', NULL); 
insert into SINHVIEN_LOP values ('11024', 'LOP22', NULL); 
insert into SINHVIEN_LOP values ('11022', 'LOP23', NULL);  
insert into SINHVIEN_LOP values ('11023', 'LOP23', NULL); 
insert into SINHVIEN_LOP values ('11024', 'LOP23', NULL); 
insert into SINHVIEN_LOP values ('11022', 'LOP24', NULL); 
insert into SINHVIEN_LOP values ('11023', 'LOP24', NULL); 
insert into SINHVIEN_LOP values ('11024', 'LOP24', NULL); 
												
insert into SINHVIEN_LOP values ('11025', 'LOP25', NULL); 
insert into SINHVIEN_LOP values ('11026', 'LOP25', NULL); 
insert into SINHVIEN_LOP values ('11027', 'LOP25', NULL); 
insert into SINHVIEN_LOP values ('11025', 'LOP26', NULL); 
insert into SINHVIEN_LOP values ('11026', 'LOP26', NULL); 
insert into SINHVIEN_LOP values ('11027', 'LOP26', NULL); 
insert into SINHVIEN_LOP values ('11025', 'LOP27', NULL); 
insert into SINHVIEN_LOP values ('11026', 'LOP27', NULL); 
insert into SINHVIEN_LOP values ('11027', 'LOP27', NULL); 



--SINHVIEN_MONHOC
insert into sinhvien_monhoc values ('11003', 'MON01', 'Rớt');
insert into sinhvien_monhoc values ('11003', 'MON02', 'Đậu');
insert into sinhvien_monhoc values ('11003', 'MON03', 'Đậu');
insert into sinhvien_monhoc values ('11008', 'MON07', 'Rớt');
insert into sinhvien_monhoc values ('11010', 'MON11', 'Rớt');
insert into sinhvien_monhoc values ('11011', 'MON11', 'Rớt');
insert into sinhvien_monhoc values ('11015', 'MON15', 'Rớt');
insert into sinhvien_monhoc values ('11025', 'MON27', 'Rớt');
insert into sinhvien_monhoc values ('11026', 'MON27', 'Rớt');
insert into sinhvien_monhoc values ('11027', 'MON27', 'Rớt');

-- LICHDAY
Insert into LICHDAY values ('GV001', 'LOP01', 2, '1-3');
Insert into LICHDAY values ('GV002', 'LOP02', 3, '4-6');
Insert into LICHDAY values ('GV003', 'LOP03', 4, '1-3');
Insert into LICHDAY values ('GV004', 'LOP04', 5, '7-9');
Insert into LICHDAY values ('GV005', 'LOP05', 6, '4-6');
Insert into LICHDAY values ('GV006', 'LOP06', 2, '10-12');
Insert into LICHDAY values ('GV007', 'LOP07', 3, '1-3');
Insert into LICHDAY values ('GV008', 'LOP08', 4, '4-6');
Insert into LICHDAY values ('GV009', 'LOP09', 5, '7-9');
Insert into LICHDAY values ('GV010', 'LOP10', 6, '1-3');
Insert into LICHDAY values ('GV011', 'LOP11', 2, '7-9');
Insert into LICHDAY values ('GV012', 'LOP12', 3, '10-12');
Insert into LICHDAY values ('GV013', 'LOP13', 4, '7-9');
Insert into LICHDAY values ('GV014', 'LOP14', 5, '1-3');
Insert into LICHDAY values ('GV015', 'LOP15', 6, '1-3');
Insert into LICHDAY values ('GV016', 'LOP16', 2, '4-6');
Insert into LICHDAY values ('GV017', 'LOP17', 3, '4-6');
Insert into LICHDAY values ('GV018', 'LOP18', 4, '7-9');
Insert into LICHDAY values ('GV019', 'LOP19', 5, '7-9');
Insert into LICHDAY values ('GV020', 'LOP20', 6, '10-12');
Insert into LICHDAY values ('GV021', 'LOP21', 2, '1-3');
Insert into LICHDAY values ('GV022', 'LOP22', 3, '4-6');
Insert into LICHDAY values ('GV023', 'LOP23', 4, '7-9');
Insert into LICHDAY values ('GV024', 'LOP24', 5, '1-3');
Insert into LICHDAY values ('GV025', 'LOP25', 6, '4-6');
Insert into LICHDAY values ('GV026', 'LOP26', 2, '7-9');
Insert into LICHDAY values ('GV027', 'LOP27', 3, '1-3');

-- MONHOCTRUOC
Insert into MONHOCTRUOC values ('MON02', 'MON01', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON03', 'MON01', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON05', 'MON04', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON06', 'MON04', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON08', 'MON07', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON09', 'MON07', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON11', 'MON10', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON12', 'MON10', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON14', 'MON13', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON15', 'MON13', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON17', 'MON16', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON18', 'MON16', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON20', 'MON19', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON21', 'MON19', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON23', 'MON22', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON24', 'MON22', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON26', 'MON25', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 
Insert into MONHOCTRUOC values ('MON27', 'MON25', '0', TO_DATE('01/07/2017', 'dd/mm/yyyy')); 

-- NGANH_MONHOC
Insert into NGANH_MONHOC values ('NG01', 'MON01', '1');
Insert into NGANH_MONHOC values ('NG01', 'MON02', '1');
Insert into NGANH_MONHOC values ('NG01', 'MON03', '1');
Insert into NGANH_MONHOC values ('NG02', 'MON04', '1');
Insert into NGANH_MONHOC values ('NG02', 'MON05', '1');
Insert into NGANH_MONHOC values ('NG02', 'MON06', '1');
Insert into NGANH_MONHOC values ('NG03', 'MON07', '1');
Insert into NGANH_MONHOC values ('NG03', 'MON08', '1');
Insert into NGANH_MONHOC values ('NG03', 'MON09', '1');
Insert into NGANH_MONHOC values ('NG04', 'MON10', '1');
Insert into NGANH_MONHOC values ('NG04', 'MON11', '1');
Insert into NGANH_MONHOC values ('NG04', 'MON12', '1');
Insert into NGANH_MONHOC values ('NG05', 'MON13', '1');
Insert into NGANH_MONHOC values ('NG05', 'MON14', '1');
Insert into NGANH_MONHOC values ('NG05', 'MON15', '1');
Insert into NGANH_MONHOC values ('NG06', 'MON16', '1');
Insert into NGANH_MONHOC values ('NG06', 'MON17', '1');
Insert into NGANH_MONHOC values ('NG06', 'MON18', '1');
Insert into NGANH_MONHOC values ('NG07', 'MON19', '1');
Insert into NGANH_MONHOC values ('NG07', 'MON20', '1');
Insert into NGANH_MONHOC values ('NG07', 'MON21', '1');
Insert into NGANH_MONHOC values ('NG08', 'MON22', '1');
Insert into NGANH_MONHOC values ('NG08', 'MON23', '1');
Insert into NGANH_MONHOC values ('NG08', 'MON24', '1');
Insert into NGANH_MONHOC values ('NG09', 'MON25', '1');
Insert into NGANH_MONHOC values ('NG09', 'MON26', '1');
Insert into NGANH_MONHOC values ('NG09', 'MON27', '1');

insert into CHINHANH values ('CN01', 'TP HCM');
insert into CHINHANH values ('CN02', 'Hà Nội');
insert into CHINHANH values ('CN03', 'CN3');
insert into CHINHANH values ('CN04', 'CN4');
insert into CHINHANH values ('CN05', 'CN5');
insert into CHINHANH values ('CN06', 'CN6');

---DUANHOCTHUAT
----1) Cấp trường
--khoa CNTT
insert into DuAnHocThuat values ('DA001', 'Thách thức', 'Trường', 'NG02', 'CN01');
insert into DuAnHocThuat values ('DA002', 'Vui cùng dữ liệu', 'Trường', 'NG01', 'CN01');
insert into DuAnHocThuat values ('DA003', 'Thông minh nhân tạo và tương lai', 'Trường', 'NG03', 'CN01');
--khoa sinh
insert into DuAnHocThuat values ('DA010', 'Vườn ươm Mendel', 'Trường', 'NG05', 'CN01');
insert into DuAnHocThuat values ('DA011', 'Thách thức cùng Poseidon', 'Trường', 'NG06', 'CN01');
----2) Cấp quốc gia
--khoa CNTT
insert into DuAnHocThuat values ('DA004', 'Olympic tin học', 'Quốc gia', 'NG02', 'CN01');
insert into DuAnHocThuat values ('DA005', 'Nấc thang tin học', 'Quốc gia', 'NG01', 'CN01');
--khoa sinh
insert into DuAnHocThuat values ('DA012', 'Ươm mầm tương lai', 'Quốc gia', 'NG05', 'CN01');
insert into DuAnHocThuat values ('DA013', 'Hướng về khoa học', 'Quốc gia', 'NG06', 'CN01');
----3) Cấp quốc tế
--khoa CNTT
insert into DuAnHocThuat values ('DA006', 'Dream it, code it, win it', 'Quốc tế', 'NG02', 'CN01');
insert into DuAnHocThuat values ('DA007', 'Data Science Game', 'Quốc tế', 'NG03', 'CN01');
insert into DuAnHocThuat values ('DA008', 'The AI Challenge', 'Quốc tế', 'NG03', 'CN01');
insert into DuAnHocThuat values ('DA009', 'Innovation, Originality and Entrepreneurship', 'Quốc tế', 'NG01', 'CN01');
--khoa sinh
insert into DuAnHocThuat values ('DA014', 'Biology Challenge', 'Quốc tế', 'NG05', 'CN01');
insert into DuAnHocThuat values ('DA015', 'iGEM', 'Quốc tế', 'NG06', 'CN01');
