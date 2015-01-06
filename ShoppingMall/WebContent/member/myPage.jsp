<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>
<title>Shopping</title>

</head>
<body>
<%
	String id = (String)request.getAttribute("id");
	System.out.println(id);

	if(id == null){
		response.sendRedirect("/ShoppingMall/MemberLogin.me");		
	}
	else{
		%>
	<a href="/Shopping/MemberModifyAction_1.me">회원정보수정</a>
<%
	}
%>
</body>
</html>