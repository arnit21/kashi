package mta.arnit.stock.model;

import java.util.Date;

/**
 * description of protfolio
 * socks- array of stocks, stockstatus- array of status for each stock,
 * portfolioSize is the size of stocks,title is the name of the stock portfolio.
 * @author Arnit
 * @since 2014
 * 01/12/14
 */
public class Portfolio {
	private final static int MAX_PORTFOLIO_SIZE = 5; 
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	private String title;

	/**
	 *  Portfolio constractor
	 */
	public Portfolio() {
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
	}

	/**
	 * Portfolio copy constractor 
	 * @param p
	 */
	public Portfolio(Portfolio p){
		this();

		for(int i=0; i<p.portfolioSize;i++){
			this.stocks[i]=new Stock(p.stocks[i]);
		}
		for(int i = 0; i < portfolioSize; i++){
			this.stocksStatus[i] = new StockStatus(p.stocksStatus[i]); 
		}
		setPortfolioSize(p.portfolioSize);
		setTitle(p.title);
	}

	public Stock[] getStocks()
	{
		return stocks;
	}
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}
	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}
	public int getPortfolioSize() {
		return portfolioSize;
	}
	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

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
	 * to remove the fist stock from array stock in the Portfolio
	 * @param stocks
	 */	
	public void removeFirstStock(Portfolio p)
	{
		for(int i = 0; i < portfolioSize; i++)
		{
			stocks[i] = stocks[i + 1];
		}
		portfolioSize--;
	}

	/**
	 * return title and html description of the stock in array stocks.
	 * @return 
	 */
	public String getHtmlString(){
		String to_return = "<h1>" + getTitle() + "</h1>";
		for(int i=0; i < getPortfolioSize() ; i++)
		{
			to_return += stocks[i].getHtmlDescription() + "<br>";	
		}
		return to_return;
	} 

	/**
	 * inner class Stock Status BUY/SELL/DO_NOTHING with stock.
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

		/**
		 * StockStatus empty constractor
		 */
		public StockStatus(String s,float cB,float cA,Date d,int r, int sQ) {
			symbol = s;
			currentBid = cB;
			currentAsk = cA;
			date = d;
			recommendation = r;
			stockQuantity = sQ;
		}

		/**
		 * StockStatus copy constractor
		 * @param stockStatus
		 */
		public StockStatus(StockStatus sS)
		{
			this(sS.getSymbol(),sS.getCurrentBid(),sS.getCurrentAsk(),sS.getDate(),sS.getRecommendation(),sS.getStockQuantity());
		}
		
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public float getCurrentBid() {
			return currentBid;
		}
		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}
		public float getCurrentAsk() {
			return currentAsk;
		}
		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getRecommendation() {
			return recommendation;
		}
		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}
		public int getStockQuantity() {
			return stockQuantity;
		}
		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}
}
