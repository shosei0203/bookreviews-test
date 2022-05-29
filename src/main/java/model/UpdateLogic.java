package model;

import dao.ReviewsDAO;

public class UpdateLogic {
    public ReviewsDTO execute(String loginId, int postId) {

        ReviewsDAO dao = new ReviewsDAO();

        // Update対象のデータをSelectするDAOを呼び出す。
        ReviewsDTO review = dao.showPost(loginId, postId);

        // Update対象のデータをリターンする
        return review;
    }

    public void execute(int postId, String loginId, String title, String content, int stars, String image) {

        ReviewsDAO dao = new ReviewsDAO();

        // Updateを実行するDAOを呼び出す。
        dao.updatePost(postId, loginId, title, content, stars, image);

        return;
    }

}