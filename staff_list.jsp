<%@ page contentType="text/html;charset=Shift_JIS" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>�Ј��ꗗ���</title>

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
  // �m�F�_�C�A���O�̕\��
    if(window.confirm(name+"����̃f�[�^���폜")){
      location.href = "/Attendance/StaffDelete?id="+id; // OK���̏���
    }else{
      window.alert('�L�����Z������܂���');
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

  <center><h2>�Ј��ꗗ</h2></center>

  <table border="1">
    <tr>
      <th width="30">No</th><th width="250">���O</th><th width="250">���[���A�h���X</th><th width="100">�ҏW</th>
    </tr>
      <c:forEach items="${dbdata}" var="dbdataLine">
        <tr>
          <td>${dbdataLine.id}</td>
          <td>${dbdataLine.name}</td>
          <td>${dbdataLine.mail}</td>
          <td><button type=�gbutton�h onclick="location.href='/Attendance/IDGet?id=${dbdataLine.id} '">�ҏW</button>&nbsp;<input type="button" value="�폜" onClick="disp('${dbdataLine.name}', '${dbdataLine.id}')"></td>
        </tr>
      </c:forEach>
  </table>

  <p class="right"><button type=�gbutton�h onclick="location.href='/Attendance/IDGet?id=0'">�Ј���o�^����</button></p>


</body>
</html>