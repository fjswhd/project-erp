package service.inventory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

public class EnterList implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "/view/inventory/enterList.jsp";
	}

}
