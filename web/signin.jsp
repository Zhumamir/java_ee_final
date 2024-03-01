<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: AMIRZH
  Date: 01.03.2024
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="header.jsp"%>
<body>
<div class="col-6 mx-auto">
  <%
    String status = request.getParameter("status");
    if (status != null) {
      Boolean authenticated = Boolean.valueOf(status);
      if (Boolean.FALSE.equals(authenticated)) {
  %>
  <div class="alert alert-danger alert-dismissible fade show" role="alert">
    <strong>Incorrect username or password! Try again.</strong>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  </div>
  <%
      }
    }
  %>
  <form action="/auth" method="post">
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input name="email" type="text" class="form-control" id="email">
    </div>
    <div class="mb-3">
      <label for="exampleInputPassword1" class="form-label">Password</label>
      <input name="password" type="password" class="form-control" id="exampleInputPassword1">
    </div>
    <button type="submit" class="btn btn-primary">SIGN IN</button>
  </form>
</div>
</body>
</html>