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
  
  <span id="span1"></span>
  
  <form action = "/attendance/AttendanceList" method="POST" >
    <select name="name">
      <option value="">社員を選択</option>	
      <option value="1">CSV太郎</option>	
      <option value="2">CSV次郎</option>	
      <option value="3">CSV三郎</option>	
      <option value="4">CSV四郎</option>	
      <option value="5">CSV五郎</option>	
      <option value="6">CSV六郎</option>	
    </select>
    <br>
    <select name="year">
      <option value="2020">2020</option>	
      <option value="2019">2019</option>	
      <option value="2018">2018</option>	
      <option value="2017">2017</option>	
      <option value="2016">2016</option>	
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
    <div class="right"><input type="submit" value="表示する"></div>
  </form>


</body>
</html>