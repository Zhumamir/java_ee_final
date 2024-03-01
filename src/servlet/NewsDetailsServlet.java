package servlet;

import db.DBComment;
import db.DBNews;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Comment;
import model.News;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/news-details")
public class NewsDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("/auth");
            return;
        }
        Long id = Long.parseLong(req.getParameter("id"));
        News news = DBNews.getNewsById(id);
        req.setAttribute("news", news);

        List<Comment> comments = DBComment.getCommentsByNewsId(id);
        req.setAttribute("commenty", comments);
        req.getRequestDispatcher("newsDetails.jsp").forward(req, resp);
    }
}