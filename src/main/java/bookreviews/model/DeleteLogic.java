package bookreviews.model;

import bookreviews.dao.ReviewsDAO;

public class DeleteLogic {
    public void execute(int postId, String loginId) {

        ReviewsDAO dao = new ReviewsDAO();

        // Delete用DAOを呼び出す
        dao.deletePost(postId, loginId);

        // 削除を実行して終わり
        return;
    }

}