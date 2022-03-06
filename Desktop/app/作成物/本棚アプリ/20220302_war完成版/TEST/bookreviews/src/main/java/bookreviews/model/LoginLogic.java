package model;

import dao.*;

public class LoginLogic {
    public boolean execute(UserForm user) {

        // LoginServletからログインIDとパスワードがDB情報と一致していることを確認するためのロジック
        // アカウント情報をDBから取得するDAOをインスタンス化
        AccountDAO dao = new AccountDAO();

        // AccountDAOのfindLoginUserメソッドをformに入った値を引数にして呼び出す
        // DAOにあるSELECT文で見つかったユーザーが返ってくるか、取得できずエラーでNullが返ってくるか
        // DAOからはAccountDTO型でReturnされてくる
        AccountDTO account = dao.findLoginUser(user);

        // 返ってきたデータがNullならfalse,何か返ってきてるならtrueとして評価してreturnする。
        return account != null;
    }
}