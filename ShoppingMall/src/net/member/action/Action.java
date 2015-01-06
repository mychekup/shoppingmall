package net.member.action;

import javax.servlet.http.*;

// 서블릿에 전송되어 오는 요청을 제어하는 Action 클래스들의 메소드를 정의하고 있는 Action인터페이스
public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
