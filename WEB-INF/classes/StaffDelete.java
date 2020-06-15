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

public class StaffDelete extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    String id = (String)request.getParameter("id");
    DBAccesser db = new DBAccesser();
    ResultSet rs = null;
    try{
      db.open();
      out.println("接続成功");
      out.println(id);
      String sql = "delete from employee where id ="+id;
      out.println(sql);
      db.execute(sql);
      //データ削除後、社員リストを再描画
      sql = "SELECT * FROM employee WHERE delete_flag = 'FALSE' " ;
      out.println(sql);
      rs = db.getResultSet(sql);
      List<DataBean> list = new ArrayList<DataBean>();
      while (rs.next()) {
        list.add(new DataBean(rs.getInt("id"),rs.getString("name"), rs.getString("mail")));
        request.setAttribute("dbdata", list);
      }
      getServletContext().getRequestDispatcher("/StaffList").forward(request, response);

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