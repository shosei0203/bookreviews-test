package model;

import java.util.*;
import javax.servlet.http.Part;

public class CheckPostLogic {
    List<String> errorMessage = new ArrayList<>();

    public void titleChecker(String title) {
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

    public void imageChecker(String image, Part filePart) {
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
                if(filePart.getSize()>5000000){
                    String message = "5MB以下の画像を取り込んでください";
                    errorMessage.add(message);
                }

        }

    public List<String> errorMsg() {
        return errorMessage;

    }
}