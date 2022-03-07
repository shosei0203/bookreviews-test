package bookreviews.model;

import bookreviews.dao.AccountDAO;
import bookreviews.dao.UserCreateDAO;

public class UserCreateLogic {
    public boolean execute(UserForm user) {

        // UserCreateServletからログインIDとパスワードを取得してDBに登録する
        // アカウント情報をDBへ登録するDAOをインスタンス化
        UserCreateDAO insertDao = new UserCreateDAO();
        insertDao.userCreate(user);

        // LoginServletからログインIDとパスワードがDB情報と一致していることを確認するためのロジック
        // AccountDAOのfindLoginUserメソッドをformに入った値を引数にして呼び出す
        // DAOにあるSELECT文で見つかったユーザーが返ってくるか、取得できずエラーでNullが返ってくるか
        // DAOからはAccountDTO型でReturnされてくる
        AccountDAO selectDao = new AccountDAO();
        AccountDTO selectAccount = selectDao.findLoginUser(user);

        // 返ってきたデータがNullならfalse,何か返ってきてるならtrueとして評価してreturnする。
        return selectAccount != null;
    }

    public boolean userChecker(String loginId, String pass, String check) {
        if ((loginId == "" || pass == "" || check == "") || !(pass.equals(check))) {
            return true;
        }
        return false;
    }
}