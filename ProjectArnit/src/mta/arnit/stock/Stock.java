package mta.arnit.stock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stock {

		private String symbol;
		private float Ask;
		private float Bid;
		private Date date;
		private String htmlDetailsString = "unknown";
		private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
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
			 htmlDetailsString = "<b>stock symbol</b>: "+getsymbol()+ "<b> Ask</b>: "+getAsk()+ "<b> Bid</b>: "+getBid()+ "<b> Date</b>: "+ sdf.format(getDate());
			return htmlDetailsString;
		}
		
}


	
	