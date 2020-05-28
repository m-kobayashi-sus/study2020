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

public class NextMonth extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name"); // �t�H�[������l���擾 
    String year = (String)request.getParameter("year");
    String month = (String)request.getParameter("month");
    int next = Integer.parseInt(month);
    next = next + 1; //�������w��
    month = String.valueOf(next);
    String hostname = "localhost"; //DB�̐ڑ��ɕK�v�Ȓl�̐ݒ�
    String dbname = "attendance"; 
    String username = "postgres"; 
    String password = "password"; 
    Connection conn = null;
    Statement stmt = null;
    
    try{
      Class.forName("org.postgresql.Driver"); //�h���C�o�̃��[�h
      conn = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":5433/" + dbname, username, password);
      out.println("�ڑ�����");
      stmt = conn.createStatement();
      //���s����SQL
      String sql = "SELECT * FROM attendance WHERE id = "+ name +" AND date >= '"+ year +"-"+ month +"-01' AND date <= '"+ year +"-"+ month +"-31'";

      out.println(sql);
      ResultSet rs = stmt .executeQuery(sql);
      //�f�[�^�̎擾
      while (rs.next()) {
        int id = rs.getInt("id");
        String start_time  = rs.getString("start_time");
        String date = rs.getString("date");
        String end_time = rs.getString("end_time");
        String break_time = rs.getString("break_time");
        String detail = rs.getString("detail");
        Boolean delete_flag = rs.getBoolean("delete_flag");
      
        String i = getTime(end_time,start_time,break_time);
        //���M����f�[�^�̐ݒ�
        request.setAttribute("Year",year);
        request.setAttribute("Name",name);                   
        request.setAttribute("Month",month);
        request.setAttribute("date",date);
        request.setAttribute("start_time",start_time);
        request.setAttribute("end_time",end_time);
        request.setAttribute("break_time",break_time);
        request.setAttribute("detail",detail);
        request.setAttribute("i",i);
 
        getServletContext().getRequestDispatcher("/attendanceList2.jsp").forward(request, response);
      }
      rs.close();

      }catch(Exception e) {
        e.printStackTrace();
      } finally {
        try{
          if(conn != null) {
            conn.close();
            }
          if (stmt != null) {
            stmt.close();
            }
          }catch (SQLException e) {
            e.printStackTrace();
          }
        }

    out.println("</body>");
    out.println("</html>");
  }
     
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
    processRequest(request, response);
  }


  public static String getTime(String end, String start, String b) throws Exception { //�Ζ����Ԃ��v�Z 
    String End = end.substring(0,2);
    int hour = Integer.parseInt(End);
    int break_time = Integer.parseInt(b);
    hour = hour - (break_time/60);
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
