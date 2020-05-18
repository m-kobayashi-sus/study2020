<%@ page contentType="text/html;charset=Shift_JIS" %>

<html>
<head>
<title>社員一覧画面</title>

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

table {
  border-collapse: collapse;
}
table th, table td {
  border: solid 1px black;
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

  <center><h2>社員一覧</h2></center>

  <table border="1">
    <tr>
      <th width="30">No</th><th width="250">名前</th><th width="250">メールアドレス</th><th width="100">編集</th>
    </tr>
    <tr>
      <td>1</td><td>山田 太郎</td><td>t.yamada@csv.co.jp</td><td><button type=“button” onclick="location.href='/attendance/staff_edit.jsp'">編集</button>&nbsp;<input type="button" value="削除" onClick="disp()"></td>
    </tr>
    <tr>
      <td>2</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
    </tr>
  </table> 

  <p class="right"><button type=“button” onclick="location.href='/attendance/staff_reg.jsp'">社員を登録する</button></p>

</body>
</html>