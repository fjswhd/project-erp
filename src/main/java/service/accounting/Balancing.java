package service.accounting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

public class Balancing implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "/view/accounting/balancing.jsp";
	}

}
