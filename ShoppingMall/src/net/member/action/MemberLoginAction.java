package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		MemberDAO memberdao = new MemberDAO();
		
		// 클라이언트로 부터 넘겨받는 아이디와 패스워드를 받아준다.
		String id = request.getParameter("MEMBER_ID");
		String pass = request.getParameter("MEMBER_PW");
		
		// 클라이언트에서 받은 아이디와 패스워드를 파라미터로 전송함과 동시에 DAO클래스의 userCheck메서드를 호출하여 인증
		int check = memberdao.userCheck(id, pass);
		
		// 로그인이 성공한 경우 1이 반환되므로 로그인이 성공했을 경우 실행되는 영역을 정의
		if(check == 1){
			// 로그인이 성공했을 경우 세션 영역에 로그인된 사용자의 아이디 값을 id라는 속성명으로 지정
			session.setAttribute("id", id);
			
			// 관리자면 포워딩될 페이지를 관리자 영역의 상품 목록 보기 페이지로 설정
			// 일반 회원일 경우 쇼핑 상품 리스트 페이지로 설정하고 포워딩.
			if(memberdao.isAdmin(id)){						
				forward.setRedirect(true);
				forward.setPath("./GoodsList.ag");
				return forward;
			}	
			else{
				forward.setRedirect(true);
				forward.setPath("./GoodsList.go?item=new_item");
				return forward;
				}
		}
		
		// 인증결과가 0이 리턴된다면 자바 스크립트를 이용해 비밀번호가 일치 않는다는 경고차를 띄우고 로그인 화면으로 돌아간다.
		else if(check == 0){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("history.go(-1);");			
			out.println("</script>");
			out.close();
		}
		// 인증결과가 -1이 리턴되었다면 로그인 화면에서 입력한 아이디를 가지고 있는 회원자체가 존재하지 않는 경우
		else{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.');");
			out.println("history.go(-1);");			
			out.println("</script>");
			out.close();
		}
		
		// 상단 조건에 각각의 경우 자바 스크립트로 이미 포워딩을 실행했으므로 FrontController에서 따로 포워딩 작업을 해줄 필요가 없으므로 
		// null 값을 리턴
		return null;
	}

}
