import db.DBAccesser;
import java.io.*;
import javax.servlet.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.*;
import java.util.*; 
import java.text.*; 

public class StaffCheck extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name"); // フォームから値を取得 
    String mailaddress = (String)request.getParameter("mailaddress");
    String password = (String)request.getParameter("password");

    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    try{
      db.open();
      out.println("接続成功");
    
      //実行するSQL
      String sql = sql = "insert into employee (name, mail, pass, delete_flag) values ('"+name+"', '"+mailaddress+"', '"+password+"', 'FALSE')";
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