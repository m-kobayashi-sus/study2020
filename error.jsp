<%@ page contentType="text/html;charset=Shift_JIS" %>

<html>
<head>
<title>エラー画面</title>

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
      <div class="right white"><a href="/attendance/staff_list.jsp"> 社員を登録する </a></div>
      <div class="left white"><a href="/attendance/attendanceList.jsp"> 勤怠管理システム </a></div>
    </div>
  </header>

  <h2><center>エラー</center></h2>
  <p><center>エラーが発生しました。<br>お手数ですが、操作をやり直してください。</center></p>
  <center><button type=“button” onclick="location.href='/attendance/attendanceList.jsp'">勤怠管理システムトップに戻る</button></center>

</body>
</html>