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
    String name = (String)request.getParameter("name");
    String mailaddress = (String)request.getParameter("mailaddress");
    String password = (String)request.getParameter("password");
    String id = (String)request.getParameter("id");
    String pass = "";
    for (int i=0; i<password.length(); i++){
      pass = pass + "*" ;
    }

    String nameCheck;
    if (name.length() >= 2 && name.length() <= 20){
      nameCheck = "SUCCESS";
    }else{
      nameCheck = "FAILURE";
    }

    String mailaddressCheck;
    if(mailaddress.length() <= 50){
      mailaddressCheck = "SUCCESS";
    }else{
      mailaddressCheck = "FAILURE";
    }

    String passwordCheck = checkLength(password);
    response.setCharacterEncoding("UTF-8");
    request.setAttribute("Password",password);
    request.setAttribute("Name",name);
    request.setAttribute("Mailaddress",mailaddress);
    request.setAttribute("Pass",pass);
    request.setAttribute("nameCheck",nameCheck);
    request.setAttribute("mailaddressCheck",mailaddressCheck);
    request.setAttribute("passwordCheck",passwordCheck);
    request.setAttribute("id",id);
    request.setAttribute("checkHanMailaddress",isHanStr(mailaddress) );
    request.setAttribute("checkHanPassword",isHanStr(password) );
    ServletContext context = this.getServletContext();
    if (name.length() >= 2 && name.length() <= 20 &&
        password.length() >= 8 && password.length() <= 64 && isHanStr(password) == "SUCCESS" &&
        mailaddress.length() <= 50 && isEmpty(mailaddress) == false && isHanStr(mailaddress) == "SUCCESS"){
      RequestDispatcher dispatcher = context.getRequestDispatcher("/staff_check.jsp");
      dispatcher.forward(request,response);
    }else{
      RequestDispatcher dispatcher = context.getRequestDispatcher("/staff_reg_error.jsp");
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

  public static String isHanStr(String s){
    if (!s.matches("^[0-9a-zA-Z]+$")) {
      return "FAILURE";
    }else{
      return "SUCCESS";
    }
  }

  public static String checkLength(String str) {
    if (str.length() >= 8 && str.length() <= 64){
      return "SUCCESS";
    }else{
      return "FAILURE";
    }
  }
}