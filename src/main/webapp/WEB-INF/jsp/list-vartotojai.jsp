<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="common/navigation.jspf"%>
<mvc:resources mapping="/resources/**" location="/resources/static/" />

<html>
<head>
    <title>View Vartotojai</title>
    <link href="/css/common.css" rel="stylesheet" type="text/css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Vardas</th>
        <th>TelNr</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${vartotojai}" var="vartotojas">
        <tr>
            <td>${vartotojas.id}</td>
            <td>${vartotojas.vardas}</td>
            <td>${vartotojas.telNr}</td>
            <td><a type="button" href="/update-vartotojas/${vartotojas.id}">UPDATE</a></td>
            <td><a type="button" href="/delete-vartotojas/${vartotojas.id}">DELETE</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <a href="add-vartotojas">ADD VARTOTOJAS</a>
</div>
</body>
</html>