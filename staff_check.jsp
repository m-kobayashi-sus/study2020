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
      <div class="right white"><a href="/attendance/staff_list.jsp"> 社員を登録する </a></div>
      <div class="left white"><a href="/attendance/attendanceList.jsp"> 勤怠管理システム </a></div>
    </div>
  </header>

  <h2><center>社員登録</center></h2>

  <p style="border-style: solid; border-width: 1px; padding: 5px 5px 5px 5px;background-color:#66FF66;">登録内容を確認し、問題がなければ「登録する」を押してください。<br>セキュリティ上、パスワードは表示されません。</p>

  <span class="col-2"></span>
  <span class="col-1"><b>お名前</b></span>
  <%
    String name = (String)session.getAttribute("Name");
    out.print(name);
  %>
  <br>
   
  <span class="col-2"></span>
  <span class="col-1"><b>メールアドレス</b></span>
  <%
    String mailaddress = (String)session.getAttribute("Mailaddress");
    out.print(mailaddress);
  %>
  <br>
  
  <span class="col-2"></span>
  <span class="col-1"><b>パスワード</b></span>
  <%
    String pass = (String)session.getAttribute("Pass");
    out.print(pass);
  %>
  
  <div class="right"><button type=“button” onclick="location.href='/attendance/staff_reg_complete.jsp'">登録する</button></div> 
  
</body>
</html>