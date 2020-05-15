<%@ page contentType="text/html;charset=Shift_JIS" %>

<html>
<head>
<title>‹Î‘Ó“o˜^-•ÒW‰æ–Ê</title>

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
    width: 100px;
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

  <h2><center>‹Î‘Ó“o˜^</center></h2>

  <div>
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>–¼‘O</b></span>
      <span>R“c ‘¾˜Y</span>
    </div> 
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>“ú•t</b></span>
      <span>2010”N3Œ4“ú</span>
    </div>
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>o‹Î</b></span>
      <span>
        <select name="start_time">
	      <option value="06">06</option>	
	      <option value="07">07</option>	
	      <option value="08">08</option>	
	      <option value="09">09</option>	
	      <option value="10">10</option>	
	      <option value="11">11</option>	
	      <option value="12">12</option>	
	      <option value="13">13</option>	
	      <option value="14">14</option>	
        </select>&nbsp;
        <input type="text" size="5" name="minute">&nbsp;•ª
      </span>
    </div>
  
  <div>
    <span class="col-2"></span>
    <span class="col-1"><b>‘Ş‹Î</b></span>
    <span>
        <select name="end_time">
	      <option value="15">15</option>	
	      <option value="16">16</option>	
	      <option value="17">17</option>	
	      <option value="18">18</option>	
	      <option value="19">19</option>	
	      <option value="20">20</option>	
	      <option value="21">21</option>	
	      <option value="22">22</option>	
	      <option value="23">23</option>	
        </select>&nbsp;
        <input type="text" size="5" name="minute">&nbsp;•ª
      </span>
    </div>
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>‹xŒeŠÔ</b></span>
      <span><input type="text" size="5" name="break_time">&nbsp;•ª</span>
    </div>
  
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>ì‹Æ“à—e</b></span>
      <span> <textarea name="detail" rows="4" cols="40"></textarea></span>
    </div>
  
  </div>

  <div class="right"><button type=gbuttonh onclick="location.href='/attendance/complete.jsp'">“o˜^‚·‚é</button></div>

</body>