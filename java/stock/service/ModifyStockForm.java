package stock.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDao;

public class ModifyStockForm implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int product_no = Integer.parseInt(request.getParameter("product_no"));
		
		ProductDao pd = ProductDao.getInstance();
		Product product = pd.select(product_no);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("product",product);
		
		return "/stockView/modifyStockForm.jsp";
	}	
}
