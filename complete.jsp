<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<title>勤怠登録画面(完了)</title>

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

</style>

</head>

<body>

  <header>
    <div class="blue">
      <div class="right white"><a href="/Attendance/StaffList"> 社員を登録する </a></div>
      <div class="left white"><a href="/Attendance/TOP"> 勤怠管理システム </a></div>
    </div>
  </header>

  <h2><center>登録完了</center></h2>
  <p><center>勤怠データの登録を行いました。</center></p>
  <center><button type=“button” onclick="location.href='/Attendance/TOP'">戻る</button></center>

</body>
</html>