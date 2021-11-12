package hr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

import hr.model.Emp;
import hr.model.HrDao;

public class HrListCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HrDao ed = HrDao.getInstance();
		
		List<Emp> empList = ed.selectEmpList();
		
		request.setAttribute("empList", empList);
		
		return "/hr/list.jsp";
	}

}
