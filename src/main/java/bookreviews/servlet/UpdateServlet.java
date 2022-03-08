package bookreviews.servlet;

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

import bookreviews.model.CheckPostLogic;
import bookreviews.model.UpdateLogic;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

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
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String strStars = request.getParameter("stars");
            String image = request.getParameter("image");

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
                // 更新を行う処理を実行する。
                UpdateLogic updatePost = new UpdateLogic();
                updatePost.execute(postId, loginId, title, content, stars, image);
                request.setAttribute("message", "更新完了");
                // 更新後の画面に遷移する。
                String forward = "show?postId=" + postId;
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
                dispatcher.forward(request, response);

            } else {
                // チェックエラーの場合前の画面にエラーメッセージを格納して遷移する。
                // 次の処理画面に遷移する
                request.setAttribute("errorMessage", errorMsgResult);
                String view = "edit";
                RequestDispatcher dispatcher = request.getRequestDispatcher(view);
                dispatcher.forward(request, response);
            }
        }
    }
}