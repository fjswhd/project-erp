package emp.model;

import java.sql.Date;

public class Emp {
	private String 	empno;
	private String	password;
	private String 	name;
	private String 	dname;
	private String 	email;
	private String 	tel;
	private String 	address;
	private Date 	birthday;
	private Date 	hiredate;
	private String	emp_reg_auth;
	private String 	resign;
	
	public String getEmpno() {
		return empno;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getDname() {
		return dname;
	}
	public String getEmail() {
		return email;
	}
	public String getTel() {
		return tel;
	}
	public String getAddress() {
		return address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public String getEmp_reg_auth() {
		return emp_reg_auth;
	}
	public String getResign() {
		return resign;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public void setEmp_reg_auth(String emp_reg_auth) {
		this.emp_reg_auth = emp_reg_auth;
	}
	public void setResign(String resign) {
		this.resign = resign;
	}
	
}
