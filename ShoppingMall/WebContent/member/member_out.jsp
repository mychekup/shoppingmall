<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Shopping</title>

</head>
<body>
<table width="960" cellspacing="0" cellpadding="0" border="0" align="center">
	<tr>
	<td colspan="2">
	<p align="center">
	<form action="./MemberDeleteAction.me" method="post">
		<table border="1" width="380" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center" colspan="2">
					<font size="4"><b>회원탈퇴</b></font>
				</td>
			</tr>
			
			<tr>
				<td align="center" height="35" width="125">
				<font size="2">비밀번호</font></td>
				<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="password" name="MEMBER_PW"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2" height="35">
					<input type="submit" value="회원 탈퇴"/>
					<input type="reset" value="취소"/>
				</td>
			</tr>
		</table>
	
	</form>

</table>
</body>
</html>