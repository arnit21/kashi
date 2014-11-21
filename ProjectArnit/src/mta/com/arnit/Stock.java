package mta.com.arnit;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Stock extends HttpServlet {

		private String symbol;
		private float Ask;
		private float Bid;
		private Date date;
		private String htmlDetailsString = "unknown";
		
		
		public String getsymbol() {
			return symbol;
		}
		public void setsymbol(String symbol) {
			this.symbol = symbol;
		}
		public float getAsk() {
			return Ask;
		}
		public void setAsk(float Ask) {
			this.Ask = Ask;
		}
		public float getBid() {
			return Bid;
		}
		public void setBid(float Bid) {
			this.Bid = Bid;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		
		public String getHtmlDescription(){
			 htmlDetailsString = "<b>stock symbol</b>: "+getsymbol()+ "<b> Ask</b>: "+getAsk()+ "<b> Bid</b>: "+getBid()+ "<b> Date</b>: "+getDate();
			return htmlDetailsString;
		}
	
		public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

			Stock s1 = new Stock();
			s1.setsymbol("PIH");
			s1.setAsk((float)(12.4));
			s1.setBid((float)(13.1));
			s1.setDate(new Date());

			Stock s2 = new Stock();
			s2.setsymbol("AAL");
			s2.setAsk((float)(5.5));
			s2.setBid((float)(5.78));
			s2.setDate(new Date());
		
			Stock s3 = new Stock();
			s3.setsymbol("CAAS");
			s3.setAsk((float)(31.5));
			s3.setBid((float)(31.2));
			s3.setDate(new Date());
			 
			resp.setContentType("text/html");
			resp.getWriter().println(s1.getHtmlDescription()+"<br>"+s2.getHtmlDescription()+"<br>"+s3.getHtmlDescription());
			
		}	 
}


	
	
