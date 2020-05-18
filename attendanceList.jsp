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

  <p>
    <select name="name">
      <option value="">�Ј���I��</option>	
      <option value="�R�c ���Y">�R�c ���Y</option>	
      <option value="��� ��Y">��� ��Y</option>	
      <option value="���� ��Y">���� ��Y</option>	
    </select>
  </p>

  <select name="year">
    <option value="2020">2020</option>	
    <option value="2019">2019</option>	
    <option value="2018">2018</option>	
    <option value="2017">2017</option>	
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

  
  <div class="hidden_box right ">
    <label for="label1">�Αӂ�\������</label>
    <input type="checkbox" id="label1"/>
    <div class="hidden_show">    
      <p> <hr width="95%">

      <div class="parent">
        <div class="center"><font size="5">2020�N5��</font></div>
        <div class="right2"><a href="">���̓�&raquo;</a></div>
        <div class="left2"><a href>&laquo;�O�̓�</a></div>
      </div>

      <table border="1">
        <tr>
          <th width="70">���t</th><th width="70">�J�n</th><th width="70">�I��</th><th width="70">�x�e</th><th width="90">�Ζ�����</th><th width="120">��Ɠ��e</th><th width="120">�ҏW</th>
        </tr>
        <tr>
          <td>0501</td><td>9:00</td><td>18:00</td><td>1:00</td><td>8:00</td><td>����</td><td><button type=�gbutton�h onclick="location.href='/attendance/staff_edit.jsp'">�ҏW</button>&nbsp;<button type="button" onClick="disp()">�폜</button></td>
        </tr>
        <tr>
          <td>0502</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
        </tr>
        <tr>
          <td>0503</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
        </tr>
      </table> 

      <p class="right"><button type=�gbutton�h onclick="location.href='/attendance/attendanceEditor.jsp'">�Αӂ�o�^����</button></p>
      
    </div>
  </div>

</body>
</html>