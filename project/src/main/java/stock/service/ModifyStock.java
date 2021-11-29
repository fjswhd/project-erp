package stock.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDao;

public class ModifyStock implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		Product product = new Product();

		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String seller_no = request.getParameter("seller_no");
		String seller_name = request.getParameter("seller_name");
		int product_no = Integer.parseInt(request.getParameter("product_no"));
		String product_name = request.getParameter("product_name");
		int stock = Integer.parseInt(request.getParameter("stock"));
		int modified_stock = Integer.parseInt(request.getParameter("modified_stock"));
		String modified_memo = request.getParameter("modified_memo");
		String emp_no = request.getParameter("emp_no");

		product.setSeller_no(seller_no);
		product.setSeller_name(seller_name);
		product.setProduct_no(product_no);
		product.setProduct_name(product_name);
		product.setStock(stock);
		product.setModified_stock(modified_stock);
		product.setModified_memo(modified_memo);
		product.setEmp_no(emp_no);

		ProductDao pd = ProductDao.getInstance();
		int result = pd.insert(product);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);

		return "/stockView/modifyStock.jsp";
	}
}