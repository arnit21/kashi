package mta.arnit.stock.model;

import java.util.ArrayList;
import java.util.List;

import mta.arnit.stock.exception.BalanceException;
import mta.arnit.stock.exception.InvalidQuantityException;
import mta.arnit.stock.exception.PortfolioFullException;
import mta.arnit.stock.exception.StockAlreadyExistsException;
import mta.arnit.stock.exception.StockNotExistException;

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
	public final static int MAX_PORTFOLIO_SIZE = 5;
	private List<StockStatus> stocksStatus;
	private String title;
	public enum ALGO_RECOMMENDATION{DO_NOTHING, BUY, SELL}
	private float balance;

	
	/**
	 *  Portfolio constractor
	 */
	public Portfolio() {
		stocksStatus = new ArrayList<StockStatus>(MAX_PORTFOLIO_SIZE);
	}

	/**
	 * Portfolio copy constractor 
	 * @param p
	 */
	public Portfolio(Portfolio p)
	{
		this();

		setTitle(p.title);
		setBalance(p.balance);
		stocksStatus.addAll(p.stocksStatus);
	}
	
	public Portfolio(List<StockStatus> sS)
	{
		this();
		
		for(int i=0; i<sS.size(); i++)
		{
			stocksStatus.add(sS.get(i));
		}
	}
	
	public StockStatus[] getStocks() {
		StockStatus[] ret = new StockStatus[stocksStatus.size()];
		ret =  stocksStatus.toArray(ret);
		return ret;
	}

	public List<StockStatus> getStocksStatus() {
		return stocksStatus;
	}
	public void setStocksStatus(List<StockStatus> stocksStatus) {
		this.stocksStatus = stocksStatus;
	}
	
	public int getPortfolioSize() {
		return stocksStatus.size();
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
	 * @throws StockAlreadyExistsException 
	 * @throws PortfolioFullException 
	 */
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException {
		
		if(stocksStatus.size()==MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Can�t add new stock, portfolio can have only "+ MAX_PORTFOLIO_SIZE +" stocks");
			throw new PortfolioFullException();
		}
		
		for (int i = 0; i < stocksStatus.size(); i++) {
			
			if (stocksStatus.get(i).getSymbol().equals(stock.getSymbol())) {
				System.out.println(stocksStatus.get(i).getSymbol() + " Already exists in the portfolio");
				
				throw new StockAlreadyExistsException(stock.getSymbol());
			}
		}
		
		stocksStatus.add(new StockStatus(stock));		
	}

	/**
	 * remove a specific stock from portfolio according to his symbol first sell the stock and then remove.
	 * @param symbol
	 * @throws StockNotExistException 
	 * @throws InvalidQuantityException 
	 */

	public void removeStock(String symbol) throws StockNotExistException, InvalidQuantityException 
	{	
		int index = get_index_of_symbol(symbol);
		
		if(index ==-1){
			throw new StockNotExistException(symbol);
		}
		
		sellStock(symbol, -1);
		
		while (index < stocksStatus.size())
		{
			stocksStatus.remove(index);
			index++;
		}
	}
	
	/**
	 * get symbol and find the place in the array - the index of symbol 
	 * @param symbol
	 * @return
	 */
	private int get_index_of_symbol(String symbol)
	{
		for(int i = 0; i < stocksStatus.size(); i++)
		{
			if(stocksStatus.get(i).getSymbol().toLowerCase().equals(symbol.toLowerCase()))
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
	 * @throws StockNotExistException 
	 * @throws InvalidQuantityException 
	 */
	public void sellStock(String symbol,int quantity) throws StockNotExistException, InvalidQuantityException
	{
		int index = get_index_of_symbol(symbol);
		
		if (index == -1)
		{
			throw new StockNotExistException(symbol);
		}
		if(quantity < -1)
		{
			System.out.println("Can't sell nagative stocks");
			throw new InvalidQuantityException("Can't sell nagative stocks");
		}
		
		if(quantity > stocksStatus.get(index).getStockQuantity())
		{
			System.out.println("Not enough stocks to sell");
			throw new InvalidQuantityException("Not enough stocks to sell");
		}
		
		if(quantity == -1)
		{	
			quantity = stocksStatus.get(index).getStockQuantity();
		}

		updateBalance(quantity * stocksStatus.get(index).getBid());
		stocksStatus.get(index).setStockQuantity(stocksStatus.get(index).getStockQuantity() - quantity);
	}
		
	/**
	 * To buy stock and update the balance
	 * -1 buy stocks (with the symbol you ask for) with all the money in balance.
	 * @param symbol
	 * @param quantity
	 * @return
	 * @throws BalanceException 
	 * @throws StockNotExistException 
	 * @throws InvalidQuantityException 
	 */
	public void buyStock(String symbol,int quantity) throws BalanceException, StockNotExistException, InvalidQuantityException 
	{
		int index = get_index_of_symbol(symbol);
		if (index == -1)
		{
			throw new StockNotExistException(symbol);
		}
		if(quantity < -1)
		{
			System.out.println("Can't buy nagative stocks");
			throw new InvalidQuantityException("Can't buy nagative stocks");
		}
		if(getBalance() < 0)
		{
			throw new BalanceException();
		}
		
		if(quantity == -1){
			quantity = (int) (balance / stocksStatus.get(index).getAsk());
		}
					
		float money_needed = quantity * (stocksStatus.get(index).getAsk()); 					
		if (money_needed > balance)
		{
			System.out.println("Not enough balance to complete purchase");
			throw new BalanceException();
		}
					
		updateBalance(-money_needed);
		stocksStatus.get(index).setStockQuantity(stocksStatus.get(index).getStockQuantity() + quantity);

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
		float ret = 0;		
		for (int i=0; i < stocksStatus.size() ; i++){
			ret += stocksStatus.get(i).getStockQuantity() * stocksStatus.get(i).getBid();
		}

		return ret;
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

	 public StockStatus findBySymbol(String symbol) throws StockNotExistException {
		for(int i=0; i<stocksStatus.size(); i++){
			if(stocksStatus.get(i).getSymbol().toLowerCase().equals(symbol.toLowerCase()))
			{
				return stocksStatus.get(i);
			}
		}
		
		throw new StockNotExistException(symbol);
	} 
	 
}
