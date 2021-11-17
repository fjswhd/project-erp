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
	emp_email		varchar2(50)		not null,
	emp_addr_no		varchar2(7)			not null,
	emp_addr		varchar2(50)		not null,
	emp_addr_detail	varchar2(50)		not null,
	emp_tel			varchar2(15)		not null,
	hiredate		date				default sysdate,
	resign			char(1)				default 'n'
);
drop table emp;

create or replace view v_hr
as
	select e1.rn, e1.emp_no, e1.emp_name, d.dept_name, e1.emp_tel, e1.emp_email 
	from (select rownum rn, e.* from emp e) e1 
	join dept d
	on e1.dept_no = d.dept_no 
	order by emp_no
with read only;
insert into emp values ('21-00001', 50, '1234', '이종민', 'fjswhd93@gmail.com', '10358','고양시', '덕양구', '010-9052-1980', to_date('210502', 'YYMMDD'), 'n');
