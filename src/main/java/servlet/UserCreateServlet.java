package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserCreateLogic;
import model.UserForm;

@WebServlet("/userCreate")
public class UserCreateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        // セッション情報を確認する。
        String loginId = request.getParameter("loginId");
        String pass = request.getParameter("pass");
        String check = request.getParameter("check");

        // 新規登録ユーザーの情報を取得し、チェックする
        UserCreateLogic checkBool = new UserCreateLogic();
        boolean result = checkBool.userChecker(loginId, pass, check);
        if (result) {
            request.setAttribute("message", "入力に誤りがあります。");

        } else {
            boolean resultid = checkBool.userChecker(loginId);
            if (resultid) {
                request.setAttribute("message", "このIDはすでに登録されています。");

            } else {

                // 画面の値をUserFormに格納する。
                UserForm loginUser = new UserForm(loginId, pass);

                // ログインIDとパスワード登録後、DB情報から取得できることを確認する。
                UserCreateLogic userCreate = new UserCreateLogic();
                boolean isLogin = userCreate.execute(loginUser);

                if (isLogin) {
                    // セッションを作成する
                    HttpSession session = request.getSession();
                    session.setAttribute("loginId", loginUser.getLoginId());

                    // 登録完了画面に遷移
                    request.setAttribute("message", "成功");

                    String view = "/WEB-INF/views/usercreate.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(view);
                    dispatcher.forward(request, response);

                } else {
                    // エラーメッセージを格納し、ログイン画面に戻す。
                    request.setAttribute("message", "セッションが切れました。再度ログインしてください。");
                }
            }
        }
        // チェックエラーもしくはログイン情報を得られなかった場合、ユーザー登録画面に遷移。
        String view = "/WEB-INF/views/newuser.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);

    }
}
