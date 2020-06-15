<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<title>勤怠登録-編集画面</title>

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
      <div class="right white"><a href="/Attendance/StaffList"> 社員を登録する </a></div>
      <div class="left white"><a href="/Attendance/TOP"> 勤怠管理システム </a></div>
    </div>
  </header>

  <h2><center>勤怠登録</center></h2>

  <form action = "/Attendance/AttendanceEditor?id=<%= request.getAttribute("id") %>" method="POST" >
  <div>
    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>名前</b></span>
      <span><input type="text" name="name" value=<%= request.getAttribute("name") %>></span>
    </div>

    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>日付</b></span>
      <span><input type="text" size="5" name="year"
      <% if (Integer.valueOf(String.valueOf(request.getAttribute("id"))) == 0 ) { %>
        value=<%= request.getAttribute("nowYear") %>> &nbsp;年
      <% }else{ %>
        value=<%= request.getAttribute("year") %>> &nbsp;年
      <% } %>

      <input type="text" size="5" name="month"
      <% if (Integer.valueOf(String.valueOf(request.getAttribute("id"))) == 0 ) { %>
        value=<%= request.getAttribute("nowMonth") %>> &nbsp;月
      <% }else{ %>
        value=<%= request.getAttribute("month") %>> &nbsp;月
      <% } %>

      <input type="text" size="5" name="day"
      <% if (Integer.valueOf(String.valueOf(request.getAttribute("id"))) == 0 ) { %>
        value=<%= request.getAttribute("nowDay") %>> &nbsp;日
      <% }else{ %>
        value=<%= request.getAttribute("day") %>> &nbsp;日
      <% } %>
      </span>
    </div>

    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>出勤時刻</b></span>
      <span>
        <select name="start_time">
          <% if (request.getAttribute("id") != null ) { %>
            <option value=<%= request.getAttribute("start_hour") %>><%= request.getAttribute("start_hour") %></option>
          <% } %>
          <option value="06">06</option>
          <option value="07">07</option>
          <option value="08">08</option>
          <option value="09">09</option>
          <option value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>
          <option value="13">13</option>
          <option value="14">14</option>
        </select>&nbsp;時
        <input type="text" size="5" name="start_minute" value=<%= request.getAttribute("start_minute") %>>&nbsp;分
      </span>
    </div>

  <div>
    <span class="col-2"></span>
    <span class="col-1"><b>退勤時刻</b></span>
    <span>
      <select name="end_time">
        <% if (request.getAttribute("id") != null ) { %>
          <option value=<%= request.getAttribute("end_hour") %>><%= request.getAttribute("end_hour") %></option>
        <% } %>
        <option value="15">15</option>
        <option value="16">16</option>
        <option value="17">17</option>
        <option value="18">18</option>
        <option value="19">19</option>
        <option value="20">20</option>
        <option value="21">21</option>
        <option value="22">22</option>
        <option value="23">23</option>
      </select>&nbsp;時
      <input type="text" size="5" name="end_minute" value=<%= request.getAttribute("end_minute") %>>&nbsp;分
    </span>
  </div>

    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>休憩時間</b></span>
      <span><input type="text" size="5" name="break_time" value=<%= request.getAttribute("break_time") %>>&nbsp;分</span>
    </div>

    <div>
      <span class="col-2"></span>
      <span class="col-1"><b>作業内容</b></span>
      <span> <textarea name="detail" rows="4" cols="40"  ><%= request.getAttribute("detail") %></textarea></span>
    </div>

  </div>

  <div class="right"><input type="submit" value="登録する"></div>
  </form>

</body>
</html>