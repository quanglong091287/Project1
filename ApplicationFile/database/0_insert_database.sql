create database toeiconline character set utf8
use toeiconline;
INSERT INTO user(name, password, fullname, createddate, roleid) VALUES('admin', '123456', 'admin',CURRENT_TIMESTAMP, 1);
INSERT INTO user(name, password, fullname, createddate, roleid) VALUES('tranquanglong', '123456', 'tran quang long',CURRENT_TIMESTAMP, 2);