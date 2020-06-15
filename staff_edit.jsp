<%@ page contentType="text/html;charset=Shift_JIS" %>

<html>
<head>
<title>社員編集画面</title>

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

  <h2><center>社員編集</center></h2>

  <form action="/Attendance/StaffEdit?id=<%=request.getAttribute("id") %>" method="POST"  style="display: inline">
    <div>
      <div>
        <span class="col-2"></span>
        <span class="col-1"><b>名前</b></span>
        <input type="text" size="50" name="name" value=<%=request.getAttribute("name") %>>
      </div>

      <div>
        <span class="col-2"></span>
        <span class="col-1"><b>メールアドレス</b></span>
        <input type="text" size="50" name="mailaddress" value=<%=request.getAttribute("mail") %>>
      </div>

      <div>
        <span class="col-2"></span>
        <span class="col-1"><b>パスワード(変更前)</b></span>
        <input type="password" size="50" name="password">
      </div>

      <div>
        <span class="col-2"></span>
        <span class="col-1"><b>パスワード(変更後)</b></span>
        <span><input type="password" size="50" name="change_password"></span>
      </div>
    </div>
    <div class="right"><input type="submit" value="編集する"></div>
  </form>

</body>
</html>