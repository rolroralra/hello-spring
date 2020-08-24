<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>Edit Review</title>
</head>
<body>
	<h1> 확인화면 </h1>
	<form action="" method="post">
		<dl>
			<dt> 이름 </dt>
			<dd><c:out value="${editCustomer.name }"/></dd>
			<dt> 주소 </dt>
			<dd><c:out value="${editCustomer.address }"/></dd>
			<dt> 이메일주소 </dt>
			<dd><c:out value="${editCustomer.emailAddress }"/></dd>
		</dl>
		
		<button type="submit" name="_event_confirmed"> 갱신 </button>
		<button type="submit" name="_event_revise"> 재입력 </button>
	</form>
</body>
</html>