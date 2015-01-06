package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// ȸ�� ���� ���� ȭ���� �����ֱ� ���� ����Ǵ� Ŭ����
public class MemberModifyAction_1 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		// �α����� ���� ���� ����, ���� ������ id��� �̸��� �Ӽ� ���� �������� ������ �α��� ȭ������ ���ư��� ó��
			ActionForward  forward = new ActionForward();
			HttpSession session = request.getSession(true);
			String id = (String) session.getAttribute("id");
			
			if(id==null){
				forward.setRedirect(true);
				forward.setPath("./MemberLogin.me");
				return forward;
			}
			
			MemberDAO memberdao = new MemberDAO();
			
			// ���� �����Ǿ� �ִ� ȸ���� ���̵� �Ķ���ͷ� �����ϸ鼭 getMember�޼��带 ȣ���� �ش� ȸ����
			// ������ MemberBean ��ü ���·� ���´�.
			MemberBean dto = memberdao.getMember(id);			
			request.setAttribute("member", dto);
			
			// ���� MemberBean ��ü�� request ������ member��� �Ӽ������� ����, ȸ�� ���� ���� ��������
			// member_info.jsp�� �������� URL�� �����ϴ� �κ�
			forward.setPath("./member/member_info.jsp");
			return forward;		
	
	}
	
}
