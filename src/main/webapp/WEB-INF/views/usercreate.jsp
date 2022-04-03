<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%String loginId = (String)session.getAttribute("loginId");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
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
           <div class="navbar-menu">
               <div class="navbar-end">
                   <a class="navbar-item" href="https://quiet-island-17618.herokuapp.com/index.jsp">
                       <span class="icon has-text-orange"><i class="fas fa-sign-in-alt"></i></span>
                       <span>Log out</span>
                   </a> 
               </div>
           </div>
       </div>
   </nav>
   <section class="section hero is-fullheight">
    <div class="user-create-body">
        <div class="container">
            <div class="box">
                <p>Login ID: <%= loginId %> を登録しました</p>
            </div>
            <form name="listlogin" method="post" action="list">
                <input type="hidden" name="<%= loginId %>" value="">
            </form>
            <p><a class="button is-link login-move" href="#" onclick="document.listlogin.submit();return false;">
                ログイン</a></p>
        </div>
    </div>
</section>
</body>
</html>

