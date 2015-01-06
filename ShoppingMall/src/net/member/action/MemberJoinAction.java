package net.member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// ȸ������ ��û�� ���۵Ǿ��� �� ����Ǵ� MemberJoinAction Ŭ����
public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		MemberDAO memberdao = new MemberDAO();
		MemberBean dto = new MemberBean();
		
		ActionForward forward = null;
		
		// �Ķ���ͷ� ���۵Ǿ� ���� �߰��� ȸ���� ������ MemberBean ��ü�� �Ӽ� ������ �Ҵ��ϴ� �κ�
		dto.setMEMBER_ID(request.getParameter("MEMBER_ID"));
		dto.setMEMBER_PW(request.getParameter("MEMBER_PW"));
		dto.setMEMBER_NAME(request.getParameter("MEMBER_NAME"));
		
		dto.setMEMBER_JUMIN1(Integer.parseInt(request.getParameter("MEMBER_JUMIN1")));
		dto.setMEMBER_JUMIN2(Integer.parseInt(request.getParameter("MEMBER_JUMIN2")));
		
		// �̸��� ���� �� �� �Ѥ�
		// �̸��� ���� �� �κ����� ���۵Ǿ� ���� ���� �ϳ��� ���ڿ��� ���ļ� �Ӽ� ���� �����Ѵ�.
		dto.setMEMBER_EMAIL(request.getParameter("MEMBER_EMAIL1") + "@" + request.getParameter("MEMBER_EMAIL2") );
		dto.setMEMBER_EMAIL_GET(request.getParameter("MEMBER_EMAIL_GET"));
		
		dto.setMEMBER_MOBILE(request.getParameter("MEMBER_MOBILE"));
		dto.setMEMBER_PHONE(request.getParameter("MEMBER_PHONE"));
		
		// �����ȣ ���� �� ���� ���ڿ��� �ϳ��� ���ڿ��� ���ļ� �����Ѵ�.
		dto.setMEMBER_ZIPCODE(request.getParameter("MEMBER_ZIPCODE1") + "-" + request.getParameter("MEMBER_ZIPCODE2"));
		
		dto.setMEMBER_ADDR1(request.getParameter("MEMBER_ADDR1"));
		dto.setMEMBER_ADDR2(request.getParameter("MEMBER_ADDR2"));
		
		// ȸ���� �����ϸ� �����ڰ� �ƴ� �Ϲ� ȸ������ ó��
		dto.setMEMBER_ADMIN(0);
		
		// ȸ�� ���������� ȸ������ �� ȸ���� �Է��ϴ� ���� �ƴ϶� ���� �󿡼� �޼��带 �̿��� �������� ���� �� ���� ����
		dto.setMEMBER_JOIN_DATE(new Timestamp(System.currentTimeMillis()));
		
		// DAOŬ������ insertMember�޼��带 ȣ���Ͽ� ���� �߰��� ȸ�� ������ ���̺� ����
		memberdao.insertMember(dto); 
		
		// ���ܸ� �߻���Ű�� �ʰ� ȸ�� ���̺� ���ο� ȸ���� ������ ����� �߰��Ǹ� �ڹ� ��ũ��Ʈ�� �̿��ؼ� ���� �޽����� ����ϰ� �α���
		// ��û�� �Ѵ�.
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('ȸ������ �Ǿ����ϴ�!');");
		out.println("location.href='./MemberLogin.me';");
		out.println("</script>");
		out.close();		
		
		return forward;
	}

}
