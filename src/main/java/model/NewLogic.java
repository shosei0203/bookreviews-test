package model;

import dao.ReviewsDAO;

public class NewLogic {
    public ReviewsDTO execute(String loginId) {

        ReviewsDAO dao = new ReviewsDAO();

        // 最大のPostIdを取得する。
        ReviewsDTO review = dao.findPostId(loginId);
        int newpostId = review.getPostId();
        newpostId += 1;

        ReviewsDTO postId = new ReviewsDTO(newpostId);

        // 最大値＋１のpostIdをリターン
        return postId;
    }

    public void execute(String loginId, int postId, String title, String content, int stars, String image) {

        ReviewsDAO dao = new ReviewsDAO();

        // 新規データを登録するInsert用のDAOを呼び出す。
        dao.inPost(loginId, postId, title, content, stars, image);

        // Insertを実行して終わり。
        return;
    }
}