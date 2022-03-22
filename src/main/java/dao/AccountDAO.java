package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.AccountDTO;
import model.UserForm;

public class AccountDAO {

    String url = "jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_061d54e7062a5b4?reconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
    String dbUser = "b2ad53d76c0eec";
    String password = "2160bdd0";

    public AccountDTO findLoginUser(UserForm user) {

        Connection connection = null;
        AccountDTO account = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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

    public void userCreate(UserForm user) {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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

    public String findLoginId(String loginId) {

        Connection connection = null;
        String account = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "SELECT personId FROM users WHERE personId = ?";

            // 接続情報を取得してDBに接続する
            connection = DriverManager.getConnection(url, dbUser, password);

            // 接続できたらSQL変数に入っているSQL文をstatement変数に代入する
            PreparedStatement statement = connection.prepareStatement(sql);

            // SQL文の？の1つ目と2つ目の値にログイン画面に入力したIDとパスワードを詰める
            statement.setString(1, loginId);

            // IDとパスワードを詰めた後のSQLをDBに対して実行する
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                String oldLoginId = results.getString("personId");

                // 取得した値をAccountDTOにセットする。
                account = new String(oldLoginId);
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
