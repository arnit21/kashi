package mta.com.mycompany.stock.model;

import java.util.Date;
import java.util.Stack;

import mta.com.arnit.Stock;


public class Portfolio {
	public final int STOCK_MAX = 3;
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int current_number_of_stocks;
	
	
	public Portfolio() 
	{
		stocks = new Stock[STOCK_MAX];
		stocksStatus = new StockStatus[STOCK_MAX];	
		current_number_of_stocks = 0;
	}

	public void addStock(Stock stock)
	{
		stocks[current_number_of_stocks] = stock;
		current_number_of_stocks++;
	}
	
	public Stock[] getStocks()
	{
		return stocks;
	}
	public class StockStatus 
	{
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
		
		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
	}
}
	