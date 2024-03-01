package servlet;

import db.DBComment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

@WebServlet("/comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("comment");
        Long newsId = Long.parseLong(req.getParameter("newsId"));

        User user = (User) req.getSession().getAttribute("currentUser");
        DBComment.addComment(comment, user.getId(), newsId);
        resp.sendRedirect("/news-details?id=" + newsId);
    }
}