<%@ page contentType="text/html;charset=Shift_JIS" %>

<html>
<head>
<title>Ğˆõ“o˜^‰æ–Ê</title>

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
      <div class="right white"><a href="/attendance/staff_list.jsp"> Ğˆõ‚ğ“o˜^‚·‚é </a></div>
      <div class="left white"><a href="/attendance/attendanceList.jsp"> ‹Î‘ÓŠÇ—ƒVƒXƒeƒ€ </a></div>
    </div>
  </header>

  <h2><center>Ğˆõ“o˜^</center></h2>

  <div>
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>–¼‘O</b></span>
      <span><input type="text" size="50" name="name"></span>
    </div> 
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>ƒ[ƒ‹ƒAƒhƒŒƒX</b></span>
      <span><input type="text" size="50" name="mailaddress"></span>
    </div>
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>ƒpƒXƒ[ƒh</b></span>
      <span><input type="text" size="50" name="password"></span>
    </div>
 
  </div>

  <div class="right"><button type=gbuttonh onclick="location.href='/attendance/staff_check.jsp'">“o˜^‚·‚é</button></div>

</body>