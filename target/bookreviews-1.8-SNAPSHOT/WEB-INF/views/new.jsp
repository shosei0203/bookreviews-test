<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<% ArrayList<String> errorMessage = (ArrayList<String>)request.getAttribute("errorMessage");
    int postId = (Integer)request.getAttribute("postId");
    String loginId = (String)request.getAttribute("personId");
%>
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
                <% for (String errorMsg : errorMessage) { %>
                <span class="icon is-small is-left">
                  <i class="fa-solid fa-circle-exclamation"></i>
                </span>
                <%= errorMsg %>
                <br>
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
                    <br>
                    <p>LoginID：　<%= loginId %></p>
                    <p><%= postId  %>投稿目</p>
                    <form action='create?postId=<%= postId %>' method='post' enctype="multipart/form-data">
                      <!--<input type='hidden' name='postId' value= <%= postId %>>-->
                      <input type='hidden' name='loginId' value= <%= loginId %>>
                        <table>

                            <tr>
                                <th>
                                    <br><label for='title'>タイトル　<br></label>
                                </th>
                                <td>
                                    <br>
                                    <input class="input is-medium" type='text' name='title' value=''>
                                    <br>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <br><label for='content'>本文 <br></label>
                                </th>
                                <td>
                                    <br>
                                    <textarea class="textarea is-medium" name='content' cols='40' rows='10'></textarea>
                                    <br>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label for='stars'>評価</label><br></label>
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
                                      </div>
                                </td>
                            </tr>
                            <tr>
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
                                            No file uploaded
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
                                      </script>                                    <br>
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
                <button class="button is-link size button-post" type='submit'>保存する</button>
            </form>
            <!--<a href='list'>キャンセル</a>-->
            <form name="listback" method="post" action="list">
                <input type="hidden" name="" value=>
            </form>
            <a class="button is-white size button-cancel" href="#" onclick="document.listback.submit();return false;">戻る</a>
        </div>
    </footer>
    </body>
</html>
