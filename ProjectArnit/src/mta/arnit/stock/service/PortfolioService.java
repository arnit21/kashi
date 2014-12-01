package mta.arnit.stock.service;

import java.util.GregorianCalendar;

import mta.arnit.stock.model.Portfolio;
import mta.arnit.stock.model.Stock;

public class PortfolioService {
	public Portfolio getPortfolio(){
		Portfolio myPortfolio = new Portfolio();
		
		Stock s1 = new Stock();
		s1.setsymbol("PIH");
		s1.setAsk((float)(12.4));
		s1.setBid((float)(13.1));
		s1.setDate(new GregorianCalendar(2014, 10, 15).getTime());
		myPortfolio.addStock(s1);

		Stock s2 = new Stock();
		s2.setsymbol("AAL");
		s2.setAsk((float)(5.5));
		s2.setBid((float)(5.78));
		s2.setDate(new GregorianCalendar(2014, 10, 15).getTime());
		myPortfolio.addStock(s2);
	
		Stock s3 = new Stock();
		s3.setsymbol("CAAS");
		s3.setAsk((float)(31.5));
		s3.setBid((float)(31.2));
		s3.setDate(new GregorianCalendar(2014, 10, 15).getTime());
		myPortfolio.addStock(s3);

		return myPortfolio;
	}

}
