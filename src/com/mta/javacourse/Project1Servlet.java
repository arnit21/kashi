package com.mta.javacourse;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Project1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/�html");

		int num1;
		int num2;
		int num3;	

		num1 = 4;
		num2 = 3;
		num3 = 7;
		
		
		int var1 = num1 + num2;
		int var2 = num3;
		int result = (num1+num2) * num3;  

		String resultStr = new String("<h1>Result of " +"("+num1+"+"+num2+")"+"*"+num3+"="+var1+"*"+var2+"="+result+"</h1>");
				
		resp.getWriter().println(resultStr);

	}
}
