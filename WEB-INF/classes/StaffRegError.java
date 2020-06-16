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
import method.CheckForm;

public class StaffRegError extends HttpServlet {

  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("Shift_JIS");
	response.setCharacterEncoding("Shift_JIS");
	CheckForm cf = new CheckForm();
    String name = (String)request.getParameter("name");
    String nameCheck = cf.checkName(name);
    String mailaddress = (String)request.getParameter("mailaddress");
    String mailaddressCheck = cf.checkMailaddress(mailaddress);
    String password = (String)request.getParameter("password");
    String passwordCheck = cf.checkPassword(password);
    String id = (String)request.getParameter("id");
    PrintWriter out = response.getWriter();
    response.setCharacterEncoding("UTF-8");
    ServletContext context = this.getServletContext();

    if (cf.checkForm(name, mailaddress, password) == true){
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
      request.setAttribute("checkHanMailaddress",cf.isHanStr(mailaddress) );
      request.setAttribute("checkHanPassword",cf.isHanStr(password) );
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

}