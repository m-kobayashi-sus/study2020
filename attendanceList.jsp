<%@ page contentType="text/html;charset=Shift_JIS" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

  function disp(id){
    // 確認ダイアログの表示
    if(window.confirm('勤怠データが削除されます。')){
      location.href = "/Attendance/AttendanceDelete?id=" + id; // OK時の処理
    }else{
      window.alert('キャンセルされました'); // 警告ダイアログを表示(キャンセル時の処理)
    }
  }

</script>

</head>
<body>
  <header>
    <div class="blue">
      <div class="right white"><a href="/Attendance/StaffList"> 社員を登録する </a></div>
      <div class="left white"><a href="/Attendance/TOP"> 勤怠管理システム </a></div>
    </div>
  </header>

  <span id="span1"></span>

  <form action = "/Attendance/AttendanceList" method="POST" >
    <select name="name">
      <option value="">社員を選択</option>
      <c:forEach items="${nameData}" var="dbdataLine">
        <option value=${dbdataLine.id}>${dbdataLine.name}</option>
      </c:forEach>
    </select>
    <br>
    <select name="year">
      <c:forEach items="${yearData}" var="dbdataLine">
        <option value=${dbdataLine}>${dbdataLine}</option>
      </c:forEach>
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
    <div class="right"><input type="submit" value="勤怠を表示する"></div>
  </form>

  <% if (request.getAttribute("Name") != null ) { %>
    <p> <hr width="95%">

    <div class="parent">
      <div class="center"><font size = 5><span id="span2"></span><%=request.getAttribute("Year") %>年<span id="span3"></span><%= request.getAttribute("Month") %>月</font></div>
      <div class="right2"><a href="/Attendance/NextMonth?name=<%=request.getAttribute("Name") %>&month=<%=request.getAttribute("Month") %>&year=<%=request.getAttribute("Year")%>">次の月&raquo;</a></div>
      <div class="left2"><a href ="/Attendance/PrevMonth?name=<%=request.getAttribute("Name") %>&month=<%=request.getAttribute("Month") %>&year=<%=request.getAttribute("Year")%>">&laquo;前の月</a></div>
    </div>

    <table border="1">
      <tr>
        <th width="120">日付</th><th width="120">開始</th><th width="120">終了</th><th width="120">休憩</th><th width="120">勤務時間</th><th width="120">作業内容</th><th width="120">編集</th>
      </tr>
    <c:forEach items="${dbdata}" var="dbdataLine">
      <tr>
        <td>${dbdataLine.date}</td>
        <td>${dbdataLine.startTime}</td>
        <td>${dbdataLine.endTime}</td>
        <td>${dbdataLine.breakTime}</td>
        <td>${dbdataLine.diffTime}</td>
        <td>${dbdataLine.detail}</td>
        <td><button type=“button” onclick="location.href='/Attendance/NameGet?name=<%=request.getAttribute("Name") %>&date=${dbdataLine.date}&id=${dbdataLine.ID}&start_time=${dbdataLine.startTime}&end_time=${dbdataLine.endTime}&break_time=${dbdataLine.breakTime}&detail=${dbdataLine.detail}'">編集</button>&nbsp;<button type="button" onClick="disp('${dbdataLine.ID}')">削除</button></td>
      </tr>
    </c:forEach>
    </table>

    <p class="right"><button type=“button” onclick="location.href='/Attendance/NameGet?name=<%=request.getAttribute("Name") %>&id=0'">勤怠を登録する</button></p>

  <% } %>

</body>
</html>