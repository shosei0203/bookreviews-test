package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReviewsDTO;
import model.ShowLogic;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

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
            int postId = Integer.parseInt(request.getParameter("postId"));

            // 更新処理を行う対象のデータを取得する
            ShowLogic getPost = new ShowLogic();
            ReviewsDTO result = getPost.execute(loginId, postId);

            int intPostId = result.getPostId();
            request.setAttribute("postId", intPostId);

            String title = result.getTitle();
            request.setAttribute("title", title);

            String content = result.getContent();
            request.setAttribute("content", content);

            int stars = result.getStars();
            request.setAttribute("stars", stars);

            String image = result.getImage();
            request.setAttribute("image", image);

            request.setAttribute("message", "更新対象投稿ID：" + postId);

            // 次の処理画面に遷移する
            List<String> errorMessage = (ArrayList<String>) request.getAttribute("errorMessage");
            if (errorMessage == null) {
                List<String> zeroMessage = new ArrayList<String>();
                request.setAttribute("errorMessage", zeroMessage);
            } else {
                if (errorMessage.size() != 0) {
                    request.setAttribute("errorMessage", errorMessage);
                }
            }
            // 更新を行う画面に遷移する。
            String view = "/WEB-INF/views/edit.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
        }
    }
}
