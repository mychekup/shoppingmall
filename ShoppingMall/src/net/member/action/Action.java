package net.member.action;

import javax.servlet.http.*;

// ������ ���۵Ǿ� ���� ��û�� �����ϴ� Action Ŭ�������� �޼ҵ带 �����ϰ� �ִ� Action�������̽�
public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
