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

public class PrevMonth extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    String name = (String)request.getParameter("name"); // �t�H�[������l���擾 
    String year = (String)request.getParameter("year");
    String month = (String)request.getParameter("month");
    int prev = Integer.parseInt(month) - 1; //�������w��
    month = String.valueOf(prev);
    if (name.equals("null")){
      getServletContext().getRequestDispatcher("/attendanceList.jsp").forward(request, response);
    }
    String hostname = "localhost"; //DB�̐ڑ��ɕK�v�Ȓl�̐ݒ�
    String dbname = "attendance"; 
    String username = "postgres"; 
    String password = "password"; 
    Statement stmt = null;
    Connection conn = null;
    
    try{
      DBConnect dc = new DBConnect();
      conn = dc.sql();
      out.println("�ڑ�����");
      stmt = conn.createStatement();
      int nextMonth = Integer.parseInt(month) + 1;
      //���s����SQL
      String sql = "SELECT * FROM attendance WHERE id = "+ name +" AND date >= '"+ year +"-"+ month +"-01' AND date <'"+ year +"-"+ nextMonth +"-01'";

      out.println(sql);
      ResultSet rs = stmt .executeQuery(sql);
      //�f�[�^�̎擾
      while (rs.next()) {
        int id = rs.getInt("id");
        String startTime  = rs.getString("start_time");
        String date = rs.getString("date");
        String endTime = rs.getString("end_time");
        String breakTime = rs.getString("break_time");
        String detail = rs.getString("detail");
        Boolean deleteFlag = rs.getBoolean("delete_flag");
      
        String diffTime = getTime(endTime,startTime,breakTime);
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
 
        getServletContext().getRequestDispatcher("/attendanceList.jsp").forward(request, response);
      }
      rs.close();

      }catch(Exception e){
        e.printStackTrace();
      }finally{
        try{
          if(conn != null){
            conn.close();
          }
          if(stmt != null){
            stmt.close();
          }
        }catch (SQLException e){
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


  public static String getTime(String end, String start, String b) throws Exception { //�Ζ����Ԃ��v�Z 
    String End = end.substring(0,2);
    int hour = Integer.parseInt(End);
    int breakTime = Integer.parseInt(b);
    hour = hour - (breakTime/60);
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

//DB�ڑ��N���X
class DBConnect {

  String hostname;
  String dbname;
  String username;
  String password;

  DBConnect(){
    hostname = "localhost"; 
    dbname = "attendance"; 
    username = "postgres";
    password = "password"; 
  }

  Connection sql() throws SQLException{
    Connection conn = null;
    try{
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":5433/" + dbname, username, password);
    }catch (Exception e){
      e.printStackTrace();
    }
    return conn;    
  }   
}