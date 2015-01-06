package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;


// 회원 정보 수정 페이지에서 수정할 정보를 입력하고 정보 수정 요청을 했을 떄 입력한 정보로 회원의 정보를 수정 처리하는 클래스
public class MemberModifyAction_2 implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		// 로그인이 되지 않은 사용자는 세션 영역에 id라는 이름으로 사용자의 아이디가 저장되어있지 않을 경우
		// 로그인 화면으로 되돌아가도록 처리
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id==null){
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean dto = new MemberBean();
		
		// 회원 정보 수정 페이지에서 입력한 정보들을 MemberBean 객체의 속성 값으로 모두 할당
		dto.setMEMBER_ID(id);
		dto.setMEMBER_NAME(request.getParameter("MEMBER_NAME"));
		dto.setMEMBER_PW(request.getParameter("MEMBER_PW"));
		dto.setMEMBER_EMAIL(request.getParameter("MEMBER_EMAIL1") + "@" + 
							request.getParameter("MEMBER_EMAIL2"));
		
		dto.setMEMBER_EMAIL_GET(request.getParameter("MEMBER_EMAIL_GET"));
		dto.setMEMBER_MOBILE(request.getParameter("MEMBER_MOBILE"));
		dto.setMEMBER_PHONE(request.getParameter("MEMBER_PHONE"));
		
		dto.setMEMBER_ZIPCODE(request.getParameter("MEMBER_ZIPCODE1") + "-" + 
								request.getParameter("MEMBER_ZIPCODE2"));
		
		dto.setMEMBER_ADDR1(request.getParameter("MEMBER_ADDR1"));
		dto.setMEMBER_ADDR2(request.getParameter("MEMBER_ADDR2"));
		
		// DAO클래스의 updateMember메서드를 호출하여 회원 정보를 수정 페이지에서 입력한 정보로 수정하는 작업
		memberdao.updateMember(dto);
		
		// 정보 수정 작업이 예외 없이 완성되었으면 다시 정보 수정 페이지로 포워딩 될 URL을 설정하여 
		// 수정된 내용을 확인할 수 있도록 처리
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<scrip>");
		out.println("alert('회원정보가 수정되었습니다.');");
		out.println("</script>");
		forward.setPath("./MemberModifyAction_1.me");
		
		
		return forward;		
	}
}
