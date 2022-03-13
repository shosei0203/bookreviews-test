package model;

import dao.ReviewsDAO;

public class ShowLogic {
    public ReviewsDTO execute(String loginId, int postId) {

        ReviewsDAO dao = new ReviewsDAO();

        // 詳細画面に表示するデータを取得するSelect文を実行してDTOに登録。
        ReviewsDTO review = dao.showPost(loginId, postId);

        // 詳細画面に表示するデータをリターン
        return review;
    }

}