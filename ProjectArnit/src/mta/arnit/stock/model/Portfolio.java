package mta.arnit.stock.model;

import java.util.Date;
import java.util.Stack;

import mta.arnit.stock.Stock;


public class Portfolio {
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	private StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	private int portfolioSize = 0;
	
	public void addStock(Stock stock)
	{
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}
	
	public int getProtofoloSize()
	{
		return portfolioSize;
	}
	
	public Stock[] getStocks()
	{
		return stocks;
	}
	
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
	