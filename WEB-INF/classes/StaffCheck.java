import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAccesser;

public class StaffCheck extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name");
    String mailaddress = (String)request.getParameter("mailaddress");
    String password = (String)request.getParameter("password");
    String id = (String)request.getParameter("id");


    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
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

  }

  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
    processRequest(request, response);
  }

}