

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

import com.mysql.cj.protocol.Resultset;
import java.sql.Statement;


@WebServlet("/ch")
public class ch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ch() {
        super();
        // TODO Auto-generated constructor stub
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

		@Resource(name="jdbc/TestDB")

	     private DataSource datasource;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	
		response.setContentType("text/html");
		PrintWriter p=response.getWriter();
		
				
		
		
		try {
			String username=request.getParameter("t1");
			String password=request.getParameter("pass");
			// private DataSource datasource;
			
			Connection con=datasource.getConnection();
			//Statement st=con.createStatement();
			PreparedStatement pt=con.prepareStatement("select User,Password from user where User='"+username+"';");
			ResultSet rs=pt.executeQuery();
			
			ServletContext context=getServletContext();
			
			while(rs.next()) {
				String u=rs.getString("User");
				String pa=rs.getString("Password");
				System.out.println(u);
				
				if((u.equals(username)) && (pa.equals(password)))
				{
					context.setAttribute("user", username);
					response.sendRedirect("/chat/msg");
					
				}
				else {
					
					p.write("</br>username and password is incorrect");
				}
				
				
			}
			
			System.out.println("ok");
			
			}catch(Exception e)
			{
				System.out.println(e);
			}
		
		}
		 
	

}
