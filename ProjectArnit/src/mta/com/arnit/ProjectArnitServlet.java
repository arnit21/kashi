package mta.com.arnit;
import java.io.IOException;
import javax.servlet.http.*;
import java.lang.Math;

@SuppressWarnings("serial")
public class ProjectArnitServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		//Ex02 
		
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
		
		//Ex03
		
		int radius = 50;
		double area = Math.pow(radius,2) * Math.PI; 
		
		int angleB = 30;
		int hypotenuse = 50;
		
		double opposite = Math.sin(Math.PI/180 * angleB) * hypotenuse;

		double caulc = Math.pow(20, 13);
		
		String line1 = new String("calculation 1: Area of circle with radius " + radius + " is:" + area + " square - cm ");
		String line2 = new String("calculation 2: Length of opposite where angle B is " + angleB + " degrees and Hypotenuse length is 50 cm is: " + opposite + "cm");
		String line3 = new String("calculation 3: Power of 20 with exp of 13 is " + caulc);
		
		String resultSt = line1 + "<br>" + line2 + "<br>" +line3;

		resp.getWriter().println(resultSt);

	}
}