package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// id, pass ã��
public class MemberFindAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		MemberDAO memberdao = new MemberDAO();
		
		MemberBean member = new MemberBean();
		
		// Ŭ���̾�Ʈ�� ���� ���۹��� �̸��� �ֹι�ȣ�� �޴� �κ�
		String name = request.getParameter("MEMBER_NAME");
		String jumin1 = request.getParameter("MEMBER_JUMIN1");
		String jumin2 = request.getParameter("MEMBER_JUMIN2");
		
		// �̸�, �ֹι�ȣ�� �Ķ���ͷ� �����ϸ鼭 �ش� �̸��� �ֹι�ȣ�� ���� ���̵� ���� �н����� ���� 
		// MemberBean Ÿ������ ����
		member = memberdao.findId(name, jumin1, jumin2);
		
		// �ش��ϴ� �̸��� �ֹι�ȣ�� ���� ���̵�� �н����带 ã������ �� ���� request ������ �Ӽ����� ����
		// member_find_ok.jsp �������� ������
		if(member != null){
			request.setAttribute("id", member.getMEMBER_ID());
			request.setAttribute("passwd", member.getMEMBER_PW());
			
			forward.setPath("./member/member_find_ok.jsp");
		}
		
		// �ش��̸��� �ֹι�ȣ�� ���� ���̵�� �н����带 ���� ��������, "�Է��� ���� ����ġ" �ڹٽ�ũ��Ʈ
		// ���� �������� �̵�
		else{
			response.setContentType("text=html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�Է��� ������ ��ġ���� �ʽ��ϴ�.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			forward = null;
		}
		
		return forward;
	}

}
