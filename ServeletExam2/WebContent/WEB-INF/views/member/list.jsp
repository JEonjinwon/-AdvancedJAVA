<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
List<MemberVO> memList = (List<MemberVO>)request.getAttribute("memList");

String msg = (String)request.getAttribute("msg");

%>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원목록</title>
</head>
<body>
<table border="1">
	<tr>
		<td colspan="4">
			<a href="<%=request.getContextPath()%>/insertMember">[회원등록]</a>
		</td>
	</tr>
	<tr>
		<td>ID</td>
		<td>이름</td>
		<td>전번</td>
		<td>주소</td>
	</tr>
<%
	int memSize = memList.size();
	if(memSize>0){
		for(int i=0;i<memSize;i++){
%>
		<tr>
		<td><%= memList.get(i).getMemId() %></td>
		<td><a href="ViewMember?memId=<%= memList.get(i).getMemName()%>"><%= memList.get(i).getMemName() %></a></td>
		<td><%= memList.get(i).getMemTel() %></td>
		<td><%= memList.get(i).getMemAddr() %></td>
		</tr>

<%			
			
			
		}
	}else{
%>
	<tr>
		<td colspan="4">회원 정보가 없습니다</td>
	</tr>


<%		
	}
	
%>
</table>
<%
	if(msg.equals("성공")){	//성공 메시지가 전달되면.
%>
	<script>
		alert('정상적으로 처리되었습니다.');
	</script>

<%		
	}

%>
</body>
</html>