package ch14.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch14.dao.SellerBoard;
import ch14.dao.BoardDao;
public class UpdateForm implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		String seller_no = request.getParameter("seller_no");
		BoardDao bd = BoardDao.getInstance();
		SellerBoard board = bd.select(seller_no);
 
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		return "updateForm.jsp";
	}

}