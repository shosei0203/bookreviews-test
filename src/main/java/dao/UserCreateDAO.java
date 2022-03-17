package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.UserForm;

public class UserCreateDAO {

    public void userCreate(UserForm user) {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DBへの接続情報を変数に格納する
            String url = "jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_061d54e7062a5b4?reconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
            String dbUser = "b2ad53d76c0eec";
            String password = "2160bdd0";
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
