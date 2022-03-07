package bookreviews.model;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import bookreviews.dao.*;
import bookreviews.model.*;
import bookreviews.servlet.*;

class main {

    public static void main(String[] args) {
        // 入力値ﾁｪｯｸを行う。
        String title = "";
        String strStars = "";

        // 入力値ﾁｪｯｸを行う。
        List<String> errorMsgResult = new ArrayList<String>();
        CheckPostLogic inPostChecker = new CheckPostLogic();
        inPostChecker.titleChecker(title);
        inPostChecker.starsChecker(strStars);
        errorMsgResult = inPostChecker.errorMsg();

        // エラーメッセージがNullの時、正常とする。
        if (errorMsgResult != null) {

        } else {
        }
        for (String errorMsg : errorMsgResult)
            System.out.println(errorMsg);
    }
    /*
     * NewLogic test = new NewLogic();
     * ReviewsDTO dto = test.execute("1");
     * 
     * int postId = dto.getPostId();
     * System.out.println(postId);
     */

}
