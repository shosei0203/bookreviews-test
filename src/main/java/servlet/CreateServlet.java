package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.CheckPostLogic;
import model.NewLogic;

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

            File path = new File("./upload");
            DiskFileItemFactory  dfu = new DiskFileItemFactory();
            ServletFileUpload   sfu   = new ServletFileUpload(dfu); 
            sfu.setSizeMax(-1);
            dfu.setSizeThreshold(1024); // バッファサイズ        
            dfu.setRepository(path);// 一時ファイルの保存先フォルダ
            sfu.setHeaderEncoding("UTF-8");
            // セッション・画面の情報を変数に格納していく
            request.setAttribute("loginId", loginId);

            try {
                //集合の要素に順番にアクセスする
                List objLst = sfu.parseRequest(request);
                Iterator iterator = objLst.iterator();

                String image = "";
                int postId = 0;
                String title = "";
                String content = "";
                String strStars = "";
                FileItem item = null;
                Random rand = new Random(); 
                String randNum = Integer.toString(rand.nextInt(100));
                
                List<String> errorMsgResult = new ArrayList<String>();
                CheckPostLogic inPostChecker = new CheckPostLogic();
                
                while (iterator.hasNext()) {
                    //ファイルデータ(FileItemオブジェクト)を順に処理
                    item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        image = randNum + item.getName();
                        
                        if(item.getSize()==0){
                            image = "NoImage.png";
                            
                        } else if ((item.getSize()!=0) && (image != null) && (!image.equals(""))) {    
                            image = (new File(image)).getName();
                            inPostChecker.imageChecker(image, item);
                            if (errorMsgResult.size() == 0) {
                                item.write(new File(path + "/" + image));
                            }
                        }//　アップロード用ファイル以外の場合
                    }else if (item.isFormField()) {
                        
                        String paraName1 = item.getFieldName();  
                        
                        if(paraName1.equals("postId")){
                            String paraValue1 = item.getString();
                            request.setAttribute("paraName1", paraName1);
                            request.setAttribute("postId", paraValue1);
                            postId = Integer.parseInt(paraValue1);
                        }
                        
                        if(paraName1.equals("loginId")){
                            String paraName2 = item.getFieldName();
                            String paraValue2 = item.getString();
                            request.setAttribute("paraName2", paraName2);
                            request.setAttribute("loginId", paraValue2);
                            loginId = paraValue2;
                        }
                        if(paraName1.equals("title")){
                            String paraName3 = item.getFieldName();
                            String paraValue3 = item.getString();
                            request.setAttribute("paraName3", paraName3);
                            request.setAttribute("title", paraValue3);
                            title = paraValue3;
                        }
                        if(paraName1.equals("content")){
                            String paraName4 = item.getFieldName();
                            String paraValue4 = item.getString();
                            request.setAttribute("paraName3", paraName4);
                            request.setAttribute("content", paraValue4);
                            content = paraValue4;
                        }
                        if(paraName1.equals("stars")){
                            String paraName5 = item.getFieldName();
                            String paraValue5 = item.getString();
                            request.setAttribute("paraName3", paraName5);
                            request.setAttribute("stars", paraValue5);
                            strStars = paraValue5;
                        }
                    }
                }
                // 入力値ﾁｪｯｸを行う。
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
                    return;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}