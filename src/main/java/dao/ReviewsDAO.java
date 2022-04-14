package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ReviewsDTO;

public class ReviewsDAO {

    // DBへの接続情報を変数に格納する
    String url = "jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_061d54e7062a5b4?reconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
    String dbUser = "b2ad53d76c0eec";
    String password = "2160bdd0";
    String sql;

    public List<ReviewsDTO> findReviews(String loginId) {

        Connection connection = null;
        List<ReviewsDTO> reviews = new ArrayList<ReviewsDTO>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sql = "SELECT * FROM reviews where personId = ?";

            // 接続情報を取得してDBに接続する
            connection = DriverManager.getConnection(url, dbUser, password);

            // 接続できたらSQL変数に入っているSQL文をstatement変数に代入する
            PreparedStatement statement = connection.prepareStatement(sql);

            // SQL文の？の1つ目の値にユーザー登録画面に入力したIDを詰める
            statement.setString(1, loginId);

            // IDを詰めた後のSQLをDBに対して実行する。
            ResultSet results = statement.executeQuery();

            while (results.next()) {

                int postId = results.getInt("postId");
                String userId = results.getString("personId");
                String title = results.getString("title");
                String content = results.getString("content");
                int stars = results.getInt("stars");
                String image = results.getString("image");
                ReviewsDTO review = new ReviewsDTO(postId, userId, title, content, stars, image);
                reviews.add(review);
            }

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
        return reviews;
    }

    public ReviewsDTO findPostId(String loginId) {

        Connection connection = null;
        ReviewsDTO review = new ReviewsDTO();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sql = "SELECT max(postId) FROM reviews where personId = ?";

            // 接続情報を取得してDBに接続する
            connection = DriverManager.getConnection(url, dbUser, password);

            // 接続できたらSQL変数に入っているSQL文をstatement変数に代入する
            PreparedStatement statement = connection.prepareStatement(sql);

            // SQL文の？の1つ目の値にユーザー登録画面に入力したIDを詰める
            statement.setString(1, loginId);

            // IDを詰めた後のSQLをDBに対して実行する。
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                review = new ReviewsDTO(result.getInt("max(postId)"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return review;
    }

    public void inPost(String loginId, int postId, String title, String content, int stars, String image) {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sql = "INSERT INTO reviews (postId, personId, title, content, stars, image) VALUES (?, ?, ?, ?, ?, ?)";

            // 接続情報を取得してDBに接続する
            connection = DriverManager.getConnection(url, dbUser, password);

            // 接続できたらSQL変数に入っているSQL文をstatement変数に代入する
            PreparedStatement statement = connection.prepareStatement(sql);

            // SQL文の？の1つ目の値にユーザー登録画面に入力したIDを詰める
            statement.setInt(1, postId);
            statement.setString(2, loginId);
            statement.setString(3, title);
            statement.setString(4, content);
            statement.setInt(5, stars);
            statement.setString(6, image);

            // IDを詰めた後のSQLをDBに対して実行する。
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    public ReviewsDTO showPost(String loginId, int postId) {

        Connection connection = null;
        ReviewsDTO review = new ReviewsDTO();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sql = "SELECT * FROM reviews WHERE postId = ? and personId = ?";

            // 接続情報を取得してDBに接続する
            connection = DriverManager.getConnection(url, dbUser, password);

            // 接続できたらSQL変数に入っているSQL文をstatement変数に代入する
            PreparedStatement statement = connection.prepareStatement(sql);

            // SQL文の？の1つ目の値にユーザー登録画面に入力したIDを詰める
            statement.setInt(1, postId);
            statement.setString(2, loginId);

            // IDを詰めた後のSQLをDBに対して実行する。
            statement.executeQuery();
            // IDを詰めた後のSQLをDBに対して実行する。
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int setPostId = result.getInt("postId");
                String setLoginId = result.getString("personId");
                String setTitle = result.getString("title");
                String setContent = result.getString("content");
                int setStars = result.getInt("stars");
                String setImage = result.getString("image");
                review = new ReviewsDTO(setPostId, setLoginId, setTitle, setContent, setStars, setImage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return review;
    }

    public void updatePost(int postId, String loginId, String title, String content, int stars, String image) {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sql = "UPDATE reviews SET title = ?, content = ? ,stars = ? ,image = ? WHERE postId = ? and personId = ?";

            // 接続情報を取得してDBに接続する
            connection = DriverManager.getConnection(url, dbUser, password);

            // 接続できたらSQL変数に入っているSQL文をstatement変数に代入する
            PreparedStatement statement = connection.prepareStatement(sql);

            // SQL文の？の1つ目の値にユーザー登録画面に入力したIDを詰める
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setInt(3, stars);
            statement.setString(4, image);
            statement.setInt(5, postId);
            statement.setString(6, loginId);
           
            // IDを詰めた後のSQLをDBに対して実行する。
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    public void deletePost(int postId, String loginId) {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sql = "delete from reviews where postId = ? and personId = ?";

            // 接続情報を取得してDBに接続する
            connection = DriverManager.getConnection(url, dbUser, password);

            // 接続できたらSQL変数に入っているSQL文をstatement変数に代入する
            PreparedStatement statement = connection.prepareStatement(sql);

            // SQL文の？の1つ目の値にユーザー登録画面に入力したIDを詰める
            statement.setInt(1, postId);
            statement.setString(2, loginId);

            // IDを詰めた後のSQLをDBに対して実行する。
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

}
