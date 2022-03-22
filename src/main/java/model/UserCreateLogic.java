package model;

import dao.AccountDAO;

public class UserCreateLogic {
    public boolean execute(UserForm user) {

        // UserCreateServletからログインIDとパスワードを取得してDBに登録する
        // アカウント情報をDBへ登録するDAOをインスタンス化
        AccountDAO insertDao = new AccountDAO();
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

    public boolean userChecker(String loginId, String pass) {
        if (loginId.length() > 8 || pass.length() > 8) {
            // いずれかの項目がNullまたはチェック項目とパスワードが不一致の場合
            return true;
        }
        return false;
    }

    public boolean userChecker(String loginId, String pass, String check) {
        if ((loginId == "" || pass == "" || check == "") || !(pass.equals(check))) {
            // いずれかの項目がNullまたはチェック項目とパスワードが不一致の場合
            return true;
        }
        return false;
    }

    public boolean userChecker(String loginId) {
        // AccountDAOのfindLoginUserメソッドをformに入った値を引数にして呼び出す
        // DAOにあるSELECT文で見つかったユーザーが返ってくるか、取得できずエラーでNullが返ってくるか
        // DAOからはAccountDTO型でReturnされてくる
        AccountDAO selectDao = new AccountDAO();
        String selectAccount = selectDao.findLoginId(loginId);

        // 返ってきたデータがNullならfalse,何か返ってきてるならtrueとして評価してreturnする。
        return selectAccount != null;

    }
}