import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IDGet extends HttpServlet{

  public void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    request.setCharacterEncoding("Shift_JIS");
    response.setCharacterEncoding("Shift_JIS");
    PrintWriter out = response.getWriter();
    String id = (String)request.getParameter("id");
    request.setAttribute("id",id);
    int ID = Integer.parseInt(request.getParameter("id"));
    if(ID==0) {
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