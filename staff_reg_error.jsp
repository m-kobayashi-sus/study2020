<%@ page contentType="text/html;charset=Shift_JIS" %>

<html>
<head>
<title>�Ј��o�^���(�G���[)</title>

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
      <div class="right white"><a href="/attendance/staff_list.jsp"> �Ј���o�^���� </a></div>
      <div class="left white"><a href="/attendance/attendanceList.jsp"> �ΑӊǗ��V�X�e�� </a></div>
    </div>
  </header>

  <h2><center>�Ј��o�^</center></h2>

  <p style="border-style: solid; border-width: 1px; padding: 5px 5px 5px 5px;background-color:#FF99CC;">�E���O����͂��Ă��������B<br>�E���[���A�h���X����͂��Ă��������B</p>


  <div>
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>���O</b></span>
      <span><input type="text" size="50" name="name"></span>
    </div> 
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>���[���A�h���X</b></span>
      <span><input type="text" size="50" name="mailaddress"></span>
    </div>
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>�p�X���[�h</b></span>
      <span><input type="password" size="50" name="password"></span>
    </div>
  </div>

  <div class="right"><button type=�gbutton�h onclick="location.href='/attendance/staff_reg_complete.jsp'">�o�^����</button></div>
  
</body>
</html>