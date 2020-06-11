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

public class TOP extends HttpServlet{

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


