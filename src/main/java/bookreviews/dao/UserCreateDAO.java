package dao;

import java.sql.*;
import model.*;

public class UserCreateDAO {

    public void userCreate(UserForm user) {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DBへの接続情報を変数に格納する
            String url = "jdbc:mysql://localhost:3306/book_reviews?useUnicode=true&amp&characterEncoding=utf8";
            String dbUser = "root";
            String password = "sakagamis0203";
            String insertSql = "INSERT INTO users (personId,username,pass,mail) VALUES (?, 'A', ?, 'B')";

            // 接続情報を取得してDBに接続する
            connection = DriverManager.getConnection(url, dbUser, password);

            // 接続できたらSQL変数に入っているSQL文をstatement変数に代入する
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);

            // SQL文の？の1つ目と3つ目の値にユーザー登録画面に入力したIDとパスワードを詰める
            insertStatement.setString(1, user.getLoginId());
            insertStatement.setString(2, user.getPass());

            // IDとパスワードを詰めた後のSQLをDBに対して実行する。
            insertStatement.executeUpdate();

            // 上記INSERTで登録したデータを取得する（新規登録後、その情報を取得してログインできるようにするため）

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
