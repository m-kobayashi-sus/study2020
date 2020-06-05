<%@ page contentType="text/html;charset=Shift_JIS" %>

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

  function disp(){
    // �m�F�_�C�A���O�̕\��
    if(window.confirm('�ΑӃf�[�^���폜����܂��B')){
      location.href = "/Attendance/AttendanceDelete?id=<%=request.getAttribute("id") %>"; // OK���̏���
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
      <div class="left white"><a href="/Attendance/attendanceList.jsp"> �ΑӊǗ��V�X�e�� </a></div>
    </div>
  </header>

  <span id="span1"></span>

  <form action = "/Attendance/AttendanceList" method="POST" >
    <select name="name">
      <option value="">�Ј���I��</option>
      <option value="1">CSV���Y</option>
      <option value="2">CSV���Y</option>
      <option value="3">CSV�O�Y</option>
      <option value="4">CSV�l�Y</option>
      <option value="5">CSV�ܘY</option>
      <option value="6">CSV�Z�Y</option>
    </select>
    <br>
    <select name="year">
      <option value="2020">2020</option>
      <option value="2019">2019</option>
      <option value="2018">2018</option>
      <option value="2017">2017</option>
      <option value="2016">2016</option>
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
      <tr>
        <td><%= request.getAttribute("date") %></td><td><%= request.getAttribute("start_time") %></td><td><%= request.getAttribute("end_time") %><br></td><td>  <%= request.getAttribute("break_time") %></td><td><%= request.getAttribute("diffTime") %>:00</td><td><%= request.getAttribute("detail") %></td><td><button type=�gbutton�h onclick="location.href='/Attendance/NameGet?year=<%=request.getAttribute("Year") %>&name=<%=request.getAttribute("Name") %>&month=<%=request.getAttribute("Month") %>&date=<%=request.getAttribute("date") %>&id=<%=request.getAttribute("id")%>'">�ҏW</button>&nbsp;<button type="button" onClick="disp()">�폜</button></td>
      </tr>
      <tr>
        <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
      </tr>
    </table>

    <p class="right"><button type=�gbutton�h onclick="location.href='/Attendance/NameGet?name=<%=request.getAttribute("Name") %>&id=0'">�Αӂ�o�^����</button></p>
<%=request.getAttribute("id") %>
  <% } %>

</body>
</html>