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
		
		// Ŭ���̾�Ʈ�� ���� �Ѱܹ޴� ���̵�� �н����带 �޾��ش�.
		String id = request.getParameter("MEMBER_ID");
		String pass = request.getParameter("MEMBER_PW");
		
		// Ŭ���̾�Ʈ���� ���� ���̵�� �н����带 �Ķ���ͷ� �����԰� ���ÿ� DAOŬ������ userCheck�޼��带 ȣ���Ͽ� ����
		int check = memberdao.userCheck(id, pass);
		
		// �α����� ������ ��� 1�� ��ȯ�ǹǷ� �α����� �������� ��� ����Ǵ� ������ ����
		if(check == 1){
			// �α����� �������� ��� ���� ������ �α��ε� ������� ���̵� ���� id��� �Ӽ������� ����
			session.setAttribute("id", id);
			
			// �����ڸ� �������� �������� ������ ������ ��ǰ ��� ���� �������� ����
			// �Ϲ� ȸ���� ��� ���� ��ǰ ����Ʈ �������� �����ϰ� ������.
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
		
		// ��������� 0�� ���ϵȴٸ� �ڹ� ��ũ��Ʈ�� �̿��� ��й�ȣ�� ��ġ �ʴ´ٴ� ������� ���� �α��� ȭ������ ���ư���.
		else if(check == 0){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.');");
			out.println("history.go(-1);");			
			out.println("</script>");
			out.close();
		}
		// ��������� -1�� ���ϵǾ��ٸ� �α��� ȭ�鿡�� �Է��� ���̵� ������ �ִ� ȸ����ü�� �������� �ʴ� ���
		else{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���̵� �������� �ʽ��ϴ�.');");
			out.println("history.go(-1);");			
			out.println("</script>");
			out.close();
		}
		
		// ��� ���ǿ� ������ ��� �ڹ� ��ũ��Ʈ�� �̹� �������� ���������Ƿ� FrontController���� ���� ������ �۾��� ���� �ʿ䰡 �����Ƿ� 
		// null ���� ����
		return null;
	}

}
