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
public class Portfolio
{
	private final static int MAX_PORTFOLIO_SIZE = 5; 
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	private String title;
	public enum ALGO_RECOMMENDATION{DO_NOTHING, BUY, SELL}
	private float balance;

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
	public Portfolio(Portfolio p)
	{
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
	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * to add stock to array stock if have a place in the array if not print to console Error.
	 * @param stock
	 */
	public void addStock(Stock stock)
	{
		if(portfolioSize==MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Can’t add new stock, portfolio can have only"+ MAX_PORTFOLIO_SIZE +"stocks");
		}
		stocks[portfolioSize] = stock;
		stocksStatus[portfolioSize]= new StockStatus(stock);
		portfolioSize++;
	}

	/**
	 * remove a specific stock from portfolio according to his symbol first sell the stock and then remove.
	 * @param symbol
	 */
	public boolean removeStock(String symbol) 
	{	
		int index = get_index_of_symbol(symbol);
		
		if(index ==-1){
			return false;
		}
		
		sellStock(symbol, -1);
		
		while (index < portfolioSize -1 )
		{
			stocks[index] = stocks[index +1];
			stocksStatus[index] = stocksStatus[index + 1];
			index ++;
		}

		stocks[index] = null;
		stocksStatus[index] = null;
		portfolioSize--;

		return true;
	}

	/**
	 * get symbol and find the place in the array - the index of symbol 
	 * @param symbol
	 * @return
	 */
	private int get_index_of_symbol(String symbol)
	{
		for(int i = 0; i < portfolioSize; i++)
		{
			assert(stocks[i].getSymbol() == stocksStatus[i].getSymbol());
			if(stocksStatus[i].getSymbol().equals(symbol))
			{
				return i;
			}
		}
		return -1; // index not found
	}
	
	/**
	 * sell a stock (but not to remove it from portfolio)
	 * -1 to sell whole quantity of this stock 
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	public boolean sellStock(String symbol,int quantity)
	{
		int index = get_index_of_symbol(symbol);
		
		if (index == -1 || quantity < -1)
		{
			return false;
		}
		
		if(quantity > stocksStatus[index].getStockQuantity())
		{
			System.out.println("Not enough stocks to sell");
			return false;
		}
		
		if(quantity == -1)
		{	
			quantity = stocksStatus[index].getStockQuantity();
		}

		updateBalance(quantity * stocksStatus[index].getCurrentBid());
		stocksStatus[index].setStockQuantity(stocksStatus[index].getStockQuantity() - quantity);


		return true;
	}

	/**
	 * To buy stock and update the balance
	 * -1 buy stocks (with the symbol you ask for) with all the money in balance.
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	public boolean buyStock(String symbol,int quantity) 
	{
		int index = get_index_of_symbol(symbol);
		if (index == -1 || quantity < -1 ||   getBalance() < 0)
		{
			return false;
		}
		
		if(quantity == -1){
			quantity = (int) (balance / stocksStatus[index].getCurrentAsk());
		}
					
		float money_needed = quantity * (stocksStatus[index].getCurrentAsk()); 					
		if (money_needed > balance)
		{
			System.out.println("Not enough balance to complete purchase");
			return false;
		}
					
		updateBalance(-money_needed);
		stocksStatus[index].setStockQuantity(stocksStatus[index].getStockQuantity() + quantity);

		return true;
	}


	/**
	 * To update the balance to know the current value in balance.
	 * @param amount
	 */
	public void updateBalance(float amount)
	{
		balance += amount;
	}

	/**
	 * returns total value of all stocks
	 * @return portfolioValue
	 */
	public float getStocksValue()
	{
		float portfolioValue = 0;		
		for(int i=0 ; i < portfolioSize; i++){
			portfolioValue += this.stocksStatus[i].getStockQuantity() * this.stocksStatus[i].getCurrentBid();		
		}
		return portfolioValue;
	}

	/**
	 * return the money available in portfolio for investments (was not yet invested in stocks).
	 * @return balance
	 */
	public float getBalance()
	{
		return this.balance;
	}

	/**
	 * Gets the total value of all stocks held in the portfolio.
	 * @return
	 */
	public float getTotalValue()
	{
		return this.getStocksValue() + this.getBalance();
	}

	/**
	 * return title and html description of the stock in array stocks.
	 * @return 
	 */
	public String getHtmlString()
	{
		String to_return = new String();
		to_return += "<h1>" + getTitle() + "</h1>";
		for(int i=0; i < getPortfolioSize() ; i++)
		{
			to_return += stocks[i].getHtmlDescription() + "<br>" ;
		}
		to_return += "<br>" + "Total Portfolio Value: "+ getTotalValue()+"$"+", Total Stocks value: "+ getStocksValue()+"$, Balance: "+ getBalance()+"$";
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
		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		ALGO_RECOMMENDATION recommendation;
		private int stockQuantity;

		/**
		 * StockStatus empty constractor
		 */
		public StockStatus(String s,float cB,float cA,Date d,ALGO_RECOMMENDATION r, int sQ)
		{
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
			this(sS.getSymbol(),sS.getCurrentBid(),sS.getCurrentAsk(),new Date (sS.getDate().getTime()),sS.getRecommendation(),sS.getStockQuantity());
		}

		/**
		 * Empty constractor StockStatus
		 */
		public StockStatus()
		{
			symbol = "";
			currentBid = 0;
			currentAsk = 0;
			date = new Date();
			recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
			stockQuantity = 0;
		}

		/**
		 * constractor get stock and update StockStatus details.
		 * @param stock
		 */
		public StockStatus(Stock stock)
		{
			this.symbol = stock.getSymbol();
			this.currentBid = stock.getBid();
			this.currentAsk = stock.getAsk();
			this.date = new Date (stock.getDate().getTime());
			this.recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
			this.stockQuantity = 0;
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
		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}
		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
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
