package ch14.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch14.dao.SellerBoard;
import ch14.dao.BoardDao;
public class WriteForm implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		String pageNum = request.getParameter("pageNum");
		String emp_no = request.getParameter("emp_no");
		String seller_no = request.getParameter("seller_no");
		String seller_reg_num = request.getParameter("seller_reg_num");
		String seller_name = request.getParameter("seller_name");
		String seller_tel = request.getParameter("seller_tel");
		String seller_address = request.getParameter("seller_address");
		String seller_email = request.getParameter("seller_email");
		String seller_memo = request.getParameter("seller_memo");
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("emp_no", emp_no);
//		해야됨!!!!!!!!!!!! sellerList에서 데이터에 저장된 담당자가 아니라,
//		현재 로그인한 세션의 아이디가 들어가야함. 
		request.setAttribute("seller_no", seller_no);
		request.setAttribute("seller_reg_num", seller_reg_num);
		request.setAttribute("seller_name", seller_name);
		request.setAttribute("seller_tel", seller_tel);
		request.setAttribute("seller_address", seller_address);
		request.setAttribute("seller_email", seller_email);
		request.setAttribute("seller_memo", seller_memo);
		return "writeForm.jsp";
	}
}