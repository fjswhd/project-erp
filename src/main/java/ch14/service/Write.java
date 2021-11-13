package ch14.service;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch14.dao.SellerBoard;
import ch14.dao.BoardDao;
public class Write implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		try {	request.setCharacterEncoding("utf-8");	} catch (UnsupportedEncodingException e) {	}
		SellerBoard board = new SellerBoard();
		String pageNum = request.getParameter("pageNum");			
		String emp_no = request.getParameter("emp_no");
		String seller_reg_num = request.getParameter("seller_reg_num");
		String seller_name = request.getParameter("seller_name");
		String seller_tel = request.getParameter("seller_tel");
		String seller_address = request.getParameter("seller_address");
		String seller_email = request.getParameter("seller_email");
		String seller_memo = request.getParameter("seller_memo");

		board.setEmp_no(emp_no);						
		board.setSeller_reg_num(seller_reg_num);		board.setSeller_name(seller_name);
		board.setSeller_tel(seller_tel);				board.setSeller_address(seller_address);
		board.setSeller_email(seller_email);			board.setSeller_memo(seller_memo);
		
		BoardDao bd = BoardDao.getInstance();
		bd.setSellerNo(board);
		int result = bd.insert(board);
		request.setAttribute("result", result);
		request.setAttribute("pageNum", pageNum);
		return "write.jsp";
	}
}