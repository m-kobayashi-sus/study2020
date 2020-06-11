import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBAccesser;


public class StaffRegError extends HttpServlet {

  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("Shift_JIS");
	response.setCharacterEncoding("Shift_JIS");
    String name = (String)request.getParameter("name");
    String mailaddress = (String)request.getParameter("mailaddress");
    String password = (String)request.getParameter("password");
    String id = (String)request.getParameter("id");
    PrintWriter out = response.getWriter();
    response.setCharacterEncoding("UTF-8");

    ServletContext context = this.getServletContext();
    if (name.length() >= 2 && name.length() <= 20 &&
        password.length() >= 8 && password.length() <= 64 && isHanStr(password) == true &&
        mailaddress.length() <= 50 && isEmpty(mailaddress) == false && isHanStr(mailaddress) == true){
      DBAccesser db = new DBAccesser();
      try{
        db.open();
        out.println("接続成功");
        String sql = "";
        int ID = Integer.parseInt(request.getParameter("id"));
        if(ID==0) {
          sql = "insert into employee (name, mail, pass, delete_flag) values ('"+name+"', '"+mailaddress+"', '"+password+"', 'FALSE')";
        }else {
          sql = "update employee set name = '"+name+"', mail = '"+mailaddress+"', pass = '"+password+"' where id ="+ID;
        }
        out.println(sql);
        db.execute(sql);
        getServletContext().getRequestDispatcher("/staff_reg_complete.jsp").forward(request, response);

        }catch(Exception e){
          e.printStackTrace();
        }finally{
          try{
            db.close();
          }catch(Exception e){
            e.printStackTrace();
          }

        }
      RequestDispatcher dispatcher = context.getRequestDispatcher("/staff_reg_complete.jsp");
      dispatcher.forward(request,response);
    }else{
      request.setAttribute("id",id);
      RequestDispatcher dispatcher = context.getRequestDispatcher("/staff_reg_error.jsp");
      dispatcher.forward(request,response);
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  public static boolean isEmpty(String value) { //������null�A������0�̏ꍇ�����o
    if ( value == null || value.length() == 0 ){
      return true;
    }else{
      return false;
    }
  }

  public static boolean isHanStr(String s){ //���p�p�����݂̂̏ꍇtrue�Ԃ�
    if (!s.matches("^[0-9a-zA-Z]+$")) {
      return false;
    }else{
      return true;
    }
  }

}