import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

      rs = db.getResultSet("SELECT * FROM attendance ");
      List<String> yearList = new ArrayList<String>();
      while (rs.next()) {
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

      String sql;
      if(prev == 0) {
        int prevYear = Integer.parseInt(year) - 1;
        prev = 12;
        nextMonth = 1;
        sql = "SELECT * FROM attendance WHERE employee_id = "+ name +" AND date >= '"+ prevYear +"-"+ prev +"-01' AND date <'"+ year +"-"+ nextMonth +"-01'";
        year = String.valueOf(prevYear);
      }else{
        nextMonth = prev + 1;
        sql = "SELECT * FROM attendance WHERE employee_id = "+ name +" AND date >= '"+ year +"-"+ prev +"-01' AND date <'"+ year +"-"+ nextMonth +"-01'";
      }

      out.println(sql);
      rs = db.getResultSet(sql);
      List<DataBean> workTimeList = new ArrayList<DataBean>();
      workTimeList = db.getWorkingTimeList(rs);
      request.setAttribute("dbdata", workTimeList);
      request.setAttribute("Year",year);
      request.setAttribute("Name",name);
      request.setAttribute("Month",prev);
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

}
