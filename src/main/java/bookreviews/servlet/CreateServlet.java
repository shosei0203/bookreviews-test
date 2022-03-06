package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {

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
            String title = (String) request.getParameter("title");
            String content = (String) request.getParameter("content");
            String strStars = (String) request.getParameter("stars");
            String image = (String) request.getParameter("image");

            // 入力値ﾁｪｯｸを行う。
            List<String> errorMsgResult = new ArrayList<String>();
            CheckPostLogic inPostChecker = new CheckPostLogic();
            inPostChecker.titleChecker(title);
            inPostChecker.starsChecker(strStars);
            errorMsgResult = inPostChecker.errorMsg();

            // エラーメッセージがNullの時、正常とする。
            if (errorMsgResult.size() == 0) {

                int stars = Integer.parseInt(strStars);
                // ﾁｪｯｸが正常の場合次の画面に遷移する。
                NewLogic inPost = new NewLogic();
                inPost.execute(loginId, postId, title, content, stars, image);
                request.setAttribute("message", "登録完了");
                response.sendRedirect("list");
                return;

            } else {
                // チェックエラーの場合前の画面にエラーメッセージを格納して遷移する。
                // 次の処理画面に遷移する
                request.setAttribute("errorMessage", errorMsgResult);
                String view = "new";
                RequestDispatcher dispatcher = request.getRequestDispatcher(view);
                dispatcher.forward(request, response);
            }
        }
    }
}