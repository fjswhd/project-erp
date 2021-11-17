package service.hr;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command;

public class HrCheckCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 1;
		request.getParameter("password");
		//현재 세션에 등록된 사원의 비밀번호와 일치하는지 확인
		
		
		
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().println(result);
		} catch (IOException e) {
			
		}
		return null;
	}

}
