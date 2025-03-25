<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%
//String data[] = new String[]{"hong","kim","park"};
//request.setAttribute("data",data);
//request.setAttribute("data",data); -> ${data}를 사용할 수 있음 
ArrayList<String> data = new ArrayList();
data.add("hong1");
data.add("kim2");
data.add("pack3");
request.setAttribute("data",data);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초3(반복문-foreach)</title>
</head>
<body>
<table border="1">
<tr>
<!-- forEach :JSTL 유일하게 배열 및 반복문으로 사용하는 속성
begin :시작값
end: 종료값
 -->
 <%-- foredch 기본형태 구조
<cr:forEach var="z" begin="1" end="5">
<td>${z}</td>
</cr:forEach>
--%>
<%-- items :배열값을 받는 속성=>${data}setAtturibute의 값,<%=data%> : jsp 변수명
배열값을 사용하여 출력하는 역할 begin="0"으로 시작해야 함(배열번호는 0~)
--%>
<cr:forEach var="z" items="<%=data%>" begin="0" end="3">
<cr:if test="${z !='kim2'}">
<td>${z}</td>
</cr:if>

</cr:forEach>
</tr>

</table>


</body>
</html>