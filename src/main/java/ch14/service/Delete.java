package ch14.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ch14.dao.BoardDao;
public class Delete implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		String seller_no= request.getParameter("seller_no");
		String pageNum= request.getParameter("pageNum");
		BoardDao bd = BoardDao.getInstance(); 
		int result  = bd.delete(seller_no); 
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		return "delete.jsp";
	}

}
