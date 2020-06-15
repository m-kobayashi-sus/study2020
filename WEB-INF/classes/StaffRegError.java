import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAccesser;


public class StaffRegError extends HttpServlet {

  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("Shift_JIS");
	response.setCharacterEncoding("Shift_JIS");
    String name = (String)request.getParameter("name");
    String nameCheck;
    if (name.length() >= 2 && name.length() <= 20){
      nameCheck = "SUCCESS";
    }else{
      nameCheck = "FAILURE";
    }
    String mailaddress = (String)request.getParameter("mailaddress");
    String mailaddressCheck;
    if(mailaddress.length() <= 50){
      mailaddressCheck = "SUCCESS";
    }else{
      mailaddressCheck = "FAILURE";
    }
    String password = (String)request.getParameter("password");
    String passwordCheck = checkLength(password);
    String id = (String)request.getParameter("id");
    PrintWriter out = response.getWriter();
    response.setCharacterEncoding("UTF-8");

    ServletContext context = this.getServletContext();
    if (name.length() >= 2 && name.length() <= 20 &&
        password.length() >= 8 && password.length() <= 64 && isHanStr(password) == "SUCCESS" &&
        mailaddress.length() <= 50 && isEmpty(mailaddress) == false && isHanStr(mailaddress) == "SUCCESS"){
      DBAccesser db = new DBAccesser();
      try{
        db.open();
        out.println("接続成功");
        String sql = "";
        int ID = Integer.parseInt(request.getParameter("id"));
        if(ID==0) {
          sql = "insert into employee (name, mail, pass, delete_flag) values ('"+name+"', '"+mailaddress+"', '"+password+"', 'FALSE')";
        }else {
          sql = "update employee set name = '"+name+"', mail = '"+mailaddress+"', pass = '"+password+"' where id ="+ID;
        }
        out.println(sql);
        db.execute(sql);
        getServletContext().getRequestDispatcher("/staff_reg_complete.jsp").forward(request, response);

        }catch(Exception e){
          e.printStackTrace();
        }finally{
          try{
            db.close();
          }catch(Exception e){
            e.printStackTrace();
          }

        }
      RequestDispatcher dispatcher = context.getRequestDispatcher("/staff_reg_complete.jsp");
      dispatcher.forward(request,response);
    }else{
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
      request.setAttribute("nameCheck",nameCheck);
      request.setAttribute("mailaddressCheck",mailaddressCheck);
      request.setAttribute("passwordCheck",passwordCheck);
      request.setAttribute("checkHanMailaddress",isHanStr(mailaddress) );
      request.setAttribute("checkHanPassword",isHanStr(password) );
      request.setAttribute("Password",password);
      request.setAttribute("Name",name);
      request.setAttribute("Mailaddress",mailaddress);
      request.setAttribute("id",id);
      RequestDispatcher dispatcher = context.getRequestDispatcher("/staff_reg_error.jsp");
      dispatcher.forward(request,response);
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
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