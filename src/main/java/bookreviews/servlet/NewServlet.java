package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

@WebServlet("/new")
public class NewServlet extends HttpServlet {

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
            request.setAttribute("message", "Create your post");
            request.setAttribute("personId", loginId);
            // 最大＋1の投稿IDを取得する。
            NewLogic getPostId = new NewLogic();
            ReviewsDTO postId = getPostId.execute(loginId);

            // 取得したデータを変数に格納する
            int intPostId = postId.getPostId();
            request.setAttribute("postId", intPostId);

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
            String view = "/WEB-INF/views/new.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);

        }
    }
}