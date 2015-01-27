package mta.arnit.stock.model;

import java.util.Date;

import mta.arnit.stock.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * class StockStatus is type of Stock that extend from Stock. 
 * @author Arnit
 * @since 2014
 * 26/12/2014
 */
public class StockStatus extends Stock {

	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	/**
	 * StockStatus copy constractor
	 * @param stockStatus
	 */
	public StockStatus(StockStatus sS)
	{	
		this.symbol = sS.symbol;
		this.bid = sS.bid;
		this.ask = sS.ask;
		this.date = new Date(sS.date.getTime());
		this.recommendation = sS.recommendation;
		this.stockQuantity = sS.stockQuantity;
	}
	
	/**
	 * StockStatus constractor that use his parent constractor  
	*/
	public StockStatus(Stock stock) {
		super(stock);
		recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}

	public StockStatus() {
		super();
		this.recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}
	
}
