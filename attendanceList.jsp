<%@ page contentType="text/html;charset=Shift_JIS" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>�ΑӊǗ��V�X�e��TOP</title>

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
    // �m�F�_�C�A���O�̕\��
    if(window.confirm('�ΑӃf�[�^���폜����܂��B')){
      location.href = "/Attendance/AttendanceDelete?id=" + id; // OK���̏���
    }else{
      window.alert('�L�����Z������܂���'); // �x���_�C�A���O��\��(�L�����Z�����̏���)
    }
  }

</script>

</head>
<body>
  <header>
    <div class="blue">
      <div class="right white"><a href="/Attendance/StaffList"> �Ј���o�^���� </a></div>
      <div class="left white"><a href="/Attendance/TOP"> �ΑӊǗ��V�X�e�� </a></div>
    </div>
  </header>

  <span id="span1"></span>

  <form action = "/Attendance/AttendanceList" method="POST" >
    <select name="name">
      <option value="">�Ј���I��</option>
      <c:forEach items="${nameData}" var="dbdataLine">
        <option value=${dbdataLine.id}>${dbdataLine.name}</option>
      </c:forEach>
    </select>
    <br>
    <select name="year">
      <c:forEach items="${yearData}" var="dbdataLine">
        <option value=${dbdataLine}>${dbdataLine}</option>
      </c:forEach>
    </select>&nbsp;�N

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
    </select>&nbsp;��
    <div class="right"><input type="submit" value="�Αӂ�\������"></div>
  </form>

  <% if (request.getAttribute("Name") != null ) { %>
    <p> <hr width="95%">

    <div class="parent">
      <div class="center"><font size = 5><span id="span2"></span><%=request.getAttribute("Year") %>�N<span id="span3"></span><%= request.getAttribute("Month") %>��</font></div>
      <div class="right2"><a href="/Attendance/NextMonth?name=<%=request.getAttribute("Name") %>&month=<%=request.getAttribute("Month") %>&year=<%=request.getAttribute("Year")%>">���̌�&raquo;</a></div>
      <div class="left2"><a href ="/Attendance/PrevMonth?name=<%=request.getAttribute("Name") %>&month=<%=request.getAttribute("Month") %>&year=<%=request.getAttribute("Year")%>">&laquo;�O�̌�</a></div>
    </div>

    <table border="1">
      <tr>
        <th width="120">���t</th><th width="120">�J�n</th><th width="120">�I��</th><th width="120">�x�e</th><th width="120">�Ζ�����</th><th width="120">��Ɠ��e</th><th width="120">�ҏW</th>
      </tr>
    <c:forEach items="${dbdata}" var="dbdataLine">
      <tr>
        <td>${dbdataLine.date}</td>
        <td>${dbdataLine.startTime}</td>
        <td>${dbdataLine.endTime}</td>
        <td>${dbdataLine.breakTime}</td>
        <td>${dbdataLine.diffTime}</td>
        <td>${dbdataLine.detail}</td>
        <td><button type=�gbutton�h onclick="location.href='/Attendance/NameGet?name=<%=request.getAttribute("Name") %>&date=${dbdataLine.date}&id=${dbdataLine.ID}&start_time=${dbdataLine.startTime}&end_time=${dbdataLine.endTime}&break_time=${dbdataLine.breakTime}&detail=${dbdataLine.detail}'">�ҏW</button>&nbsp;<button type="button" onClick="disp('${dbdataLine.ID}')">�폜</button></td>
      </tr>
    </c:forEach>
    </table>

    <p class="right"><button type=�gbutton�h onclick="location.href='/Attendance/NameGet?name=<%=request.getAttribute("Name") %>&id=0'">�Αӂ�o�^����</button></p>

  <% } %>

</body>
</html>