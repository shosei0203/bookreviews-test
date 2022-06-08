package servlet;

import java.io.IOException;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteLogic;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

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
            String image = request.getParameter("image");

            if(!(image.equals("NoImage.png"))){
                String path = getServletContext().getRealPath("./upload");
                File path2 = new File(path + "/" + image);
                // 削除処理を実行する。
                path2.delete();
            }

            DeleteLogic deletePost = new DeleteLogic();
            deletePost.execute(postId, loginId);
            request.setAttribute("message", postId + "を削除しました。");

            // 処理後、次の画面に遷移する。
            response.sendRedirect("list");                        
        }
            //response.sendRedirect("list");    
    }
}
