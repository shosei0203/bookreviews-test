package model;

import java.util.*;

public class CheckPostLogic {
    List<String> errorMessage = new ArrayList<>();

    public void titleChecker(String title) {
        if (title == "") {
            String message = "タイトルを入力してください";
            errorMessage.add(message);
        }
    }

    public void starsChecker(String strStars) {
        if (strStars == "") {
            String message = "評価を入力してください";
            errorMessage.add(message);

        } else {
            int stars = Integer.parseInt(strStars);
            if (stars < 0 || stars > 10) {
                String message = "10段階で評価してください";
                errorMessage.add(message);
            }
        }
    }

    public List<String> errorMsg() {
        return errorMessage;

    }
}