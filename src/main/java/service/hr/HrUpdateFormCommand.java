package service.hr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

import model.hr.Dept;
import model.hr.Emp;
import model.hr.Hr;
import model.hr.HrDao;

public class HrUpdateFormCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HrDao hd = HrDao.getInstance();
		Hr hr = (Hr)request.getSession().getAttribute("Hr");
		
		Emp emp = hd.selectEmp(hr.getEmp_no());
		emp.setPassword("");
		
		List<Dept> deptList = hd.selectDeptList();
		
		request.setAttribute("emp", emp);
		request.setAttribute("deptList", deptList);
		
		return "/hr/updateForm.jsp";
	}

}
