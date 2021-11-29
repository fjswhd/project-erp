package stock.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OutProduct;
import model.OutProductDao;

public class OutProductListSearch implements CommandProcess {
	
		public String requestPro(HttpServletRequest request, HttpServletResponse response) {
			final int ROW_PER_PAGE = 10; // 한페이지에 10개씩
			final int PAGE_PER_BLOCK = 10; // 한블럭에 10페이지
			
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals(""))
				pageNum = "1";
			
			int currentPage = Integer.parseInt(pageNum);
			
			//현재날짜와 1년전 날짜 
			Calendar day = Calendar.getInstance();
			String today = new SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
			day.add(Calendar.YEAR, -1);
			String beforeYear = new SimpleDateFormat("yyyy-MM-dd").format(day.getTime());
			
			//기간설정값 가져오기
			String s_date = request.getParameter("s_date");
			String e_date = request.getParameter("e_date");
			//기간을 설정하지 않고 (시작, 끝 둘다 혹은 하나만 설정시 ) 검색한 경우
			if (s_date.equals("") && e_date.equals("")){
				s_date = beforeYear;
				e_date = today;
			}else if (s_date.equals("")) {
				s_date = beforeYear;
			}else if (e_date.equals("")) {
				e_date = today;
			}
			//검색카테고리, 검색어 가져오기
			String searchField = request.getParameter("searchField");
			String keyword = request.getParameter("keyword");
			
			OutProductDao opd = OutProductDao.getInstance();
			
			int total2 = opd.getTotal2(s_date,e_date,searchField, keyword);
			
			int startRow = (currentPage - 1) * ROW_PER_PAGE + 1; // 시작번호 (페이지번호 - 1) * 페이지당 갯수+ 1
			int endRow = startRow + ROW_PER_PAGE - 1; // 끝번호 시작번호 + 페이지당개수 - 1
			
			List<OutProduct> getSearch = opd.getSearch(startRow, endRow, s_date, e_date, searchField, keyword);
			int totalPage = (int) Math.ceil((double) total2 / ROW_PER_PAGE); // 총 페이지 수
			// 시작페이지 현재페이지 - (현재페이지 - 1)%10
			int startPage = currentPage - (currentPage - 1) % PAGE_PER_BLOCK;
			// 끝페이지 시작페이지 + 블록당페이지 수 - 1
			int endPage = startPage + PAGE_PER_BLOCK - 1;
			// 총 페이지보다 큰 endPage나올 수 없다
			if (endPage > totalPage)
				endPage = totalPage;
			
			String[] searchEn = {"customer_no", "customer_name", "product_no", "product_name"};
			String[] searchKr = {"업체코드", "업체명", "상품코드", "상품명"};
			String searchFiledKr ="";
			for (int i =0 ; i < searchEn.length;i++) {
				if (searchField.equals(searchEn[i])) {
					searchFiledKr = searchKr[i];
					break;
				}
			}

			// JSP에서 jstl로 사용하는 변수와 값을 전달
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("PAGE_PER_BLOCK", PAGE_PER_BLOCK);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
			request.setAttribute("searchField", searchField);
			request.setAttribute("searchFiledKr", searchFiledKr);
			request.setAttribute("keyword", keyword);
			request.setAttribute("getSearch", getSearch);
			
			return "/stockView/outProductListSearch.jsp";
		}

}
