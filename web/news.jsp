<%@ page import="model.News" %>
<%@ page import="java.util.List" %><%--
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
<div class="col-8 mx-auto">
    <form action="/news" method="post">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input name="title" type="text" class="form-control" id="title">
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">Content</label>
            <input name="content" type="text" class="form-control" id="content">
        </div>
        <div class="mb-3">
            <label for="category" class="form-label">Category</label>
            <select name="category" class="form-control" id="category">
                <option value="1">1</option>
            </select>
        </div>

        <button class="btn btn-success">+ADD NEWS</button>
    </form>
    <table class="table table-striped">
        <thead>
        <th>ID</th>
        <th>TITLE</th>
        <th>POSTED AT</th>
        <th>DETAILS</th>
        </thead>
        <tbody>
        <%
            List<News> newsMore = (List<News>) request.getAttribute("news");
            for (News news : newsMore) {
        %>
        <tr>
            <td><%=news.getId()%></td>
            <td><%=news.getTitle()%></td>
            <td><%=news.getPost_date()%></td>
            <td><a class="btn btn-dark" href="/news-details?id=<%=news.getId()%>">DETAILS</a></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>