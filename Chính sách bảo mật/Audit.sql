create role GIAOVU;
create role SINHVIEN;
create role GIAOVIEN;

grant GIAOVU to GVU01;
grant GIAOVU to GVU02;
grant GIAOVU to GVU03;
grant GIAOVU to GVU04;
grant GIAOVU to GVU05;

grant SINHVIEN to "11001";
grant SINHVIEN to "11002";
grant SINHVIEN to "11003";
grant SINHVIEN to "11004";
grant SINHVIEN to "11005";
grant SINHVIEN to "11006";
grant SINHVIEN to "11007";
grant SINHVIEN to "11008";
grant SINHVIEN to "11009";
grant SINHVIEN to "11010";
grant SINHVIEN to "11011";
grant SINHVIEN to "11012";
grant SINHVIEN to "11013";
grant SINHVIEN to "11014";
grant SINHVIEN to "11015";
grant SINHVIEN to "11016";
grant SINHVIEN to "11017";
grant SINHVIEN to "11018";
grant SINHVIEN to "11019";
grant SINHVIEN to "11020";
grant SINHVIEN to "11021";
grant SINHVIEN to "11022";
grant SINHVIEN to "11023";
grant SINHVIEN to "11024";
grant SINHVIEN to "11025";
grant SINHVIEN to "11026";
grant SINHVIEN to "11027";

grant GIAOVIEN to GV001;
grant GIAOVIEN to GV002;
grant GIAOVIEN to GV003;
grant GIAOVIEN to GV004;
grant GIAOVIEN to GV005;
grant GIAOVIEN to GV006;
grant GIAOVIEN to GV007;
grant GIAOVIEN to GV008;
grant GIAOVIEN to GV009;
grant GIAOVIEN to GV010;
grant GIAOVIEN to GV011;
grant GIAOVIEN to GV012;
grant GIAOVIEN to GV013;
grant GIAOVIEN to GV014;
grant GIAOVIEN to GV015;
grant GIAOVIEN to GV016;
grant GIAOVIEN to GV017;
grant GIAOVIEN to GV018;
grant GIAOVIEN to GV019;
grant GIAOVIEN to GV020;
grant GIAOVIEN to GV021;
grant GIAOVIEN to GV022;
grant GIAOVIEN to GV023;
grant GIAOVIEN to GV024;
grant GIAOVIEN to GV025;
grant GIAOVIEN to GV026;
grant GIAOVIEN to GV027;

grant select, insert, update, delete on LOP to GIAOVU;
grant select on MONHOC to GIAOVU;
grant select on LICHDAY to GIAOVU;

grant select, insert, delete, update(MaSV, MaLop) on SINHVIEN_LOP to GIAOVU;

grant select, insert, delete on SINHVIEN_LOP to SINHVIEN;

grant select, update on SINHVIEN_LOP to GIAOVIEN;

--revoke all on SINHVIEN_LOP from GIAOVIEN;

-- AUDIT
-- chu y: grant quyen truoc khi thuc hien cac buoc duoi (neu co grant quyen them thi phai thuc hien lai cac buoc duoi)
-- thuc hien trong cmd nhung cau lenh
-- sqlplus “/as sysdba”
-- alter system set audit_trail='db','extended' scope=spfile;
-- shutdown immediate;
-- startup;

-- QTV audit insert, delete, update tren bang SINHVIEN_LOP tat ca lenh successful/not successful
audit insert, delete, update on QTV.SINHVIEN_LOP by access;
--noaudit all on QTV.SINHVIEN_LOP;

-- GVU xoa dki lop LOP01 cua sv 11001
delete from QTV.SINHVIEN_LOP where MASV = '11001' and MALOP = 'LOP01';

-- GVU dki LOP01 cho sv 11001
insert into QTV.SINHVIEN_LOP values ('11001', 'LOP01', 0);

-- QTV goi audit ra de theo doi qua trinh dki lop (insert vs delete)
select username, owner, obj_name, action_name, extended_timestamp, sql_text
from dba_audit_trail
where action_name = 'INSERT' or action_name = 'DELETE';

-- GV cap nhat diem cho sv 11001 lop LOP01
update QTV.SINHVIEN_LOP set DIEM = 7 where MASV = '11001' and MALOP = 'LOP01';

-- QTV goi audit ra de theo doi qua trinh sua diem (update)
select username, owner, obj_name, action_name, extended_timestamp, sql_text
from dba_audit_trail
where action_name = 'UPDATE';

select * from SINHVIEN_LOP where MASV = '11001' and MALOP = 'LOP01';