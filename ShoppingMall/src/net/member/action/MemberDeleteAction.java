package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

// 회원 탈퇴
public class MemberDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// 인증을 하지 않은 사용자가 회원 탈퇴 요청을 한 경우 다시 로그인 화면으로 포워딩
		HttpSession session = request.getSession();
		String id = (String) request.getAttribute("id");
		
		if(id == null){
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		
		MemberDAO memberdao = new MemberDAO();
		
		// 회원 탈퇴를 위해 사용자가 입력한 비밀번호를 전송받는다.
		String pass = request.getParameter("MEMBER_PW");
		
		try{
			// DAO클래스의 메서드를 호출해 삭제 결과를 정수 타입의 데이터로 리턴
			int check = memberdao.deleteMember(id, pass);
			
			// 해당 회원의 정보가 테이블에서 제대로 삭제되면, 세션 정보를 invalidate()메서드로 삭제,
			// 포워딩될 페이지를 탈퇴 성공 메시지를 보여주는 member_out_ok.jsp로 지정
			if(check == 1){
				session.invalidate();
				forward.setPath("./member/member_out_ok.jsp");
			}
			
			// 회원 정보가 제대로 삭제되지 않은 경우에 자바 스크립트를 사용해 '비밀번호가 일치하지 않는다'는 경고창
			else{
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 일치하지 않습니다.');");
				out.println("history.go(-1)");
				out.println("</script>");
				out.close();
				forward=null;
			}
		}
		catch(Exception err){
			err.printStackTrace();
		}
		
		return forward;
	}

}
