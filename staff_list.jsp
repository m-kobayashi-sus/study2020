<%@ page contentType="text/html;charset=Shift_JIS" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

  function disp(name, id){
  // 確認ダイアログの表示
    if(window.confirm(name+"さんのデータを削除")){
      location.href = "/Attendance/StaffDelete?id="+id; // OK時の処理
    }else{
      window.alert('キャンセルされました');
    }
}

</script>

</head>
<body>

  <header>
    <div class="blue">
      <div class="right white"><a href="/Attendance/StaffList"> 社員を登録する </a></div>
      <div class="left white"><a href="/Attendance/attendanceList.jsp"> 勤怠管理システム </a></div>
    </div>
  </header>

  <center><h2>社員一覧</h2></center>

  <table border="1">
    <tr>
      <th width="30">No</th><th width="250">名前</th><th width="250">メールアドレス</th><th width="100">編集</th>
    </tr>
      <c:forEach items="${dbdata}" var="dbdataLine">
        <tr>
          <td>${dbdataLine.id}</td>
          <td>${dbdataLine.name}</td>
          <td>${dbdataLine.mail}</td>
          <td><button type=“button” onclick="location.href='/Attendance/IDGet?id=${dbdataLine.id} '">編集</button>&nbsp;<input type="button" value="削除" onClick="disp('${dbdataLine.name}', '${dbdataLine.id}')"></td>
        </tr>
      </c:forEach>
  </table>

  <p class="right"><button type=“button” onclick="location.href='/Attendance/IDGet?id=0'">社員を登録する</button></p>


</body>
</html>