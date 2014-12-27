package mta.arnit.stock.model;

/**
 * description of portfolio
 * socks-  stockstatus- array of status for each stock,
 * portfolioSize is the size of stocks,title is the name of the stock portfolio.
 * @author Arnit
 * @since 2014
 * 01/12/14
 */
public class Portfolio
{
	private final static int MAX_PORTFOLIO_SIZE = 5; 
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	private String title;
	public enum ALGO_RECOMMENDATION{DO_NOTHING, BUY, SELL}
	private float balance;

	/**
	 *  Portfolio constractor
	 */
	public Portfolio() {
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

		for(int i = 0; i < portfolioSize; i++){
			this.stocksStatus[i] = new StockStatus(p.stocksStatus[i]); 
		}
		setPortfolioSize(p.portfolioSize);
		setTitle(p.title);
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
	 * @param stockStatus
	 */
	public boolean addStock(StockStatus stcokStatus) {
		if(portfolioSize==MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Can�t add new stock, portfolio can have only"+ MAX_PORTFOLIO_SIZE +"stocks");
			return false;
		}
		
		for (int i = 0; i < portfolioSize; i++) {
			if (stocksStatus[i].getSymbol().equals(stcokStatus.getSymbol())) {
				System.out.println(stocksStatus[i].getSymbol() + " Already exists in the portfolio");
				
				return false;
			}
		}
		
		stocksStatus[portfolioSize] = new StockStatus(stcokStatus);
		portfolioSize++;
		
		return true;
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
			stocksStatus[index] = stocksStatus[index + 1];
			index ++;
		}

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

		updateBalance(quantity * stocksStatus[index].getBid());
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
			quantity = (int) (balance / stocksStatus[index].getAsk());
		}
					
		float money_needed = quantity * (stocksStatus[index].getAsk()); 					
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
			portfolioValue += this.stocksStatus[i].getStockQuantity() * this.stocksStatus[i].getBid();		
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
			to_return += stocksStatus[i].getHtmlDescription() + "<br>" ;
		}
		to_return += "<br>" + "Total Portfolio Value: "+ getTotalValue()+"$"+", Total Stocks value: "+ getStocksValue()+"$, Balance: "+ getBalance()+"$";
		return to_return;
	} 
}
