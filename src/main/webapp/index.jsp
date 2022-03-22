<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>BookReview</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <h1>ログイン</h1>
        <form action='https://quiet-island-17618.herokuapp.com/login' method='post'>
            <label for='loginId'>ログインID：</label>
            <input type='text' name='loginId' value=''>
            <p></p>
            <label for='pass'>パスワード：</label>
            <input type='password' name='pass' value=''>
            <p></p>
            <button type='submit'>ログイン</button>
            <a href='https://quiet-island-17618.herokuapp.com/newUser'>新規登録画面</a>
        </form>
    </body>
</html>