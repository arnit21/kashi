package mta.arnit.stock.service;

import java.util.GregorianCalendar;

import mta.arnit.stock.model.Portfolio;
import mta.arnit.stock.model.StockStatus;

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
	public Portfolio getPortfolio()
	{
		
		Portfolio myPortfolio = new Portfolio();
		myPortfolio.setTitle("Exercise 8 portfolio");
		myPortfolio.setBalance(10000);
		
		StockStatus s1 = new StockStatus("PIH",10f,8.5f,new GregorianCalendar(2014, 11, 15).getTime());
		myPortfolio.addStock(s1);

		StockStatus s2 = new StockStatus("AAL",30f,25.5f,new GregorianCalendar(2014, 11, 15).getTime());
		myPortfolio.addStock(s2);
	
		StockStatus s3 = new StockStatus("CAAS",20f,15.5f,new GregorianCalendar(2014, 11, 15).getTime());
		myPortfolio.addStock(s3);
		
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
}
