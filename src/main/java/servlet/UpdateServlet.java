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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.CheckPostLogic;
import model.UpdateLogic;

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

            String pathStr = getServletContext().getRealPath("./upload");
            File path = new File(pathStr);
            DiskFileItemFactory  dfu = new DiskFileItemFactory();
            ServletFileUpload   sfu   = new ServletFileUpload(dfu); 

            // セッション・画面の情報を変数に格納していく
            request.setAttribute("loginId", loginId);

            try {
                //変数の作成
                List lists = sfu.parseRequest(request);
                Iterator iterator = lists.iterator();

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

                //ファイルデータ(FileItemオブジェクト)を処理
                while (iterator.hasNext()) {
                    item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        //残念ポイント：同じファイル名がUPされた場合、uploadフォルダに同じ名前のファイルがあり落ちてしまうため、ランダム数字を付与している
                        image = randNum + item.getName();

                        //画面で添付されていない場合
                        if(item.getSize()==0){
    
                            image = "NoImage.png";

                        //画面で添付されている場合
                        } else if ((item.getSize()!=0) && (image != null) && (!image.equals(""))) {

                            image = (new File(image)).getName();
                            inPostChecker.imageChecker(image, item);
                            errorMsgResult = inPostChecker.errorMsg();

                            if (errorMsgResult.size() == 0) {

                                sfu.setSizeMax(-1);
                                dfu.setSizeThreshold(1024); // バッファサイズ        
                                dfu.setRepository(path);// 一時ファイルの保存先フォルダ
                                sfu.setHeaderEncoding("UTF-8");

                                item.write(new File(path + "/" + image));
                            }
                        }
                //画像以外の処理"multipart/form-data"のため画面から取得した項目順に処理していく
                }else if (item.isFormField()) {
                    
                        String paraName1 = item.getFieldName();  
                        
                        if(paraName1.equals("postId")){
                            
                            request.setAttribute("paraName1", paraName1);
                            String paraValue1 = item.getString();
                            request.setAttribute("postId", paraValue1);
                            postId = Integer.parseInt(paraValue1);
                        }
                        if(paraName1.equals("loginId")){
                            
                            String paraName2 = item.getFieldName();
                            //String paraValue2 = item.getString();
                            request.setAttribute("paraName2", paraName2);
                            //request.setAttribute("loginId", paraValue2);
                            //loginId = paraValue2;
                        }
                        if(paraName1.equals("title")){
                            
                            String paraName3 = item.getFieldName();
                            String paraValue3 = item.getString();
                            request.setAttribute("paraName3", paraName3);
                            title = new String(paraValue3.getBytes("ISO-8859-1"),"UTF-8");
                            request.setAttribute("title", title);
                        }
                        
                        if(paraName1.equals("content")){
                            String paraName4 = item.getFieldName();
                            String paraValue4 = item.getString();
                            request.setAttribute("paraName4", paraName4);
                            content = new String(paraValue4.getBytes("ISO-8859-1"),"UTF-8");
                            request.setAttribute("content", content);
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
                inPostChecker.titleChecker(title);
                inPostChecker.starsChecker(strStars);
                errorMsgResult = inPostChecker.errorMsg();
                
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
                    return;

                } else {
                    // チェックエラーの場合前の画面にエラーメッセージを格納して遷移する。
                    // 次の処理画面に遷移する
                    request.setAttribute("errorMessage", errorMsgResult);
                    request.setAttribute("postId", postId);
                    String view = "edit";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(view);
                    dispatcher.forward(request, response);

                }
            }catch (Exception e) {
                e.printStackTrace();            
                }
        }
    }
}
