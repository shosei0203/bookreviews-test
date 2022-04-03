<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8" />
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

    <section class="section hero is-fullheight">
      <div class="error login-error">
        <div class="container">
          <% if (request.getAttribute("message") == null) {} else{ %>
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
      <div class="login-body-button">
        <div class="container">
          <a
            class="button is-link width-100"
            href="https://quiet-island-17618.herokuapp.com/index.jsp"
          >
            戻る
          </a>
        </div>
      </div>
    </section>
  </body>
</html>
