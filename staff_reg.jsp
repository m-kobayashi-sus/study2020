<%@ page contentType="text/html;charset=Shift_JIS" %>

<html>
<head>
<title>�Ј��o�^���</title>

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

  <form action="/attendance/StaffReg" method="POST"  style="display: inline">
    <div>
      <div>
        <span class="col-2"></span>
        <span class="col-1"><b>���O</b></span>
        <input type="text" size="50" name="name">
      </div> 
  
      <div>
        <span class="col-2"></span>
        <span class="col-1"><b>���[���A�h���X</b></span>
        <input type="text" size="50" name="mailaddress">
      </div>
  
      <div>
        <span class="col-2"></span>
        <span class="col-1"><b>�p�X���[�h</b></span>
        <input type="password" size="50" name="password">
      </div>
    </div>

    <div class="right"><input type="submit" value="�o�^����"></div>
  </form>

</body>
</html>