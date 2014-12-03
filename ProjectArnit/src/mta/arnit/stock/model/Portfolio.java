package mta.arnit.stock.model;

import java.util.Date;

/**
 * Contains two arrays: Stock and StockStatus
 * @author Arnit
 * @since 2014
 * 01/12/14
 */

public class Portfolio {
	private final static int MAX_PORTFOLIO_SIZE = 5; 
	/**
	 * Stock is array,the size of Stock is 5, stock array include stocks. 
	 */
	private Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	/**
	 * stocksStatus is array, the size of stocksStatus is 5, to save Status of Stocks
	 */
	private StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	/**
	 * The size of portfolio
	 */
	private int portfolioSize = 0;
	/**
	 * The title of the portfolio
	 */
	private String title = "<h1>Portfolio</h1>";
	
	/**
	 * to add stock to array stock
	 * @param stock
	 */
	public void addStock(Stock stock)
	{
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}
	
	/**
	 * @return stocks
	 */
	public Stock[] getStocks()
	{
		return stocks;
	}
	
	/**
	 * return title and html description of the stock in array stocks.
	 * @return 
	 */
	public String getHtmlString(){
		String to_return = title;
		for(int i=0; i < portfolioSize ; i++)
		{
			to_return += stocks[i].getHtmlDescription() + "<br>";	
		}
		return to_return;
	}
	
	/**
	 * inner class Stock Status 
	 * @author Arnit
	 * @since 2014
	 * 01/12/14
	 */
	public class StockStatus 
	{
		public final static int DO_NOTHING = 0;
		public final static int BUY = 1;
		public final static int SELL = 2;
		
		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
	}
	
}
	