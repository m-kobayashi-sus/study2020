import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DataBean;
import db.DBAccesser;

public class AttendanceList extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Windows-31J");
    response.setCharacterEncoding("Windows-31J");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name");
    String year = (String)request.getParameter("year");
    String month = (String)request.getParameter("month");
    if (name.equals("")){
      getServletContext().getRequestDispatcher("/attendanceList.jsp").forward(request, response);
    }

    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    try{
      db.open();
      out.println("接続成功");

      rs = db.getResultSet("SELECT * FROM attendance ");
      List<String> yearList = new ArrayList<String>();
      while(rs.next()) {
        if (!yearList.contains(rs.getString("date").substring(0, 4))) {
          yearList.add(rs.getString("date").substring(0,4));
        }
        request.setAttribute("yearData", yearList);
      }

      rs = db.getResultSet("SELECT * FROM employee ");
      List<DataBean> nameList = new ArrayList<DataBean>();
      while (rs.next()) {
        nameList.add(new DataBean(rs.getString("name"),rs.getInt("id")));
        request.setAttribute("nameData", nameList);
       }

      int nextMonth = Integer.parseInt(month) + 1;
      String sql = "";
      if(nextMonth == 13) { //選択が12月だった場合に範囲の指定を一月に変更
        int nextYear = Integer.parseInt(year) + 1;
        nextMonth = 1;
        sql = "SELECT * FROM attendance WHERE employee_id = "+ name +" AND date >= '"+ year +"-"+ month +"-01' AND date <'"+ nextYear +"-"+ nextMonth +"-01'";
      }else {
        sql = "SELECT * FROM attendance WHERE employee_id = "+ name +" AND date >= '"+ year +"-"+ month +"-01' AND date <'"+ year +"-"+ nextMonth +"-01'";
      }
        out.println(sql);
      rs = db.getResultSet(sql);
      List<DataBean> workTimeList = new ArrayList<DataBean>();
      while (rs.next()) {
        int id = rs.getInt("id");
        String startTime  = rs.getString("start_time");
        String endTime = rs.getString("end_time");
        String breakTime = rs.getString("break_time");
        Boolean deleteFlag = rs.getBoolean("delete_flag");
        String diffTime = getTime(startTime, endTime, breakTime);
        workTimeList.add(new DataBean(rs.getInt("id"), rs.getString("date"), rs.getString("start_time").substring(0,5), rs.getString("end_time").substring(0,5), rs.getInt("break_time"), diffTime.substring(0,5), rs.getString("detail")));
        request.setAttribute("Year",year);
        request.setAttribute("Name",name);
        request.setAttribute("Month",month);
        request.setAttribute("id",id);
        request.setAttribute("dbdata", workTimeList);      }
      if(workTimeList.size() == 0) {
    	getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
      }
      getServletContext().getRequestDispatcher("/attendanceList.jsp").forward(request, response);

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


  public static String getTime(String start, String end, String breakTime) throws Exception { //�Ζ����Ԃ��v�Z
    int hour = Integer.parseInt(end.substring(0,2));
    hour = hour - (Integer.parseInt(breakTime)/60);
    SimpleDateFormat formatter = new SimpleDateFormat ("HH:mm:ss");
    Date startDate = formatter.parse(start);
    Date endDate = formatter.parse(hour+":00:00");


    long diffTime = endDate.getTime() - startDate.getTime();

    SimpleDateFormat timeFormatter = new SimpleDateFormat ("HH:mm:ss");
    timeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));

    String diffTimeStr = timeFormatter.format(new Date(diffTime));

    return diffTimeStr;
  }
}


