<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<% 
  int showPostId = ((Integer)(request.getAttribute("postId"))).intValue();
  ArrayList<String> errorMessage = (ArrayList<String>)request.getAttribute("errorMessage");
  int intStars = ((Integer)(request.getAttribute("stars"))).intValue();
  String image = (String)request.getAttribute("image");
  String tmp1 =  (String)request.getAttribute("title");
  String title = new String(tmp1.getBytes("ISO-8859-1"),"UTF-8");
  String tmp2 =  (String)request.getAttribute("content");
  String content = new String(tmp2.getBytes("ISO-8859-1"),"UTF-8");
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
             aria-label="main navigation">
             <div class="container">
                 <div class="navbar-brand">
                     <a id="logo" class="navbar-item" href="#">
                         <span class="logo-triangle"></span>
                         <span>BookReview APP</span>
                        </a>
                    </div>
                    <div class="navbar-menu">
                        <div class="navbar-end">
                            <a class="navbar-item" href="logout">
                                <span class="icon has-text-orange"><i class="fas fa-sign-in-alt"></i></span>
                                <span>Log out</span>
                            </a> 
                        </div>
                    </div>
                </div>
            </nav>
            <div class="error">
                <div class="container">   
                  <%  if (errorMessage.size()==0) {} else{ %>
                    <div class="notification">
                      <div class="subtitle is-3 has-text-danger">
                        <span class="icon is-small is-left">
                          <i class="fa-solid fa-circle-exclamation"></i>
                        </span>
                        <% for (String errorMsg : errorMessage) { %>
                        <%= errorMsg %>
                        <% } %>
                      </div>
                    </div>
                    <% } %>
                  </div>
                </div>
            <div class="post-body">
                <div class="container">
                    <div class="post-field">
                        <div class="form-field">

                            <form action='update' method='post' enctype="multipart/form-data">
                                <table>
                                    <input type='hidden' name='postId' value='<%= request.getAttribute("postId") %>'>
                                    <input type='hidden' name='loginId' value='<%= request.getAttribute("loginId") %>'>
                                    <tr>
                                        <th>
                                            <br><label for='title'>タイトル　<br></label>
                                        </th>
                                        <td>
                                            <br>
                                            <input class="input is-medium" type='text' name='title' value='<%= title %>'>
                                            <br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            <br><label for='content'>本文 <br></label>
                                        </th>
                                        <td>
                                            <br>
                                            <textarea class="textarea is-medium" name='content' cols='40' rows='10'><%= content %></textarea>
                                            <br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            <label for='stars'>評価</label> <br>
                                        </th>
                                        <td>
                                            <div class="rate-form">
                                                <input id="star5" type="radio" name="stars" value="5">
                                                <label for="star5">★</label>
                                                <input id="star4" type="radio" name="stars" value="4">
                                                <label for="star4">★</label>
                                                <input id="star3" type="radio" name="stars" value="3">
                                                <label for="star3">★</label>
                                                <input id="star2" type="radio" name="stars" value="2">
                                                <label for="star2">★</label>
                                                <input id="star1" type="radio" name="stars" value="1">
                                                <label for="star1">★</label>
                                                <input type="radio" name="stars" value="0">
                                              </div>
                                        </td>
                                    </tr>
                                    <tr>                                            <br>
                                        <th>
                                            <label for='image'></label>
                                        </th>
                                        <td>
                                            <br>
                                            <div id="file-js-example" class="file has-name">
                                                <label id="file-label">
                                                  <input class="file-input" type="file" name="image" >
                                                  <span class="file-cta">
                                                    <span class="file-icon">
                                                      <i class="fas fa-upload"></i>
                                                    </span>
                                                    <span class="file-label">
                                                      Choose a file…
                                                    </span>
                                                  </span>
                                                  <span class="file-name">
                                                    <%= image %>
                                                  </span>
                                                </label>
                                              </div>
                                             
                                              <script>
                                                const fileInput = document.querySelector('#file-js-example input[type=file]');
                                                fileInput.onchange = () => {
                                                  if (fileInput.files.length > 0) {
                                                    const fileName = document.querySelector('#file-js-example .file-name');
                                                    fileName.textContent = fileInput.files[0].name;
                                                  }
                                                }
                                              </script>
                                            <br>
                                        </td>
                                    </tr>
                                </table>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
                <footer>
                    <div class="container">
                        <button class="button is-link size button-post" type='submit'>編集を保存する</button>
                    </form>
                    <form name="showback" method="post" action="show">
                        <input type="hidden" name="postId" value="<%= showPostId %>">
                    </form>
                    <a class="button is-white size button-cancel" href="#" onclick="document.showback.submit();return false;">戻る</a>
                </div>
            </footer>
    </body>
</html>