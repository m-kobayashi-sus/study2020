import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAccesser;

public class AttendanceEditor extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Windows-31J");
    response.setCharacterEncoding("Windows-31J");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name");
    String year = (String)request.getParameter("year");
    String month = (String)request.getParameter("month");
    if(Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1) {
      getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }
    String day = (String)request.getParameter("day");
    List<String> days30MonthList = new ArrayList<String>(Arrays.asList("04","06","09","11"));
    List<String> days31MonthList = new ArrayList<String>((Arrays.asList("01","03","05","07","08","10","12")));
    String limitDay;
    if(days30MonthList.contains(month)) { //末日が30日の場合
      limitDay = "30";
    }else if(days31MonthList.contains(month)){ //末日が31日の場合
      limitDay = "31";
    }else if((Integer.parseInt(year)%4 == 0) && (Integer.parseInt(year)%100 != 0)){ //うるう年の2月だった場合
      limitDay = "29";
    }else{
      limitDay = "28";
    }

    if(Integer.parseInt(day) > Integer.parseInt(limitDay) || Integer.parseInt(day) < 1 || Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12 ) {
      getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }
    String startTime = (String)request.getParameter("start_time");
    String startMinute = (String)request.getParameter("start_minute");
    String endTime = (String)request.getParameter("end_time");
    String endMinute = (String)request.getParameter("end_minute");
    String breakTime = (String)request.getParameter("break_time");
    String detail = (String)request.getParameter("detail");
    int id = 0;

    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    if(detail.equals("")) {
      request.setAttribute("detail", detail);
      getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }

    try{
      db.open();
      out.println("接続成功");
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
        sql = "update attendance set date = '"+year+"-"+month+"-"+day+"', start_time = '"+startTime+":"+startMinute+"',end_time = '"+endTime+":"+endMinute+"',break_time = "+breakTime+",detail = '"+detail+"' where id ="+ ID;
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

