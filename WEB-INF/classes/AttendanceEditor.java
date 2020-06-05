import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAccesser;

public class AttendanceEditor extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name"); // �t�H�[������l���擾
    String year = (String)request.getParameter("year");
    String month = (String)request.getParameter("month");
    String day = (String)request.getParameter("day");
    String startTime = (String)request.getParameter("start_time");
    String startMinute = (String)request.getParameter("start_minute");
    String endTime = (String)request.getParameter("end_time");
    String endMinute = (String)request.getParameter("end_minute");
    String breakTime = (String)request.getParameter("break_time");
    String detail = (String)request.getParameter("detail");
    int id = 0;

    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    try{
      db.open();
      out.println("�ڑ�����");
      //���s����SQL
      rs = db.getResultSet("SELECT * FROM employee WHERE name ='" +name+"'" );
      while (rs.next()) {
        id = rs.getInt("id");
      }
      String sql = "";
      int ID = Integer.parseInt(request.getParameter("id"));
      out.println(ID);
      if(ID == 0 ){
        sql = "insert into attendance (employee_id, date, start_time, end_time, break_time, detail, delete_flag) values ("+id+",'"+year+"-"+month+"-"+day+"', '"+startTime+":"+startMinute+"','"+endTime+":"+endMinute+"',"+breakTime+",'"+detail+"','FALSE')";
      }else{
        sql = "update attendance set start_time = '"+startTime+":"+startMinute+"',end_time = '"+endTime+":"+endMinute+"',break_time = "+breakTime+",detail = '"+detail+"' where id ="+ ID;
      }
      out.println(sql);
      db.execute(sql);
      getServletContext().getRequestDispatcher("/complete.jsp").forward(request, response);

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

