package mta.arnit.stock.service;

import java.util.GregorianCalendar;

import mta.arnit.stock.exception.BalanceException;
import mta.arnit.stock.exception.PortfolioFullException;
import mta.arnit.stock.exception.StockAlreadyExistsException;
import mta.arnit.stock.exception.StockNotExistException;
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
	 * @throws PortfolioFullException 
	 * @throws StockAlreadyExistsException 
	 * @throws StockNotExistException 
	 * @throws BalanceException 
	 */
	public Portfolio getPortfolio() throws StockAlreadyExistsException, PortfolioFullException, BalanceException, StockNotExistException
	{
		
		Portfolio myPortfolio = new Portfolio();
		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.setBalance(10000);
		
		Stock s1 = new Stock("PIH",10f,8.5f,new GregorianCalendar(2014, 11, 15).getTime());
		myPortfolio.addStock(s1);

		Stock s2 = new Stock("AAL",30f,25.5f,new GregorianCalendar(2014, 11, 15).getTime());
		myPortfolio.addStock(s2);
	
		Stock s3 = new Stock("CAAS",20f,15.5f,new GregorianCalendar(2014, 11, 15).getTime());
		myPortfolio.addStock(s3);
		
		Stock s4 = new Stock("CAAS",20f,15.5f,new GregorianCalendar(2014, 11, 15).getTime());
		myPortfolio.addStock(s4);

		
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
}
