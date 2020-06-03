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

public class NameGet extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name"); // フォームから値を取得 
    String year = (String)request.getParameter("year"); 
    if(year == null){
      year = "";
    }
    String month = (String)request.getParameter("month");
    if(month == null){
      month = "";
    }
    String date = (String)request.getParameter("date");
    String day = "";
    if(date != null){
      day = date.substring(8,10);
    }
    String id = (String)request.getParameter("id");

    request.setAttribute("year",year); 
    request.setAttribute("month",month); 
    request.setAttribute("day",day); 
    request.setAttribute("id",id); 
 
    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    try{
      db.open();
      out.println("接続成功a");
      
      //実行するSQL
      String sql = "SELECT * FROM employee WHERE id = "+ name;
      out.println(sql);
      rs = db.getResultSet(sql);
      //データの取得
      while (rs.next()) {
        String Name  = rs.getString("name");
        request.setAttribute("name",Name);                   
        getServletContext().getRequestDispatcher("/attendanceEditor.jsp").forward(request, response);
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
    
  }
     
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
    processRequest(request, response);
  }

}