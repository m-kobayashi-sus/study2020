import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAccesser;

public class NameGet extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Windows-31J");
    response.setCharacterEncoding("Windows-31J");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name");
    String date = (String)request.getParameter("date");
    String startTime = (String)request.getParameter("start_time");
    String endTime = (String)request.getParameter("end_time");
    String breakTime = (String)request.getParameter("break_time");
    String detail = (String)request.getParameter("detail");
    String id = (String)request.getParameter("id");
    String year = "";
    String month = "";
    String day = "";
    String startHour = "";
    String startMinute = "";
    String endHour = "";
    String endMinute = "";

    //yyyy:MM:dd HH:mmの形からデータを成形
    if(date != null){
      day = date.substring(8,10);
      year = date.substring(0,4);
      month = date.substring(5,7);
    }

    if(startTime != null){
      startHour = startTime.substring(0,2);
      startMinute = startTime.substring(3,5);
    }

    if(endTime != null){
      endHour = endTime.substring(0,2);
      endMinute = endTime.substring(3,5);
    }

    if(breakTime == null) {
      breakTime = "";
    }

    if(detail == null) {
      detail = "";
    }

    request.setAttribute("year",year);
    request.setAttribute("month",month);
    request.setAttribute("day",day);
    request.setAttribute("id",id);
    request.setAttribute("start_hour",startHour);
    request.setAttribute("start_minute",startMinute);
    request.setAttribute("end_hour",endHour);
    request.setAttribute("end_minute",endMinute);
    request.setAttribute("break_time",breakTime);
    request.setAttribute("detail",detail);
    //閲覧している日時を取得
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    request.setAttribute("nowYear",sdf.format(c.getTime()));
    sdf.applyPattern("MM");
    request.setAttribute("nowMonth",sdf.format(c.getTime()));
    sdf.applyPattern("dd");
    request.setAttribute("nowDay",sdf.format(c.getTime()));

    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    try{
      db.open();
      out.println("接続成功");
      String sql = "SELECT * FROM employee WHERE id = "+ name;
      out.println(sql);
      rs = db.getResultSet(sql);

      while (rs.next()) {
        String n  = rs.getString("name");
        request.setAttribute("name",n);
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