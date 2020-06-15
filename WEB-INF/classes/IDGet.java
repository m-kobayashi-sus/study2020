import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IDGet extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    String id = (String)request.getParameter("id");
    String name = (String)request.getParameter("name");
    String mail= (String)request.getParameter("mail");
    request.setAttribute("id",id);
    request.setAttribute("name",name);
    request.setAttribute("mail",mail);

    if(Integer.parseInt(request.getParameter("id"))==0) { //主キーで登録・編集を場合分け
      getServletContext().getRequestDispatcher("/staff_reg.jsp").forward(request, response);
    }else {
      getServletContext().getRequestDispatcher("/staff_edit.jsp").forward(request, response);
    }

  }

  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
    processRequest(request, response);
  }

}