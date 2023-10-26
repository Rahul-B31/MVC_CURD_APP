package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Student;
import in.ineuron.factory.StudentServiceFactory;
import in.ineuron.service.IStudentService;


// Mapping the URL
@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String status = null;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess( request,response);

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    doProcess(request, response);
	}
	public  void  doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		   String uri = request.getRequestURI();
		   System.out.println(uri);
		   RequestDispatcher rd=null;
		   String status = null;
		   IStudentService studentService = StudentServiceFactory.getIStudentService();

		   // Start The Application Using index.html
		   if(uri.endsWith("layout"))
		   {
			   rd = request.getRequestDispatcher("../layout.html");
			   rd.forward(request, response);
		   }

		   if(uri.endsWith("addform"))
		   {
			   String sname = request.getParameter("sname");
			   String sage = request.getParameter("sage");
			   String saddr = request.getParameter("saddr");

			   Student student = new Student();

			   student.setSname(sname);
			   student.setSage(Integer.parseInt(sage));
			   student.setSaddr(saddr);
			   status =  studentService.save(student);

			   if(status.equalsIgnoreCase("success"))
			   {
				   rd = request.getRequestDispatcher("../success.html");
				   rd.forward(request, response);
			   }
			   else {
				   rd = request.getRequestDispatcher("../fail.html");
				   rd.forward(request, response);
			   }


		   }
		   if(uri.endsWith("searchform"))
		   {
			   Student student = null;
			   String sid = request.getParameter("sid");
			   System.out.println(sid);
			   student =  studentService.findById(Integer.parseInt(sid));
			   if(student!=null)
			   {
				 PrintWriter out =  response.getWriter();
				 out.println("<html>");
				 out.println("<head><title>Student Record</title>");
				 out.println("</head>");
				 out.println("<body bgcolor='lightblue'>");
				 out.println("</br></br></br></br>");
				 out.println("<table align='center' border='1'> ");
				 out.println("<tr>");
				 out.println("<th>SID</th><th>SNAME</th><th>SAGE</th><th>SADDRESS</th>");
				 out.println("</tr>");
				 out.println("<td>"+student.getSid()+"</td>");
				 out.println("<td>"+student.getSname()+"</td>");
				 out.println("<td>"+student.getSage()+"</td>");
				 out.println("<td>"+student.getSaddr()+"</td>");
				 out.println("</table>");
				 out.println("</body>");
				 out.println("</html>");
				 out.close();

			   }
			   else
			   {
				  rd = request.getRequestDispatcher("../notfound.html");
				  rd.forward(request, response);
			   }


		   }
		   if(uri.endsWith("editform"))
		   {
			   Student student = null;
			   String sid = request.getParameter("sid");
			   student =  studentService.findById(Integer.parseInt(sid));

			   if(student!=null)
			   {
				   PrintWriter out = response.getWriter();
				   out.println("<html>");
				   out.println("<body bgcolor='lightblue'>");
				   out.println("<form action='./update' method='post'>");
				   out.println("<table align='center'>");
				   out.println("<tr>");
				   out.println("<th>SID</th><td>"+student.getSid()+"</td>");
				   out.println("</tr>");
				   out.println("<input type='hidden' name = 'sid' value='"+student.getSid()+"'/>");
				   out.println("<tr>");
				   out.println("<th>NAME</th><td><input type='text' name = 'sname' value='"+student.getSname()+"'/></td>");
				   out.println("</tr>");
				   out.println("<tr>");
				   out.println("<th>AGE</th><td><input type='text' name = 'sage' value='"+student.getSage()+"'/></td>");
				   out.println("</tr>");
				   out.println("<tr>");
				   out.println("<th>ADDRESS</th><td><input type='text' name = 'saddr' value='"+student.getSaddr()+"'/></td>");
				   out.println("</tr>");
				   out.println("<tr>");
				   out.println("<td></td><td><input type='submit' value='UPDATE'/></td>");
				   out.println("</tr>");

				   out.println("</form>");
				   out.println("</body>");
				   out.println("</html");


//

			   }
			   else {

				   rd = request.getRequestDispatcher("../notfound");
				   rd.forward(request, response);
			  }

		   }
		   if(uri.endsWith("update"))
		   {
			   String sid = request.getParameter("sid");
			   String sname = request.getParameter("sname");
			   String sage = request.getParameter("sage");
			   String saddr = request.getParameter("saddr");

			   Student student = new Student();

			   student.setSid(Integer.parseInt(sid));
			   student.setSname(sname);
			   student.setSage(Integer.parseInt(sage));
			   student.setSaddr(saddr);
			   status = studentService.updateById(student);

			   if(status.equalsIgnoreCase("success"))
			   {
				   rd = request.getRequestDispatcher("../success.html");
				   rd.forward(request, response);
			   }
			   else if(status.equalsIgnoreCase("fail")) {
				   rd = request.getRequestDispatcher("../fail.html");
				   rd.forward(request, response);
			   }
			   else {
				   rd = request.getRequestDispatcher("../notfound");
				   rd.forward(request, response);
			 }



		   }
		   if(uri.endsWith("deleteform"))
		   {
			   String sid = request.getParameter("sid");
			   Student student = null;
			   student = studentService.findById(Integer.parseInt(sid));

			   if(student!=null)
			   {
				   status =  studentService.deleteById(Integer.parseInt(sid));
				   if(status.equalsIgnoreCase("success"))
				   {
					   rd = request.getRequestDispatcher("../success.html");
					   rd.forward(request, response);
				   }
				   else if(status.equalsIgnoreCase("fail")) {
					   rd = request.getRequestDispatcher("../fail.html");
					   rd.forward(request, response);
				   }

			   }
			   else {
				   rd = request.getRequestDispatcher("../notfound");
				   rd.forward(request, response);
			  }
		   }



		}

}
