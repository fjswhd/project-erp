package service.hr;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

import model.hr.Emp;
import model.hr.HrDao;

public class HrInsertCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Emp emp = new Emp();
		emp.setEmp_no(request.getParameter("emp_no"));
		emp.setEmp_name(request.getParameter("emp_name"));
		emp.setDept_no(request.getParameter("dept_no"));
		emp.setEmp_address(request.getParameter("emp_address"));
		emp.setEmp_tel(request.getParameter("emp_tel"));
		emp.setEmp_email(request.getParameter("emp_email"));
		emp.setHiredate(Date.valueOf(request.getParameter("hiredate")));   
		
		int result = HrDao.getInstance().insertEmp(emp);
		
		request.setAttribute("result", result);
		
		return "/hr/insertResult.jsp";
	}

}


