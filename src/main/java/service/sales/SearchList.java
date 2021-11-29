package service.sales;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

import model.SearchOption;

public class SearchList implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		SearchOption option = new SearchOption();
		if (request.getParameter("keyword") != null && !request.getParameter("keyword").trim().equals("")) {
			String keyword = "";
		}
		return null;
	}
}
