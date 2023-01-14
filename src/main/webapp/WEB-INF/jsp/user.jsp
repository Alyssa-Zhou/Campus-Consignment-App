<%--
  Created by IntelliJ IDEA.
  User: 81905
  Date: 2022/11/6
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>age</td>
        <td>email</td>
    </tr>
    <c:forEach items="${user}" var="u" varStatus="st">
        <tr>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.phone}</td>
            <td>${u.email}</td>
        </tr>
    </c:forEach>
</table>

