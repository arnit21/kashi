package mta.arnit.stock.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class stock with all the parameters to stock: symbol, ask, bid, date.
 * @author Arnit
 * @since 2014
 * 05/12/14
 */
public class Stock {

		protected String symbol;
		protected float ask;
		protected float bid;
		protected Date date;
		protected String htmlDetailsString = "unknown";
		protected SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		/**
		 * Stock constractor
		 */		
		public Stock(String s,float a,float b,Date d){
			setsymbol(s);
			setAsk(a);
			setBid(b);
			setDate(d);
		}
				
		/**
		 * Stock copy constractor
		 * @param s
		 */	
		public Stock (Stock s){
			this(s.getSymbol(),s.getAsk(),s.getBid(),new Date (s.getDate().getTime()));
		}
		
		/**
		 * Empty constractor.
		 */
		public Stock() {

		}

		public String getSymbol() {
			return symbol;
		}
		public void setsymbol(String symbol) {
			this.symbol = symbol;
		}
		public float getAsk() {
			return ask;
		}
		public void setAsk(float ask) {
			this.ask = ask;
		}
		public float getBid() {
			return bid;
		}
		public void setBid(float bid) {
			this.bid = bid;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		
		/**
		 * to get string with html description
		 * @return
		 */
		public String getHtmlDescription(){
			 htmlDetailsString = "<b>stock symbol</b>: "+getSymbol()+ "<b> Ask</b>: "+getAsk()+ "<b> Bid</b>: "+getBid()+ "<b> Date</b>: "+ sdf.format(getDate());
			return htmlDetailsString;		
		}

}


	
	
