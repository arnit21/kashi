package mta.com.arnit;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet  {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Calendar c = Calendar.getInstance();
		c.set(2014,10,15);
		Date myDate = c.getTime();
		
		Stock s1 = new Stock();
		s1.setsymbol("PIH");
		s1.setAsk((float)(12.4));
		s1.setBid((float)(13.1));
		s1.setDate(myDate);

		Stock s2 = new Stock();
		s2.setsymbol("AAL");
		s2.setAsk((float)(5.5));
		s2.setBid((float)(5.78));
		s2.setDate(myDate);
	
		Stock s3 = new Stock();
		s3.setsymbol("CAAS");
		s3.setAsk((float)(31.5));
		s3.setBid((float)(31.2));
		s3.setDate(myDate);
		 
		resp.setContentType("text/html");
		resp.getWriter().println(s1.getHtmlDescription()+"<br>"+s2.getHtmlDescription()+"<br>"+s3.getHtmlDescription());
		
	}
}
