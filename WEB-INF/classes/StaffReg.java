import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaffReg extends HttpServlet {

  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("Shift_JIS");
    String name = (String)request.getParameter("name");             // �t�H�[������l���擾
    String mailaddress = (String)request.getParameter("mailaddress");
    String password = (String)request.getParameter("password");
    String id = (String)request.getParameter("id");
    String pass = "";

    for (int i=0; i<password.length(); i++){ //�p�X���[�h��*�̕ϊ�����
      pass = pass + "*" ;
    }

    response.setCharacterEncoding("UTF-8");
    request.setAttribute("Password",password); //�Z�b�V�����ɒl��ݒ�
    request.setAttribute("Name",name);
    request.setAttribute("Mailaddress",mailaddress);
    request.setAttribute("Pass",pass);
    request.setAttribute("id",id);
    ServletContext context = this.getServletContext();
    if (name.length() >= 2 && name.length() <= 20 &&
        password.length() >= 8 && password.length() <= 64 && isHanStr(password) == true &&
        mailaddress.length() <= 50 && isEmpty(mailaddress) == false && isHanStr(mailaddress) == true){
      RequestDispatcher dispatcher = context.getRequestDispatcher("/staff_check.jsp");  //�m�F��ʂ֑J��
      dispatcher.forward(request,response);
    }else{
      RequestDispatcher dispatcher = context.getRequestDispatcher("/staff_reg_error.jsp"); //�G���[��ʂ֑J��
      dispatcher.forward(request,response);
    }

  }

  protected void doGet(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  public static boolean isEmpty(String value) {
    if ( value == null || value.length() == 0 ){
      return true;
    }else{
      return false;
    }
  }

  public static boolean isHanStr(String s){
    if (!s.matches("^[0-9a-zA-Z]+$")) {
      return false;
    }else{
      return true;
    }
  }
}