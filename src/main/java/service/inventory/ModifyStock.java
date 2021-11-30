package service.inventory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

import dao.ProductDao;
import model.Product;

public class ModifyStock implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		int p = Integer.parseInt(request.getParameter("p"));
		
		//product_no를 갖는 product 뽑기
		int product_no = Integer.parseInt(request.getParameter("product_no"));
		Product product = ProductDao.getInstance().selectProduct(product_no);
		
		//해당 상품의 재고 수정
		int stock = Integer.parseInt(request.getParameter("stock"));
		product.setStock(stock);
		
		//db에 반영
		//ProductDao.getInstance().updateProduct(product);
		
		int modified_stock = Integer.parseInt(request.getParameter("modified_stock"));
		String modified_memo = request.getParameter("modified_memo");
		String emp_no = request.getParameter("emp_no");

		
		return "/view/inventory/list.do?p="+p;
	}

}
