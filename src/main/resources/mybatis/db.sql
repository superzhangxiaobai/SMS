drop table demo_security_user ;
create table demo_security_user(
	EID INT IDENTITY(1, 1) PRIMARY KEY,/*'springsecurity测试表';*/
	EITIME DATE  default (GETDATE()) ,
	EUTIME DATE  default (GETDATE()) ,
	username VARCHAR(50) default '' not null,
	password VARCHAR(50) default ''not null,
	access_level int default 2 not null,/*用户权限*/
	description VARCHAR(64) default ''
);

alter table demo_security_user add constraint PK_EMHK_RM_demo_security_user primary key(EID) using index tablespace tbs_unvidx;
grant insert,update,delete on demo_security_user to dbo;

select username, password, access_level,description from demo_security_user t
		where 1=1;

insert into demo_security_user(eitime,eutime,username,password,access_level,description)
values(GETDATE(),GETDATE(),'username1','password1',0,'description1');
insert into demo_security_user(EITIME,EUTIME,username,password,access_level,description)
values(GETDATE(),GETDATE(),'username2','password2',1,'description2');
insert into demo_security_user(EITIME,EUTIME,username,password,access_level,description)
values(GETDATE(),GETDATE(),'username3','password3',1,'description3');
insert into demo_security_user(EITIME,EUTIME,username,password,access_level,description)
values(GETDATE(),GETDATE(),'admin','admin',0,'admin');
insert into demo_security_user(EITIME,EUTIME,username,password,access_level,description)
values(GETDATE(),GETDATE(),'app','app',1,'app');