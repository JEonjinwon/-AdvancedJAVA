<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	MemberVO memVO = (MemberVO)request.getAttribute("memVO");
	
	//lineSeparator를 이용하여 <br>태그로 변경함.
	 String memAddr = memVO.getMemAddr().replaceAll(System.lineSeparator(), "<br>");
%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<td>ID:</td>
		<td><%=memVO.getMemId() %></td>
	</tr>
	<tr>
		<td>NAME:</td>
		<td><%=memVO.getMemName() %></td>
	</tr>
	<tr>
		<td>TEL:</td>
		<td><%=memVO.getMemTel() %></td>
	</tr>
	<tr>
		<td>ADDR:</td>
		<td><%=memVO.getMemId() %></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="<%=request.getContextPath() %>/selectAllMember">[회원 목록 조회]</a>
			<a href="<%=request.getContextPath() %>/selectAllMember">[회원 정보 추가]</a>
			<a href="<%=request.getContextPath() %>/selectAllMember">[회원 정보 삭제]</a>
	</tr>





</table>
</body>
</html>