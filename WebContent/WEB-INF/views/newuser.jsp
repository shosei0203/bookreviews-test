<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
    <title>BookReviewAPP</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v6.1.1/css/all.css"
    />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
    />
    <link rel="stylesheet" href="/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
  </head>
  <body>
    <div id="wrapper">
    <nav
    class="navbar is-fixed-top"
    role="navigation"
    aria-label="main navigation"
    >
    <div class="container">
      <div class="navbar-brand">
        <a id="logo" class="navbar-item" href="#">
          <span class="logo-triangle"></span>
          <span>BookReview APP</span>
        </a>
      </div>
    </div>
  </nav>

    <section>
      <div class="error">
        <div class="container">   
          <%  if (request.getAttribute("message") == null) {} else{ %>
            <div class="notification">
              <div class="subtitle is-3 has-text-danger">
                <span class="icon is-small is-left">
                  <i class="fa-solid fa-circle-exclamation"></i>
                </span>
                <%= request.getAttribute("message") %> 
              </div>
            </div>
            <% } %>
          </div>
        </div>
      <div id="warpper">
        <div class="login-body">
              <div class="container">     
                <h2>新規ユーザー登録</h2>
                <form action="userCreate" method="post">
                  <div class="field">
                    <label for="loginId" class="text-margin">Login ID</label>
                    <div class="control has-icons-left">
                      <input
                        class="input is-success"
                        type="text"
                        name="loginId"
                        value=""
                        placeholder="IDを入力"
                      />
                      <span class="icon is-small is-left">
                        <i class="fas fa-user"></i>
                      </span>
                    </div>
                  </div>
                  <div class="field">
                    <label for="pass" class="text-margin">Password</label>
                    <div class="control has-icons-left">
                      <input
                        class="input is-success"
                        type="password"
                        name="pass"
                        value=""
                        placeholder="パスワードを入力"
                      />
                      <span class="icon is-small is-left">
                        <i class="fas fa-key"></i>
                      </span>
                    </div>
                  </div>
                  <div class="field">
                    <label for="check" class="text-margin">Retype Password</label>
                    <div class="control has-icons-left">
                      <input
                        class="input is-success"
                        type="password"
                        name="check"
                        value=""
                        placeholder="パスワード再入力"
                      />
                      <span class="icon is-small is-left">
                        <i class="fa-solid fa-check"></i>
                      </span>
                    </div>
                  </div>
                </div>
            </div>
            <div class="login-body-button">
              <div class="container">
                <div class="field">
                  <input
                    type="submit"
                    name="commit"
                    value="新規登録"
                    class="button is-link width-100"
                  />
                  <a class="button is-link width-100" href="login">戻る</a>
                </form>
                </div>
              </div>
          </div>
        </div>
      </section>
    </div>
  </body>
</html>