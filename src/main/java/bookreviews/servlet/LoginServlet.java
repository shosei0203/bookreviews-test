package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import model.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // URLからログイン画面にフォワードする
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // セッション削除
        HttpSession session = request.getSession();
        session.invalidate();

        // ログイン画面に遷移
        String view = "/WEB-INF/views/login.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    // ログイン画面から取得したID、パスワードを取得してログイン情報が正しければListServletにリダイレクト、誤っていればログイン画面にフォワード
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        // 画面（post）の値を変数に格納する。
        String loginId = request.getParameter("loginId");
        String pass = request.getParameter("pass");

        // 画面の値をUserFormに格納する。
        UserForm loginUser = new UserForm(loginId, pass);

        // ログインIDとパスワードがDB情報と一致していることを確認する。
        LoginLogic loginCheck = new LoginLogic();
        boolean isLogin = loginCheck.execute(loginUser);

        // 画面に入力された情報が正しければtrue 誤っていればfalseがreturnされる。
        if (isLogin) {
            // セッションを作成しログイン後のListServletにリダイレクトする
            HttpSession session = request.getSession();
            session.setAttribute("loginId", loginId);
            session.setAttribute("pass", pass);

            response.sendRedirect("list");
        } else {

            // エラーメッセージを格納し、ログイン画面に戻す。
            request.setAttribute("message", "ログイン情報に誤りがあります。");

            String view = "/WEB-INF/views/login.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);

        }

    }
}