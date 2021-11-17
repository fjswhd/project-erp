package service.hr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

import model.hr.Emp;
import model.hr.Hr;
import model.hr.HrDao;

public class HrListCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HrDao ed = HrDao.getInstance();
		
		List<Hr> hrList = ed.selectHrList();
		
		request.setAttribute("hrList", hrList);
		
		return "/hr/list.jsp";
	}

}
