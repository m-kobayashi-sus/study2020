<%@ page contentType="text/html;charset=Shift_JIS" %>

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

function disp(){

	// �m�F�_�C�A���O�̕\��
	if(window.confirm('�R�c������폜���܂����H\n�o�^�ς݂̋ΑӃf�[�^���폜����܂��B')){
		location.href = ""; // OK���̏���
	}else{
		window.alert('�L�����Z������܂���'); // �x���_�C�A���O��\��(�L�����Z�����̏���)
	}
}

</script>

</head>
<body>

  <header>
    <div class="blue">
      <div class="right white"><a href="/attendance/staff_list.jsp"> �Ј���o�^���� </a></div>
      <div class="left white"><a href="/attendance/attendanceList.jsp"> �ΑӊǗ��V�X�e�� </a></div>
    </div>
  </header>

  <center><h2>�Ј��ꗗ</h2></center>

  <table border="1">
    <tr>
      <th width="30">No</th><th width="250">���O</th><th width="250">���[���A�h���X</th><th width="100">�ҏW</th>
    </tr>
    <tr>
      <td>1</td><td>�R�c ���Y</td><td>t.yamada@csv.co.jp</td><td><button type=�gbutton�h onclick="location.href='/attendance/staff_edit.jsp'">�ҏW</button>&nbsp;<input type="button" value="�폜" onClick="disp()"></td>
    </tr>
    <tr>
      <td>2</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
    </tr>
  </table> 

  <p class="right"><button type=�gbutton�h onclick="location.href='/attendance/staff_reg.jsp'">�Ј���o�^����</button></p>

</body>
</html>