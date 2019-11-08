import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/SQLQuery"})
public class SQLQuery extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<String> arr = new ArrayList<>();
            String query;
            Cookie[] cookies = request.getCookies();
            String tableName = cookies[0].getValue();
            int noOfColumns = Integer.parseInt(cookies[1].getValue());
            String pKey = request.getParameter("pKey");
            for(int i = 0; i < noOfColumns; i++){
                String name = request.getParameter("column" + (i + 1) + "name");
                String type = request.getParameter("column" + (i + 1) + "type");
                String length = request.getParameter("column" + (i + 1) + "length");
                query = name + " " + type + "(" + length + ")";
                if(pKey.equals("column" + (i + 1)))
                    query += " " + "primary key";
                arr.add(query);
            }
            int n = arr.size() - 1;
            query = "Create Table " + tableName + " (";
            for(int i = 0; i < n; i++){
                query += arr.get(i) + ", ";
            }
            query += arr.get(n) + ");";
            out.println("<!DOCTYPE html><html><head><title>SQL Query Details</title><body>");
            out.println("<h1>" + query + "</h1>");
            out.println("</body>");
            out.println("</html>");
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