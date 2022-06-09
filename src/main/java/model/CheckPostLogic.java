package model;

import java.util.*;
import org.apache.commons.fileupload.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CheckPostLogic {
    List<String> errorMessage = new ArrayList<>();

    public void titleChecker(String title,String content) {
        if (title == "" || title.isEmpty()) {

            String message = "タイトルを入力してください";
            errorMessage.add(message);
        } else {
            int titleLength = title.length();
            if (titleLength > 15) {
                String message = "タイトルは15文字以内で入力してください";
                errorMessage.add(message);
            }
        }
        int contentLength = content.length();
        if(contentLength > 150) {
            String message = "本文は150文字以内で入力してください";
            errorMessage.add(message);
        }
    }

    public void starsChecker(String strStars) {
        if (strStars == "" || strStars == null ) {
            String message = "評価を入力してください";
            errorMessage.add(message);

        } else {
            int stars = Integer.parseInt(strStars);
            if (stars < 1 || stars > 10) {
                String message = "10段階で評価してください";
                errorMessage.add(message);
            }
        }
    }

    public void imageChecker(String image, FileItem item) {
            Pattern checkAlphaNum = Pattern.compile("^[A-Za-z0-9]+$");
            Matcher match;
			if (image.endsWith(".JPG")
                || image.endsWith(".jpg")
				|| image.endsWith(".JEPG")
                || image.endsWith(".jpeg")
				|| image.endsWith(".PNG")
				|| image.endsWith(".png")
				|| image.endsWith(".GIF")
                || image.endsWith(".gif")
				|| image.endsWith(".BMP")
                || image.endsWith(".bmp")
				|| image.endsWith(".TIFF")
                || image.endsWith(".tiff")) {
            }else{
                String message = "画像を取り込んでください";
                errorMessage.add(message);
            }
                if(item.getSize()>5000000){
                    String message = "5MB以下の画像を取り込んでください";
                    errorMessage.add(message);
                }
                if(!(image.matches("^[a-zA-Z0-9.]+$"))){
                    String message = "画像ファイル名は英数文字列で取り込んでください";
                    errorMessage.add(message);
                }
        }

    public List<String> errorMsg() {
        return errorMessage;

    }
}