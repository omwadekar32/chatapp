

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;

@WebServlet("/sigin")


public class sigin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public sigin() {
        super();
        // TODO Auto-generated constructor stub
    }
     @Resource(name="jdbc/TestDB")

     private DataSource datasource;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String a=request.getParameter("t");
		String b=request.getParameter("password");
		
		response.setContentType("text/html");
		PrintWriter p=response.getWriter();
		System.out.println(b);
		
		p.println("hello "+a);
		
		
		
		
		try {
			// private DataSource datasource;
			Connection con=datasource.getConnection();
			PreparedStatement pt=con.prepareStatement("insert into user values(NULL,'"+a+"','"+b+"');");
			pt.executeUpdate();
			System.out.println("ok");
			p.write("Your Username and Password Created sucessfully </br> Please go to login page");
			
			}catch(Exception e)
			{
				System.out.println(e);
			}
		
		
	}
	protected void doget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
