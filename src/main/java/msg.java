

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class msg
 */
@WebServlet("/msg")
public class msg extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public msg() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request,response);
	}

	@Resource(name="jdbc/TestDB")

    private DataSource datasource;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String s=request.getParameter("m");
		System.out.println(s);
		response.setContentType("text/html");
		PrintWriter p=response.getWriter();
		
		p.println("<style>"
				+ ".box {"
				+ "			border: 2px solid black;"
				+ "			padding: 30px;"
				+ "		}"
				+ ".center {"
				+ "			display: flex;"
				+ "			justify-content: center;"
				+ "			align-items: center;"
				+ "			background-color: cyan;"
				+ "		}"
				+ "</style>");
		p.println("<div class=box><h1>Chatapp</h1></div>"
				+ "<div class=center>");
		ServletContext context=getServletContext();
		String u1=(String)context.getAttribute("user");
		System.out.println(u1+"here");
		
		context.setAttribute("u", u1);
		
		try {
		Connection con=datasource.getConnection();
		
	
		PreparedStatement update=con.prepareStatement("select * from chat;");
		ResultSet res=update.executeQuery();
		
		
		while(res.next()) {
			
			String us=res.getString("User");
			String ms=res.getString("Msg");
			
			p.println("</br>"+us+":</br>"+ms+"</br>");
			
			
		}
		
		p.println("<form action=/chat/add>");
		
		p.println("</br><input type=text name=m></br>");
		p.println("<input type=submit value=Send></br>");
		p.println("</form>");
		p.println("</div>");
		p.println("</body>");
		p.println("</html>");
		
		}
		catch(Exception ex)
		{
			
			System.out.println(ex);
		}
		
		
	}

}
