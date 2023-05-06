

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class add
 */
@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public add() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Resource(name="jdbc/TestDB")

    private DataSource datasource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		String s=request.getParameter("m");
		
		ServletContext context=getServletContext();
		String u2=(String)context.getAttribute("u");
		System.out.println(u2+"ok");
		
		
		try {
			Connection con=datasource.getConnection();
			PreparedStatement pt=con.prepareStatement("insert into chat values(NULL,'"+u2+"','"+s+"');");
			pt.executeUpdate();
			System.out.println("ok");
			
		}
		catch(Exception ec) {
			System.out.println(ec);
		}
		
		response.sendRedirect("/chat/msg");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
