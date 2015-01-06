package net.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

// �����ȣ �˻� ��û
public class MemberZipcodeAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		
		MemberDAO memberdao = new MemberDAO();
		
		// �ش� �� �̸��� �����ϰ� �ִ� �ּҿ� �����ȣ ���� ArrayList ���·� ����
		List zipcodeList = new ArrayList();

		String searchdong = request.getParameter("dong");
		zipcodeList = memberdao.searchZipcode(searchdong);
		System.out.println("MemberZipcodeAction.java" + searchdong);
		request.setAttribute("zipcodelist", zipcodeList);
		
		forward.setPath("./member/member_zipcode.jsp");
		
		
		return forward;
	}

}
