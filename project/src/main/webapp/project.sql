select * from emp;
select * from (select s.seller_no, s.seller_name, p.product_no, p.product_name, p.cost, p.price, p.stock,emp.emp_no
from seller s, product p,purchase_order po, purchase_order_detail pod,emp emp
where s.seller_no = po.seller_no and po.purchase_order_no = pod.purchase_order_no and pod.product_no = p.product_no)
where product_no=1;