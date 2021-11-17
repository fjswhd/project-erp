package model.hr;

import java.sql.Date;

public class Emp {
	//사번, 부서번호, 비밀번호, 이름, 이메일, 주소, 전화번호, 입사일, 퇴사여부
	private String 	emp_no;
	private String	dept_no;
	private String	password;
	private String 	emp_name;
	private String 	emp_email;
	private String 	emp_address;
	private String 	emp_tel;
	private Date 	hiredate;
	private String 	resign;
	
	public String getEmp_no() {
		return emp_no;
	}
	public String getDept_no() {
		return dept_no;
	}
	public String getPassword() {
		return password;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public String getEmp_address() {
		return emp_address;
	}
	public String getEmp_tel() {
		return emp_tel;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public String getResign() {
		return resign;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}
	public void setEmp_tel(String emp_tel) {
		this.emp_tel = emp_tel;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public void setResign(String resign) {
		this.resign = resign;
	}
	
	
}