import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAccesser;

public class StaffEdit extends HttpServlet {

  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("Shift_JIS");
    String name = (String)request.getParameter("name");
    String nameCheck;
    if (name.length() >= 2 && name.length() <= 20){ //名前の長さを判定
      nameCheck = "SUCCESS";
    }else{
      nameCheck = "FAILURE";
    }
    String mailaddress = (String)request.getParameter("mailaddress");
    String mailaddressCheck;
    if(mailaddress.length() <= 50){ //メールアドレスの長さを判定
      mailaddressCheck = "SUCCESS";
    }else{
      mailaddressCheck = "FAILURE";
    }
    String changePassword = (String)request.getParameter("change_password");
    String changePasswordCheck = checkLength(changePassword);
    String password = (String)request.getParameter("password");
    String passwordCheck = checkLength(password);
    String id = (String)request.getParameter("id");
    String pass = "";

    for (int i=0; i<changePassword.length(); i++){
      pass = pass + "*" ;
    }

    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    try{
      db.open();
      rs = db.getResultSet("SELECT * FROM employee WHERE id ="+id);
      while (rs.next()) {
        String DBPassword = rs.getString("pass");
        request.setAttribute("DBPassword", DBPassword);
      }
    }catch(Exception e){
        e.printStackTrace();
      }finally{
        try{
          db.close();
        }catch(Exception e){
          e.printStackTrace();
        }

      }

    response.setCharacterEncoding("UTF-8");
    request.setAttribute("Password",password);
    request.setAttribute("changePassword",changePassword);
    request.setAttribute("Name",name);
    request.setAttribute("nameCheck",nameCheck);
    request.setAttribute("mailaddressCheck",mailaddressCheck);
    request.setAttribute("passwordCheck",passwordCheck);
    request.setAttribute("changePasswordCheck",changePasswordCheck);
    request.setAttribute("Mailaddress",mailaddress);
    request.setAttribute("Pass",pass);
    request.setAttribute("id",id);
    request.setAttribute("checkHanMailaddress",isHanStr(mailaddress) );
    request.setAttribute("checkHanPassword",isHanStr(password) );
    request.setAttribute("checkHanChangePassword",isHanStr(changePassword) );
    ServletContext context = this.getServletContext();
    if (name.length() >= 2 && name.length() <= 20 &&
        changePassword.length() >= 8 && changePassword.length() <= 64 && password.length() >= 8 && password.length() <= 64 &&isHanStr(password) == "SUCCESS" &&isHanStr(changePassword) == "SUCCESS" &&
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

  public static String checkLength(String str) { //パスワードの長さ判定メソッド
    if (str.length() >= 8 && str.length() <= 64){
      return "SUCCESS";
    }else{
      return "FAILURE";
    }
  }

}