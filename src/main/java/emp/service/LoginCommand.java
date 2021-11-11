package emp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import emp.model.EmpDao;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String empno 	= request.getParameter("empno");
		String password = request.getParameter("password");
		
		//로그인 결과표
		//  0 : 아이디 없음
		// -1 : 아이디 있음 / 비밀번호 틀림
		//  1 : 아이디 있음 / 비밀번호 맞음
		
		int result = 0;
		
		EmpDao ed = EmpDao.getInstance();
		Emp emp = ed.select(empno);
		
		if (emp == null) {
			result = 0;
		} else if (password.equals(emp.getPassword())) {
			result = 1;			
			request.getSession().setAttribute("currentEmp", emp);
		} else {
			result = -1;
		}
		
		request.setAttribute("result", result);
		
		return "/empView/loginResult.jsp";
	}

}
