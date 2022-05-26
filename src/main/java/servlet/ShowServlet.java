package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReviewsDTO;
import model.ShowLogic;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        // セッション情報を確認する。
        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute("loginId");

        if (loginId == null) {
            // セッションがなければログイン画面に戻す。
            request.setAttribute("message", "セッションがありません。ログインしてください。");
            response.sendRedirect("login");
        } else {

            // セッション・画面の情報を変数に格納していく
            request.setAttribute("loginId", loginId);
            String strPostId = (String)request.getParameter("postId");
            int postId = Integer.parseInt(strPostId);
            
            // 詳細画面に表示するレビュー情報を取得して、値をセットしていく
            ShowLogic getPost = new ShowLogic();
            ReviewsDTO result = getPost.execute(loginId, postId);

            int intPostId = result.getPostId();
            request.setAttribute("postId", intPostId);

            String title = result.getTitle();
            request.setAttribute("title", title);

            String content = result.getContent().replaceAll("\n", "<br>");
            request.setAttribute("content", content);

            int stars = result.getStars();
            request.setAttribute("stars", stars);

            String image = result.getImage();
            request.setAttribute("image", image);

            request.setAttribute("message", "This is your post " + postId);

            // 取得したデータを表示させる詳細画面への遷移を行う。
            String view = "/WEB-INF/views/post.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
        }
    }
}