package net.member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// 회원가입 요청이 전송되었을 때 실행되는 MemberJoinAction 클래스
public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		MemberDAO memberdao = new MemberDAO();
		MemberBean dto = new MemberBean();
		
		ActionForward forward = null;
		
		// 파라미터로 전송되어 오는 추가될 회원의 정보를 MemberBean 객체의 속성 값으로 할당하는 부분
		dto.setMEMBER_ID(request.getParameter("MEMBER_ID"));
		dto.setMEMBER_PW(request.getParameter("MEMBER_PW"));
		dto.setMEMBER_NAME(request.getParameter("MEMBER_NAME"));
		
		dto.setMEMBER_JUMIN1(Integer.parseInt(request.getParameter("MEMBER_JUMIN1")));
		dto.setMEMBER_JUMIN2(Integer.parseInt(request.getParameter("MEMBER_JUMIN2")));
		
		// 이메일 오류 날 듯 ㅡㅡ
		// 이메일 값은 두 부분으로 전송되어 오는 값을 하나의 문자열로 합쳐서 속성 값을 설정한다.
		dto.setMEMBER_EMAIL(request.getParameter("MEMBER_EMAIL1") + "@" + request.getParameter("MEMBER_EMAIL2") );
		dto.setMEMBER_EMAIL_GET(request.getParameter("MEMBER_EMAIL_GET"));
		
		dto.setMEMBER_MOBILE(request.getParameter("MEMBER_MOBILE"));
		dto.setMEMBER_PHONE(request.getParameter("MEMBER_PHONE"));
		
		// 우편번호 역시 두 개의 문자열을 하나의 문자열로 합쳐서 설정한다.
		dto.setMEMBER_ZIPCODE(request.getParameter("MEMBER_ZIPCODE1") + "-" + request.getParameter("MEMBER_ZIPCODE2"));
		
		dto.setMEMBER_ADDR1(request.getParameter("MEMBER_ADDR1"));
		dto.setMEMBER_ADDR2(request.getParameter("MEMBER_ADDR2"));
		
		// 회원이 가입하면 관리자가 아닌 일반 회원으로 처리
		dto.setMEMBER_ADMIN(0);
		
		// 회원 가입입일을 회원가입 시 회원이 입력하는 것이 아니라 서버 상에서 메서드를 이용해 가입일을 구한 후 값을 설정
		dto.setMEMBER_JOIN_DATE(new Timestamp(System.currentTimeMillis()));
		
		// DAO클래스의 insertMember메서드를 호출하여 새로 추가할 회원 정보를 테이블에 삽입
		memberdao.insertMember(dto); 
		
		// 예외를 발생시키지 않고 회원 테이블에 새로운 회원의 정보가 제대로 추가되면 자바 스크립트를 이용해서 성공 메시지를 출력하고 로그인
		// 요청을 한다.
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('회원가입 되었습니다!');");
		out.println("location.href='./MemberLogin.me';");
		out.println("</script>");
		out.close();		
		
		return forward;
	}

}
