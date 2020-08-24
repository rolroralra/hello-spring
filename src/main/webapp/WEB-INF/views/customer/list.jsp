<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Customer List</title>
</head>
<body>
	<h2>Customer List</h2>
	
	<c:if test="${editedCustomer != null }">
		<dl>
			<dt> 이름 </dt>
			<dd><c:out value="${editedCustomer.name }"/></dd>
			<dt> 주소 </dt>
			<dd><c:out value="${editedCustomer.address }"/></dd>
			<dt> 이메일주소 </dt>
			<dd><c:out value="${editedCustomer.emailAddress }"/></dd>
		</dl>
	</c:if>
	
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email Address</th>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${customers }" var="customer">
				<tr>
					<td><c:out value="${customer.id }"/></td>
					<td><c:out value="${customer.name }"/></td>
					<td><c:out value="${customer.address }"/></td>
					<td><c:out value="${customer.emailAddress }"/></td>
					<td>
						<c:url value="/customer/${customer.id }" var="url"/>
						<a href="${url }"> View </a>
						<c:url value="/customer/${customer.id }/edit" var="url"/>
						<a href="${url }"> Edit </a>
					</td>
				</tr>
			</c:forEach>	
		</tbody>
		
	</table>
	
</body>
</html>