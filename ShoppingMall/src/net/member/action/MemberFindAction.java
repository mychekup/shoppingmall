package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// id, pass 찾기
public class MemberFindAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		MemberDAO memberdao = new MemberDAO();
		
		MemberBean member = new MemberBean();
		
		// 클라이언트로 부터 전송받은 이름과 주민번호를 받는 부분
		String name = request.getParameter("MEMBER_NAME");
		String jumin1 = request.getParameter("MEMBER_JUMIN1");
		String jumin2 = request.getParameter("MEMBER_JUMIN2");
		
		// 이름, 주민번호를 파라미터로 전송하면서 해당 이름과 주민번호에 대한 아이디 값과 패스워드 값을 
		// MemberBean 타입으로 리턴
		member = memberdao.findId(name, jumin1, jumin2);
		
		// 해당하는 이름과 주민번호에 대해 아이디와 패스워드를 찾았으면 각 값을 request 영역에 속성으로 공유
		// member_find_ok.jsp 페이지로 포워딩
		if(member != null){
			request.setAttribute("id", member.getMEMBER_ID());
			request.setAttribute("passwd", member.getMEMBER_PW());
			
			forward.setPath("./member/member_find_ok.jsp");
		}
		
		// 해당이름과 주민번호에 대해 아이디와 패스워드를 얻어내지 못헀으면, "입력한 정보 불일치" 자바스크립트
		// 이전 페이지로 이동
		else{
			response.setContentType("text=html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('입력한 정보가 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			forward = null;
		}
		
		return forward;
	}

}
