import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Column"})
public class Column extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.addHeader("tableName", request.getParameter("tableName"));
        response.addHeader("numOfColumn", request.getParameter("numOfColumn"));
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Column Details</title><body>");
            out.println("<form method='get' action='SQLQuery'>");
            
            int numOfColumn = Integer.parseInt(request.getParameter("numOfColumn"));
            Cookie c1 = new Cookie("tableName", request.getParameter("tableName"));
            Cookie c2 = new Cookie("numOfColumn", request.getParameter("numOfColumn"));
            response.addCookie(c1);
            response.addCookie(c2);
            for(int i = 0; i < numOfColumn; i++){
                out.println("<h2>Column " + (i + 1) + ":</h2>");
                out.println("<h2>Name:</h2>");
                out.println("<input type='text' name='column" + (i + 1) + "name'>");
                out.println("<h2>Type:</h2>");
                out.println("<select name='column" + (i + 1) + "type'>");
                out.println("<option value='varchar'>Varchar</option>");
                out.println("<option value='int'>Int</option></select>");
                out.println("<h2>Length:</h2>");
                out.println("<input type='number' name='column" + (i + 1) + "length'><br><hr>");
            }
            
            out.println("<h2>Primary Key:<br>");
            for(int i = 0; i < numOfColumn; i++)
                out.println("<input type='radio' name='pKey' value='column" + (i + 1) + "'>Column " + (i + 1));
            out.println("</h2><br><input type='submit'>");
            out.println("</form>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}