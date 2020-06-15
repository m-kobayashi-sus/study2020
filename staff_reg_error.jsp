<%@ page contentType="text/html;charset=Shift_JIS" %>

<html>
<head>
<title>社員登録画面(エラー)</title>

<style type="text/css">
  .blue {
    background-color:blue; ;
  }

  .left{
    texti-align: left;
  }

  .right{
    text-align: right;
    float: right;
  }

  div.white a{ color: white; }

  .col-1 {
    display: inline-block;
    width: 200px;
  }

  .col-2 {
    display: inline-block;
    width: 50px;
  }

</style>

</head>
<body>

  <header>
    <div class="blue">
      <div class="right white"><a href="/Attendance/StaffList"> 社員を登録する </a></div>
      <div class="left white"><a href="/Attendance/TOP"> 勤怠管理システム </a></div>
    </div>
  </header>

  <h2><center>社員登録</center></h2>


    <p style="border-style: solid; border-width: 1px; padding: 5px 5px 5px 5px;background-color:#FF99CC;">
    <% if (request.getAttribute("Name") == "" ) { %>
      ・名前を入力してください。
    <% } %>

    <% if ((request.getAttribute("nameCheck").equals("FAILURE")) && (request.getAttribute("Name") != "" )) { %>
      ・名前2文字以上20文字以内で入力してください。
    <% } %>


    <% if ((request.getAttribute("Mailaddress") == "" )) { %>
      <br>・メールアドレスを入力してください。
    <% } %>

    <% if (request.getAttribute("mailaddressCheck") == ("FAILURE" )) { %>
      <br>・メールアドレスを50文字以内で入力してください。
    <% } %>

    <% if ((request.getAttribute("checkHanMailaddress") == ("FAILURE")) && (request.getAttribute("mailaddressCheck") == ("SUCCESS" )) && (request.getAttribute("Mailaddress") != "" ))  { %>
      <br>・メールアドレスは半角英数字記号だけで入力してください。
    <% } %>

    <% if ((request.getAttribute("passwordCheck") == "FAILURE" ) && (request.getAttribute("Password") != "") && (request.getAttribute("checkHanPassword") == ("SUCCESS"))) { %>
      <br>・パスワードは8文字以上64文字以内で入力してください。
    <% } %>

    <% if ((request.getAttribute("checkHanPassword") == ("FAILURE")) && (request.getAttribute("Password") != "" ))  { %>
      <br>・パスワードは半角英数字記号だけで入力してください。
    <% } %>

    <% if ((request.getAttribute("changePassword") == "")&& (request.getAttribute("Password") != "")){ %>
      <br>・パスワード(変更後)を入力してください。
    <% }else if((request.getAttribute("Password") == "" ) && (request.getAttribute("changePassword") != "")) { %>
      <br>・パスワード(変更前)を入力してください。
    <% }else if ((request.getAttribute("changePassword") == "") && (request.getAttribute("Password") == "")) { %>
      <br>・パスワードを入力してください。
    <% } %>

    <% if ((request.getAttribute("changePasswordCheck") == "FAILURE" ) &&  (request.getAttribute("changePassword") != "") && (request.getAttribute("checkHanChangePassword") == ("SUCCESS"))) { %>
      <br>・パスワードは8文字以上64文字以内で入力してください。
    <% } %>

    <% if ((request.getAttribute("checkHanChangePassword") == ("FAILURE")) && (request.getAttribute("changePassword") != "" ))  { %>
      <br>・パスワードは半角英数字記号だけで入力してください。
    <% } %>

    <% if (!(request.getAttribute("Password")).equals(request.getAttribute("DBPassword")) && (request.getAttribute("Password")) != "") { %>
      <br>・パスワードが間違っています。
    <% } %>
    </p>
  <form action="/Attendance/StaffRegError?id=<%=request.getAttribute("id") %>" method="POST"  style="display: inline">
    <div>
      <div>
        <span class="col-2"></span>
        <span class="col-1"><b>名前</b></span>
        <input type="text" size="50" name="name">
      </div>

      <div>
        <span class="col-2"></span>
        <span class="col-1"><b>メールアドレス</b></span>
        <input type="text" size="50" name="mailaddress">
      </div>

      <div>
        <span class="col-2"></span>
        <span class="col-1"><b>パスワード</b></span>
        <input type="password" size="50" name="password">
      </div>
    </div>

    <div class="right"><input type="submit" value="登録する"></div>
  </form>

</body>
</html>