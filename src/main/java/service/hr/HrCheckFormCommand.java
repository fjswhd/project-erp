package service.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

import model.hr.Hr;

public class HrCheckFormCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "/hr/checkForm.jsp";
	}

}
