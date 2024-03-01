<%--
  Created by IntelliJ IDEA.
  User: AMIRZH
  Date: 01.03.2024
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="header.jsp"%>
<body>
<div class="col-6 mx-auto">
    <%
        String result = (String) session.getAttribute("result");
        if (result != null) {
            if (result.equals("errorUsername")) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Username is busy!</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
    } else if (result.equals("errorPasswords")) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Passwords are not same!</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
    } else {
    %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <strong>Account created is successfully!</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
            }
        }
    %>
    <form action="/signup" method="post">
        <div class="mb-3">
            <label for="full_name" class="form-label">Full Name</label>
            <input name="full_name" type="text" class="form-control" id="full_name">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input name="email" type="text" class="form-control" id="email">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input name="password" type="password" class="form-control" id="exampleInputPassword1">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword2" class="form-label">Repeat Password</label>
            <input name="rePassword" type="password" class="form-control" id="exampleInputPassword2">
        </div>
        <button type="submit" class="btn btn-primary">CREATE ACCOUNT</button>
    </form>
</div>
</body>
</html>