<%@ page contentType="text/html;charset=Shift_JIS" %>

<html>
<head>
<title>勤怠管理システムTOP</title>

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

.parent {
  display: flex;   
  justify-content: center;
  position: relative;
}

.right2 {
  position: absolute;
  right: 0; 
}

.left2 {
  position: absolute;
  left: 0; 
}

table {
  border-collapse: collapse;
}
table th, table td {
  border: solid 1px black;
}

div.white a{ color: white; }

.hidden_box {
    margin:  0;
    padding: 0;
}

.hidden_box label {
    padding: 2px;
    font-weight: bold;
    border: solid 1px black;
    cursor :pointer;
}

.hidden_box label:hover {
    background: #efefef;
}

.hidden_box input {
    display: none;
}

.hidden_box .hidden_show {
    height: 0;
    padding: 0;
    overflow: hidden;
    opacity: 0;
    transition: 0.8s;
}

.hidden_box input:checked ~ .hidden_show {
    padding: 10px 0;
    height: auto;
    opacity: 1;
}

</style>

<script type="text/javascript">

function disp(){

	// 確認ダイアログの表示
	if(window.confirm('山田さんを削除しますか？\n登録済みの勤怠データも削除されます。')){
		location.href = ""; // OK時の処理
	}else{
		window.alert('キャンセルされました'); // 警告ダイアログを表示(キャンセル時の処理)
	}
}

</script>

</head>
<body>
  <header>
    <div class="blue">
      <div class="right white"><a href="/attendance/staff_list.jsp"> 社員を登録する </a></div>
      <div class="left white"><a href="/attendance/attendanceList.jsp"> 勤怠管理システム </a></div>
    </div>
  </header>

  <p>
    <select name="name">
      <option value="">社員を選択</option>	
      <option value="山田 太郎">山田 太郎</option>	
      <option value="鈴木 一郎">鈴木 一郎</option>	
      <option value="佐藤 二郎">佐藤 二郎</option>	
    </select>
  </p>

  <select name="year">
    <option value="2020">2020</option>	
    <option value="2019">2019</option>	
    <option value="2018">2018</option>	
    <option value="2017">2017</option>	
  </select>&nbsp;年

  <select name="month">
	<option value="01">01</option>	
	<option value="02">02</option>	
	<option value="03">03</option>	
	<option value="04">04</option>	
	<option value="05">05</option>	
	<option value="06">06</option>	
	<option value="07">07</option>	
	<option value="08">08</option>	
	<option value="09">09</option>	
	<option value="10">10</option>	
	<option value="11">11</option>	
	<option value="12">12</option>	
  </select>&nbsp;月

  
  <div class="hidden_box right ">
    <label for="label1">勤怠を表示する</label>
    <input type="checkbox" id="label1"/>
    <div class="hidden_show">    
      <p> <hr width="95%">

      <div class="parent">
        <div class="center"><font size="5">2020年5月</font></div>
        <div class="right2"><a href="">次の日&raquo;</a></div>
        <div class="left2"><a href>&laquo;前の日</a></div>
      </div>

      <table border="1">
        <tr>
          <th width="70">日付</th><th width="70">開始</th><th width="70">終了</th><th width="70">休憩</th><th width="90">勤務時間</th><th width="120">作業内容</th><th width="120">編集</th>
        </tr>
        <tr>
          <td>0501</td><td>9:00</td><td>18:00</td><td>1:00</td><td>8:00</td><td>実装</td><td><button type=“button” onclick="location.href='/attendance/staff_edit.jsp'">編集</button>&nbsp;<button type="button" onClick="disp()">削除</button></td>
        </tr>
        <tr>
          <td>0502</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
        </tr>
        <tr>
          <td>0503</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
        </tr>
      </table> 

      <p class="right"><button type=“button” onclick="location.href='/attendance/attendanceEditor.jsp'">勤怠を登録する</button></p>
      
    </div>
  </div>

</body>
</html>