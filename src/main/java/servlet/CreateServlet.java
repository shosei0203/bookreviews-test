package servlet;

import java.io.File;
import java.io.IOException;


import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.CheckPostLogic;
import model.NewLogic;

@WebServlet("/create")
@MultipartConfig(maxFileSize=5000000, maxRequestSize=5000000, fileSizeThreshold=5000000)
public class CreateServlet extends HttpServlet {

    private String image;
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
            String postIdStr = (String) request.getParameter("postId");
			int postId = Integer.valueOf(postIdStr);
            
            String title = (String) request.getParameter("title");
            String content = (String) request.getParameter("content");
            String strStars = (String) request.getParameter("stars");

            //画像処理
            Part filePart = request.getPart("image");
            String path = getServletContext().getRealPath("./upload");

            if(filePart.getSize()==0){
                image = "NoImage.png";
            } else {
            //ファイル名を取得
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            image = fileName;
            }
            
            // 入力値ﾁｪｯｸを行う。
            List<String> errorMsgResult = new ArrayList<String>();
            CheckPostLogic inPostChecker = new CheckPostLogic();
            inPostChecker.titleChecker(title);
            inPostChecker.starsChecker(strStars);
            inPostChecker.imageChecker(image, filePart);
            
            //画像の保存
            filePart.write(path+File.separator+image);
            System.out.println(image);

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