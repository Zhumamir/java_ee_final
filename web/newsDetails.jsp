<%@ page import="model.Comment" %>
<%@ page import="model.News" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: AMIRZH
  Date: 02.03.2024
  Time: 0:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="header.jsp" %>
<body>
<%
  News news = (News) request.getAttribute("news");
  if (news != null) {
%>
<div class="col-8 mx-auto">
  <div class="d-flex position-relative">
    <div>
      <h3 class="mt-0"><%=news.getTitle()%>
      </h3>
      <p><%=news.getContent()%>
      </p>
    </div>
  </div>
  <hr>
  <form action="/comment" method="post">
    <input type="hidden" value="<%=news.getId()%>" name="newsId">
    <textarea name="comment" placeholder="Insert comment..." class="form-control"></textarea>
    <button type="submit" class="btn btn-primary">+ ADD COMMENT</button>
  </form>

  <%
    List<Comment> comments = (List<Comment>) request.getAttribute("commenty");
    for (Comment comment : comments) {
  %>
  <h4><%=comment.getAuthor().getFull_name()%>
  </h4>
  <p><%=comment.getComment()%>
  </p>
  <p><%=comment.getPost_date()%>
  </p>
  <%
    }
  %>
</div>
<%
  }
%>
</body>
</html>
