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

public class AttendanceList extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name"); // �t�H�[������l���擾 
    String year = (String)request.getParameter("year");
    String month = (String)request.getParameter("month");
    if (name.equals("")){
      getServletContext().getRequestDispatcher("/attendanceList.jsp").forward(request, response);
    }

    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    try{
      db.open();
      out.println("�ڑ�����");
      
      int nextMonth = Integer.parseInt(month) + 1;
      //���s����SQL
      String sql = "SELECT * FROM attendance WHERE employee_id = "+ name +" AND date >= '"+ year +"-"+ month +"-01' AND date <'"+ year +"-"+ nextMonth +"-01'";
      out.println(sql);
      rs = db.getResultSet(sql);
      out.println(rs);
      //�f�[�^�̎擾
      while (rs.next()) {
        int id = rs.getInt("id");
        String startTime  = rs.getString("start_time");
        String date = rs.getString("date");
        String endTime = rs.getString("end_time");
        String breakTime = rs.getString("break_time");
        String detail = rs.getString("detail");
        Boolean deleteFlag = rs.getBoolean("delete_flag");
      
        String diffTime = getTime(startTime,endTime,breakTime);
        //���M����f�[�^�̐ݒ�
        request.setAttribute("Year",year);
        request.setAttribute("Name",name);                   
        request.setAttribute("Month",month);
        request.setAttribute("date",date);
        request.setAttribute("start_time",startTime);
        request.setAttribute("end_time",endTime);
        request.setAttribute("break_time",breakTime);
        request.setAttribute("detail",detail);
        request.setAttribute("diffTime",diffTime);
        request.setAttribute("id",id);
 
        getServletContext().getRequestDispatcher("/attendanceList.jsp").forward(request, response);
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


