package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// 회원 정보 수정 화면을 보여주기 위해 실행되는 클래스
public class MemberModifyAction_1 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		// 로그인이 되지 않은 상태, 세션 영역에 id라는 이름의 속성 값이 존재하지 않으면 로그인 화면으로 돌아가게 처리
			ActionForward  forward = new ActionForward();
			HttpSession session = request.getSession(true);
			String id = (String) session.getAttribute("id");
			
			if(id==null){
				forward.setRedirect(true);
				forward.setPath("./MemberLogin.me");
				return forward;
			}
			
			MemberDAO memberdao = new MemberDAO();
			
			// 현재 인증되어 있는 회원의 아이디를 파라미터로 전송하면서 getMember메서드를 호출해 해당 회원의
			// 정보를 MemberBean 객체 형태로 얻어온다.
			MemberBean dto = memberdao.getMember(id);			
			request.setAttribute("member", dto);
			
			// 얻어온 MemberBean 객체를 request 영역에 member라는 속성명으로 저장, 회원 정보 수정 페이지인
			// member_info.jsp로 포워딩될 URL을 설정하는 부분
			forward.setPath("./member/member_info.jsp");
			return forward;		
	
	}
	
}
