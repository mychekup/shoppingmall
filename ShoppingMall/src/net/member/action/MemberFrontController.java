package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 회원에 관한 모든 요청을 제어하는 컨트롤러 클래스
public class MemberFrontController extends HttpServlet{

	public void service(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		response.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String command = RequestURI.substring(ContextPath.length());
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/MemberLogin.me")){
			System.out.println("MemberLogin.me");
			forward = new ActionForward();
			forward.setPath("./member/member_login.jsp");
		}
		else if(command.equals("/MemberJoin.me")){
			System.out.println("MemberJoin.me");
			forward = new ActionForward();
			forward.setPath("./member/member_join.jsp");
		}
		else if(command.equals("/MemberFind.me")){
			System.out.println("MemberFind.me");
			forward = new ActionForward();
			forward.setPath("./member/member_find.jsp");
		}
		else if(command.equals("/MemberOut.me")){
			System.out.println("MemberOut.me");
			forward = new ActionForward();
			forward.setPath("./member/member_out.jsp");
		}
		else if(command.equals("/Zipcode.me")){
			System.out.println("Zipcode.me");
			forward = new ActionForward();
			forward.setPath("./member/member_zipcode.jsp");			
		}
		else if(command.equals("/MemberLoginAction.me")){
			System.out.println("MemberLoginAction.me");
			action = new MemberLoginAction();
			try{
				forward = action.execute(request, response);
			}
			catch(Exception err){
				err.printStackTrace();
			}
		}
		else if(command.equals("/MemberJoinAction.me")){
			System.out.println("MemberJoinAction.me");
			action = new MemberJoinAction();
			try{
				forward = action.execute(request, response);
			}
			catch(Exception err){
				err.printStackTrace();
			}
		}
		else if(command.equals("/MemberModifyAction_1.me")){
			System.out.println("MemberModifyAction_1.me");
			action = new MemberModifyAction_1();
			try{
				forward = action.execute(request, response);
			}
			catch(Exception err){
				err.printStackTrace();
			}
		}
		else if(command.equals("/MemberModifyAction_2.me")){
			System.out.println("MemberModifyAction_2.me");
			action = new MemberModifyAction_2();
			try{
				forward = action.execute(request, response);
			}
			catch(Exception err){
				err.printStackTrace();
			}
		}
		else if(command.equals("/MemberDeleteAction.me")){
			System.out.println("MemberDeleteAction.me");
			action = new MemberDeleteAction();
			try{
				forward = action.execute(request, response);
			}
			catch(Exception err){
				err.printStackTrace();
			}
		}
		else if(command.equals("/MemberIDCheckAction.me")){
			System.out.println("MemberIDCheckAction.me");
			action = new MemberIDCheckAction();
			try{
				forward = action.execute(request, response);				
			}
			catch(Exception err){
				err.printStackTrace();
			}
		}
		else if(command.equals("/MemberFindAction.me")){
			System.out.println("MemberFindAction.me");
			action = new MemberFindAction();
			try{
				forward = action.execute(request, response);
			}
			catch(Exception err){
				err.printStackTrace();
			}
		}
		else if(command.equals("/MemberZipcodeAction.me")){
			System.out.println("MemberZipcodeAction.me");
			action = new MemberZipcodeAction();
			try{
				forward = action.execute(request, response);				
			}
			catch(Exception err){
				err.printStackTrace();
			}
		}
		else if(command.equals("/GoodsList.go")){
			System.out.println("GoodsList.go");
			forward = new ActionForward();
			forward.setPath("./goods/goods.jsp");
		}
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}
			else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				System.out.println("오나");
				dispatcher.forward(request,response);
			}
		}
		
	}
	
}
