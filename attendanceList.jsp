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

  function clickBtn1(){

    const name = document.form1.name;

    // �l(���l)���擾
    const num = name.selectedIndex;

    // �l(���l)����l(value�l)���擾
    const str = name.options[num].value;
    document.getElementById("span1").textContent = str; 
  }

  function clickBtn2(){
  
    const year = document.form2.year;

    // �l(���l)���擾
    const num = year.selectedIndex;

    // �l(���l)����l(value�l)���擾
    const str = year.options[num].value;
    document.getElementById("span2").textContent = str; 
  
  }
  
  function clickBtn3(){
  
    const month = document.form3.month;

    // �l(���l)���擾
    const num = month.selectedIndex;

    // �l(���l)����l(value�l)���擾
    const str = month.options[num].value;
    document.getElementById("span3").textContent = str; 
  
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
  
  <span id="span1"></span>
  
  <form name = "form1" >
    <select name="name">
      <option value="">�Ј���I��</option>	
      <option value="�R�c ���Y">�R�c ���Y</option>	
      <option value="��� ��Y">��� ��Y</option>	
      <option value="���� ��Y">���� ��Y</option>	
    </select>
  </form>

  <form name = "form2" style="display: inline">
    <select name="year">
      <option value="2020">2020</option>	
      <option value="2019">2019</option>	
      <option value="2018">2018</option>	
      <option value="2017">2017</option>	
    </select>&nbsp;�N
  </form>
  
  <form name = "form3" style="display: inline">
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
  </form>

  <div class = "right" onclick="clickBtn1(); clickBtn2();clickBtn3(); obj=document.getElementById('menu1').style; obj.display=(obj.display=='none')?'block':'none';" style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
  <a style="cursor:pointer;">�Αӂ�\������</a>
  </div>
  
  <div id="menu1" style="display:none;clear:both;">   
    <p> <hr width="95%">

    <div class="parent">
      <div class="center"><font size = 5><span id="span2"></span>�N<span id="span3"></span>��</font></div>
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

</body>
</html>