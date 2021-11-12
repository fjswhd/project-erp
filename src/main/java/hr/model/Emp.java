package hr.model;

import java.sql.Date;

public class Emp {
	//사번, 부서번호, 비밀번호, 이름, 이메일, 주소, 전화번호, 입사일, 퇴사여부
	private String 	empNo;
	private Dept 	dept;
	private String	password;
	private String 	empName;
	private String 	empEmail;
	private String 	empAddress;
	private String 	empTel;
	private String 	hiredate;
	private String 	resign;
	
	public String getEmpNo() {
		return empNo;
	}
	public Dept getDept() {
		return dept;
	}
	public String getPassword() {
		return password;
	}
	public String getEmpName() {
		return empName;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public String getEmpTel() {
		return empTel;
	}
	public String getHiredate() {
		return hiredate;
	}
	public String getResign() {
		return resign;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setResign(String resign) {
		this.resign = resign;
	}
	
}
