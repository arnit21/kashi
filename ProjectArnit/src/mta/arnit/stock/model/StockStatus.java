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
	 * StockStatus constractor that use his parent constractor and add recommendation + stockQuantity
	 * @param s
	 * @param a
	 * @param b
	 * @param d
	 * @param r
	 * @param SQ
	 */
	public StockStatus(String s, float a, float b, Date d, ALGO_RECOMMENDATION r ,int SQ) {
		super(s, a, b, d);	
		this.recommendation = r;
		this.stockQuantity = SQ;
	}
	
	/**
	 * StockStatus copy constractor
	 * @param stockStatus
	 */
	public StockStatus(StockStatus sS)
	{	
		super(sS);
		this.recommendation = sS.getRecommendation();
		this.stockQuantity = sS.getStockQuantity();
	}
	
	/**
	 * StockStatus constractor that use his parent constractor  
	*/
	public StockStatus(Stock stock) {
		super(stock);	
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
