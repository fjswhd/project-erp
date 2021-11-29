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
	dept_no 		references dept(dept_no) not null,
	password		varchar2(15)		not null,
	emp_name		varchar2(15)		not null,
	emp_email		varchar2(50)		not null,
	emp_addr_no		varchar2(7)			not null,
	emp_addr		varchar2(200)		not null,
	emp_addr_detail	varchar2(50)		not null,
	emp_tel			varchar2(15)		not null,
	hiredate		date				default sysdate,
	resign			char(1)				default 'n'
);
alter table emp
modify (dept_no not null);

create table Customer (
    customer_no             VARCHAR2(10) 	primary key, 
    customer_name           VARCHAR2(30) 	not null,
    customer_reg_num        VARCHAR2(15) 	not null,
    customer_tel            VARCHAR2(15) 	not null,
    customer_email          VARCHAR2(50) 	not null,
    customer_addr_no        VARCHAR2(7)  	not null,
    customer_addr			VARCHAR2(200) 	not null,
    customer_addr_detail	VARCHAR2(50) 	not null,
    emp_no					REFERENCES Emp(emp_no) ON DELETE set null not null,
    customer_memo           VARCHAR2(100),
    customer_del            CHAR(1) 		default 'n'
); 

create table Sales_order(
    sales_order_no 		NUMBER 		primary key,
    customer_no						REFERENCES Customer(customer_no) ON DELETE set null not null,
    sales_order_date 	DATE 		default (sysdate),
    emp_no 							REFERENCES Emp(emp_no) ON DELETE set null not null
);

create table Product(
    product_no 		NUMBER 			primary key,
    product_name 	VARCHAR2(30) 	not null,
    price 			NUMBER 			not null,
    cost 			NUMBER 			not null,
    stock 			NUMBER 			not null, 
    product_memo 	VARCHAR2(100)
);

create table Sales_order_detail(
    sales_order_no					REFERENCES Sales_order(sales_order_no) ON DELETE set null not null,
    product_no 						REFERENCES Product(product_no) ON DELETE set null not null,
    sales_detail_pcount 	NUMBER 	not null,
    								PRIMARY KEY(sales_order_no, product_no)
);

create table Seller (
    seller_no           VARCHAR2(10) 	primary key, 
    seller_name         VARCHAR2(30) 	not null,
    seller_reg_num      VARCHAR2(15) 	not null,
    seller_tel          VARCHAR2(15) 	not null,
    seller_email        VARCHAR2(50) 	not null,
    seller_addr_no      VARCHAR2(7)  	not null,
    seller_addr			VARCHAR2(200) 	not null,
    seller_addr_detail	VARCHAR2(50) 	not null,
    emp_no				REFERENCES Emp(emp_no) ON DELETE set null not null,
    seller_memo         VARCHAR2(100),
    seller_del          CHAR(1) 		default 'n'
); 

create table Purchase_order(
    purchase_order_no 		NUMBER 		primary key,
    seller_no							REFERENCES Seller(seller_no) ON DELETE set null not null,
    purchase_order_date 	DATE 		default (sysdate),
    emp_no 								REFERENCES Emp(emp_no) ON DELETE set null not null
);

create table Purchase_order_detail(
    purchase_order_no				REFERENCES Purchase_order(purchase_order_no) ON DELETE set null not null,
    product_no 						REFERENCES Product(product_no) ON DELETE set null not null,
    purchase_detail_pcount 	NUMBER 	not null,
    								PRIMARY KEY(purchase_order_no, product_no)
);

create table Cash (
	cash_code	NUMBER 	primary key,
	cash		number,	
	purchase_order_no	references purchase_order(purchase_order_no),
	sales_order_no		references sales_order(sales_order_no)
)

update emp set password = '1234' where emp_no = '21-00001';

select * from customer where customer_no = 'C0001' and del = 'n';
select * from v_hr_2 where emp_no = '21-00001';

--topN을 위한 뷰
create or replace view v_hr
as
	select e1.rn, e1.emp_no, e1.emp_name, d.dept_name, e1.emp_tel, e1.emp_email 
	from (select rownum rn, e.* from emp e) e1 
	join dept d
	on e1.dept_no = d.dept_no 
	order by emp_no
with read only;

truncate table EMP;
truncate table dept;
truncate table seller;
truncate table purchase_order;
truncate table purchase_order_detail;
truncate table product;
truncate table sales_order_detail;
truncate table sales_order;
truncate table customer;
truncate table cash;

insert into emp values ('21-00001', 50, '1234', '이종민', 'fjswhd93@gmail.com', '10358','고양시', '덕양구', '010-9052-1980', to_date('210502', 'YYMMDD'), 'n');
