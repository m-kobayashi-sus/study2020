import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class StaffReg extends HttpServlet {
  
  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
  request.setCharacterEncoding("Shift_JIS");
    String name = (String)request.getParameter("name");             // フォームから値を取得 
    String mailaddress = (String)request.getParameter("mailaddress");
    String password = (String)request.getParameter("password");
    String pass = "";
 
    for (int i=0; i<password.length(); i++){ //パスワードを*の変換する
      pass = pass + "*" ;
    }                       

    response.setCharacterEncoding("UTF-8");
    HttpSession session = request.getSession();
    session.setAttribute("Password",password); //セッションに値を設定
    session.setAttribute("Name",name);                   
    session.setAttribute("Mailaddress",mailaddress);
    session.setAttribute("Pass",pass);              
    ServletContext context = this.getServletContext();
    if (name.length() >= 2 && name.length() <= 20 &&
        password.length() >= 8 && password.length() <= 64 && isHanStr(password) == true && 
        mailaddress.length() <= 50 && isEmpty(mailaddress) == false && isHanStr(mailaddress) == true){
      RequestDispatcher dispatcher = context.getRequestDispatcher("/staff_check.jsp");  //確認画面へ遷移
      dispatcher.forward(request,response);
    }else{
      RequestDispatcher dispatcher = context.getRequestDispatcher("/staff_reg_error.jsp"); //エラー画面へ遷移
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