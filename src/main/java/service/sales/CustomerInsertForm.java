package service.sales;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

import dao.SalesDao;

public class CustomerInsertForm implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String customerCount = "000" + (SalesDao.getInstance().getTotalCustomer() + 1);
		customerCount = customerCount.substring(customerCount.length() - 4);
		
		String customer_no = "C" + customerCount;
		
		request.setAttribute("customer_no", customer_no);
		
		return "/view/sales/customerInsertForm.jsp";
	}

}
