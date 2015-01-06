package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

// ȸ�� Ż��
public class MemberDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// ������ ���� ���� ����ڰ� ȸ�� Ż�� ��û�� �� ��� �ٽ� �α��� ȭ������ ������
		HttpSession session = request.getSession();
		String id = (String) request.getAttribute("id");
		
		if(id == null){
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		
		MemberDAO memberdao = new MemberDAO();
		
		// ȸ�� Ż�� ���� ����ڰ� �Է��� ��й�ȣ�� ���۹޴´�.
		String pass = request.getParameter("MEMBER_PW");
		
		try{
			// DAOŬ������ �޼��带 ȣ���� ���� ����� ���� Ÿ���� �����ͷ� ����
			int check = memberdao.deleteMember(id, pass);
			
			// �ش� ȸ���� ������ ���̺��� ����� �����Ǹ�, ���� ������ invalidate()�޼���� ����,
			// �������� �������� Ż�� ���� �޽����� �����ִ� member_out_ok.jsp�� ����
			if(check == 1){
				session.invalidate();
				forward.setPath("./member/member_out_ok.jsp");
			}
			
			// ȸ�� ������ ����� �������� ���� ��쿡 �ڹ� ��ũ��Ʈ�� ����� '��й�ȣ�� ��ġ���� �ʴ´�'�� ���â
			else{
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.');");
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
