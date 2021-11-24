package service.sales;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

import model.Hr;
import dao.HrDao;
import model.Customer;
import dao.SalesDao;

public class CustomerUpdateForm implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		//유효한 접근인지 확인 
		if(request.getHeader("referer") == null || !request.getHeader("referer").contains("sales/customerList.do")) {
			request.getSession().invalidate();
			return "/login/loginForm.do";
		}
		
		String customer_no = request.getParameter("customer_no");
		
		Customer customer = SalesDao.getInstance().selectCustomer(customer_no);
		Hr hr = HrDao.getInstance().selectHr(customer.getEmp_no());		
		
		request.setAttribute("customer", customer);
		request.setAttribute("Hr", hr);
		
		return "/view/sales/customerUpdateForm.jsp";
	}

}
