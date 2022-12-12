DROP SEQUENCE IF EXISTS "MAHASISWA_S";
CREATE SEQUENCE "MAHASISWA_S"
MINVALUE 1
MAXVALUE 999999999
INCREMENT BY 1
START WITH 3
NOCACHE
NOCYCLE;

insert into mahasiswa (id,nama,npm,alamat,jeniskelamin,tempatlahir,tanggallahir,createddate,createdby) values (nextval('MAHASISWA_S'),'Asep','08728272','Bogor','L','BOGOR',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'SYSTEM');
insert into mahasiswa (id,nama,npm,alamat,jeniskelamin,tempatlahir,tanggallahir,createddate,createdby) values (nextval('MAHASISWA_S'),'Yola','08728273','Jakarta','P','Jakarta',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'SYSTEM');