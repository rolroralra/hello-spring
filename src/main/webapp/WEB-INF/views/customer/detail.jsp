<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Customer Detail</title>
</head>
<body>
	<h2>Customer Detail</h2>
	<dl>
		<dt> URI Template Variable ID </dt>
		<dd><c:out value="${customerId }"/></dd>
		<dt> ID </dt>
		<dd><c:out value="${customer.id }"/></dd>
		<dt> Name </dt>
		<dd><c:out value="${customer.name }"/></dd>
		<dt> Address </dt>
		<dd><c:out value="${customer.address }"/></dd>
		<dt> Email Address </dt>
		<dd><c:out value="${customer.emailAddress }"/></dd>
	</dl>
	<br/>
	
	<c:url value="/customer/list" var="url"/>
	<a href="${url }">목록</a>
</body>
</html>