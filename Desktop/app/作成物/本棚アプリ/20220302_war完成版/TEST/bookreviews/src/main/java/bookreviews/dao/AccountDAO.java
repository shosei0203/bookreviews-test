package dao;

import java.sql.*;
import model.*;

public class AccountDAO {

    public AccountDTO findLoginUser(UserForm user) {

        Connection connection = null;
        AccountDTO account = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DBへの接続情報を変数に格納する
            String url = "jdbc:mysql://localhost:3306/book_reviews?useUnicode=true&amp&characterEncoding=utf8";
            String dbUser = "root";
            String password = "sakagamis0203";
            String sql = "SELECT personId,username,pass,mail FROM users WHERE personId = ? AND pass =?";

            // 接続情報を取得してDBに接続する
            connection = DriverManager.getConnection(url, dbUser, password);

            // 接続できたらSQL変数に入っているSQL文をstatement変数に代入する
            PreparedStatement statement = connection.prepareStatement(sql);

            // SQL文の？の1つ目と2つ目の値にログイン画面に入力したIDとパスワードを詰める
            statement.setString(1, user.getLoginId());
            statement.setString(2, user.getPass());

            // IDとパスワードを詰めた後のSQLをDBに対して実行する
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                String loginId = results.getString("personId");
                String pass = results.getString("pass");
                String userName = results.getString("username");
                String mail = results.getString("mail");

                // 取得した値をAccountDTOにセットする。
                account = new AccountDTO(loginId, pass, userName, mail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return account;
    }
}
