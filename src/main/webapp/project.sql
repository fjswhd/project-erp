create table dept (
	dept_no 	number 			primary key,
	dept_name 	varchar2(12) 	not null
);

insert into dept values (10, '구매');
insert into dept values (20, '판매');
insert into dept values (30, '재고');
insert into dept values (40, '회계');
insert into dept values (50, '인사');

create table emp (
	emp_no 			varchar2(10) 		primary key,
	dept_no 		references dept(dept_no),
	password		varchar2(15)		not null,
	emp_name		varchar2(15)		not null,
	emp_email		varchar2(30)		not null,
	emp_address		varchar2(50)		not null,
	emp_tel			varchar2(15)		not null,
	hiredate		date				default sysdate,
	resign			char(1)				default 'n'
);

insert into emp values ('21-00001', 50, '1234', '이종민', 'fjswhd93@gmail.com', '고양시', '01090521980', to_date('210502', 'YYMMDD'), 'n');
insert into emp values ('21-00002', 40, '1234', '배문지', 'moonji418@naver.com', '서울특별시', '01000001980', to_date('210418', 'YYMMDD'), 'n');
insert into emp values ('21-00003', 30, '1234', '홍길동', 'rlfehd12@gmail.com', '서울특별시', '01011111980', to_date('210421', 'YYMMDD'), 'n');
insert into emp values ('21-00004', 20, '1234', '김철수', 'cjftn92@hanmail.net', '의정부시', '01022221980', to_date('211225', 'YYMMDD'), 'n');
insert into emp values ('21-00005', 10, '1234', '김영희', 'young123@gmail.com', '고양시', '01033331980', to_date('210920', 'YYMMDD'), 'n');