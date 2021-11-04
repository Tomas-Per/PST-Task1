<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="common/navigation.jspf"%>
<mvc:resources mapping="/resources/**" location="/resources/static/" />

<html>
<head>
    <title>View Veiksmai</title>
    <link href="/css/common.css" rel="stylesheet" type="text/css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Veiksmas</th>
        <th>Vartotojo ID</th>
        <th>Vartotojo vardas</th>
        <th>Data</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${veiksmai}" var="veiksmas" varStatus="loop">
        <tr>
            <td>${veiksmas.id}</td>
            <td>${veiksmas.veiksmas}</td>
            <td>${veiksmas.vartotojoId}</td>
            <td><c:out value="${names[loop.index]}"/></td>
            <td>${veiksmas.data}</td>
            <td><a type="button" href="/update-veiksmas/${veiksmas.id}">UPDATE</a></td>
            <td><a type="button" href="/delete-veiksmas/${veiksmas.id}">DELETE</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <a href="add-veiksmas">ADD VEIKSMAS</a>
</div>
</body>
</html>