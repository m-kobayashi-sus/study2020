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
      <div class="right white"><a href="/attendance/staff_list.jsp"> 社員を登録する </a></div>
      <div class="left white"><a href="/attendance/attendanceList.jsp"> 勤怠管理システム </a></div>
    </div>
  </header>

  <h2><center>社員編集</center></h2>

  <div>
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>名前</b></span>
      <span><input type="text" size="50" name="name"></span>
    </div> 
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>メールアドレス</b></span>
      <span><input type="text" size="50" name="mailaddress"></span>
    </div>
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>パスワード(変更前)</b></span>
      <span><input type="password" size="50" name="password"></span>
    </div>
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>パスワード(変更後)</b></span>
      <span><input type="password" size="50" name="password"></span>
    </div>
  
  </div>

  <div class="right"><button type=“button” onclick="location.href='/attendance/staff_check.jsp'">編集する</button></div>

</body>
</html>