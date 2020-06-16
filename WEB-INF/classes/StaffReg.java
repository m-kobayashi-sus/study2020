import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import method.CheckForm;

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
    CheckForm cf = new CheckForm();
    String nameCheck = cf.checkName(name);
    String mailaddressCheck = cf.checkMailaddress(mailaddress);
    String passwordCheck = cf.checkPassword(password);
    response.setCharacterEncoding("UTF-8");
    request.setAttribute("Password",password);
    request.setAttribute("Name",name);
    request.setAttribute("Mailaddress",mailaddress);
    request.setAttribute("Pass",pass);
    request.setAttribute("nameCheck",nameCheck);
    request.setAttribute("mailaddressCheck",mailaddressCheck);
    request.setAttribute("passwordCheck",passwordCheck);
    request.setAttribute("id",id);
    request.setAttribute("checkHanMailaddress",cf.isHanStr(mailaddress) );
    request.setAttribute("checkHanPassword",cf.isHanStr(password) );
    ServletContext context = this.getServletContext();
    if(cf.checkForm(name, mailaddress, password) == true) {
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

}