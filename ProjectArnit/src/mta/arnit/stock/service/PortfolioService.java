package mta.arnit.stock.service;

import java.util.GregorianCalendar;

import mta.arnit.stock.model.Portfolio;
import mta.arnit.stock.model.Stock;

/**
 * Service the Portfolio
 * @author Arnit
 * @since 2014
 * 01/12/2014
 */
public class PortfolioService {
	/**
	 * Creates a new instance of a portfolio 
	 * @return portfolio
	 */
	public Portfolio getPortfolio(){
		Portfolio myPortfolio = new Portfolio();
		
		Stock s1 = new Stock("PIH",12.4f,13.1f,new GregorianCalendar(2014, 10, 15).getTime());
		myPortfolio.addStock(s1);

		Stock s2 = new Stock("ALL",5.5f,5.78f,new GregorianCalendar(2014, 10, 15).getTime());
		myPortfolio.addStock(s2);
	
		Stock s3 = new Stock("CAAS",31.5f,31.2f,new GregorianCalendar(2014, 10, 15).getTime());
		myPortfolio.addStock(s3);
		
		myPortfolio.setTitle("Portfolio");

		return myPortfolio;
	}

}
