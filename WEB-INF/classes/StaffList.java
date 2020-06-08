import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAccesser;

public class StaffList extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    try{
      db.open();
      out.println("接続成功");


      String sql = "SELECT * FROM employee WHERE delete_flag = 'FALSE'" ;
      out.println(sql);
      rs = db.getResultSet(sql);
      out.println(rs);
      while (rs.next()) {
        int id = rs.getInt("id");
        String name  = rs.getString("name");
        String mail = rs.getString("mail");

        request.setAttribute("mail",mail);
        request.setAttribute("name",name);
        request.setAttribute("id",id);

        getServletContext().getRequestDispatcher("/staff_list.jsp").forward(request, response);
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
