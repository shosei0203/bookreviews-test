package model;

import java.util.*;
import dao.*;

public class ListSelectLogic {
    public List<ReviewsDTO> execute(String loginId) {

        ReviewsDAO dao = new ReviewsDAO();

        // 一覧表示させるためのデータを取得するSelect用DAOを呼び出す
        List<ReviewsDTO> reviews = dao.findReviews(loginId);

        // 一覧表示させるデータリストをリターン
        return reviews;
    }
}