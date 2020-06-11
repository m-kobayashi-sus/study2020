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

public class PrevMonth extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name");
    String year = (String)request.getParameter("year");
    String month = (String)request.getParameter("month");
    int nextMonth;
    int prev = Integer.parseInt(month) - 1;

    if (name.equals("")){
      getServletContext().getRequestDispatcher("/attendanceList.jsp").forward(request, response);
    }

    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    try{
      db.open();
      out.println("接続成功");
      String sql;

      rs = db.getResultSet("SELECT * FROM attendance ");
      List<String> list = new ArrayList<String>();
      while (rs.next()) {
    	if (!list.contains(rs.getString("date").substring(0, 4))) {
          list.add(rs.getString("date").substring(0,4));
    	}
        request.setAttribute("dbdata", list);
      }

      rs = db.getResultSet("SELECT * FROM employee ");
      List<DataBean> list2 = new ArrayList<DataBean>();
      while (rs.next()) {
        list2.add(new DataBean(rs.getString("name"),rs.getInt("id")));
        request.setAttribute("dbdata2", list2);
      }

      if(prev == 0) {
        int prevYear = Integer.parseInt(year) - 1;
        prev = 12;
        nextMonth = 1;
        sql = "SELECT * FROM attendance WHERE employee_id = "+ name +" AND date >= '"+ prevYear +"-"+ prev +"-01' AND date <'"+ year +"-"+ nextMonth +"-01'";
        year = String.valueOf(prevYear);
      }else {
        nextMonth = prev + 1;
        sql = "SELECT * FROM attendance WHERE employee_id = "+ name +" AND date >= '"+ year +"-"+ prev +"-01' AND date <'"+ year +"-"+ nextMonth +"-01'";
      }

      out.println(sql);
      rs = db.getResultSet(sql);
      List<DataBean> list3 = new ArrayList<DataBean>();
      while (rs.next()) {
        int id = rs.getInt("id");
        String startTime  = rs.getString("start_time");
        String date = rs.getString("date");
        String endTime = rs.getString("end_time");
        String breakTime = rs.getString("break_time");
        String detail = rs.getString("detail");
        Boolean deleteFlag = rs.getBoolean("delete_flag");
        String diffTime = getTime(startTime,endTime,breakTime);
        list3.add(new DataBean(rs.getInt("id"), rs.getString("date"), rs.getString("start_time").substring(0,5), rs.getString("end_time").substring(0,5), rs.getInt("break_time"), diffTime.substring(0,5), rs.getString("detail")));

        request.setAttribute("Year",year);
        request.setAttribute("Name",name);
        request.setAttribute("Month",prev);
        request.setAttribute("date",date);
        request.setAttribute("start_time",startTime);
        request.setAttribute("end_time",endTime);
        request.setAttribute("break_time",breakTime);
        request.setAttribute("detail",detail);
        request.setAttribute("diffTime",diffTime);
        request.setAttribute("id",id);
        request.setAttribute("dbdata3", list3);
      }
      if(list3.size() == 0) {
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
    Date startDate = formatter.parse(start); // �J�n����
    Date endDate = formatter.parse(hour+":00:00"); // �I������


    long diffTime = endDate.getTime() - startDate.getTime();

    SimpleDateFormat timeFormatter = new SimpleDateFormat ("HH:mm:ss");
    timeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));

    String diffTimeStr = timeFormatter.format(new Date(diffTime));

    return diffTimeStr;
  }
}
