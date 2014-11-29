package mta.arnit.stock.model;

import java.util.Date;
import java.util.Stack;

import mta.arnit.stock.Stock;


public class Portfolio {
	private final int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	
	
	public Portfolio() 
	{
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];	
		portfolioSize = 0;
	}

	public void addStock(Stock stock)
	{
		stocks[portfolioSize] = stock;
		portfolioSize++;
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
	