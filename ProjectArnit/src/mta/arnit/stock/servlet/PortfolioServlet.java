package mta.arnit.stock.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mta.arnit.stock.model.Portfolio;
import mta.arnit.stock.model.Stock;
import mta.arnit.stock.service.PortfolioService;

/**
 * to show the user the result on the screen
 * @author Arnit
 * @since 2014
 * 01/12/14
 */
public class PortfolioServlet extends HttpServlet  {	
	/**
	 * Request and Response from the user in HTML
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();	
		
		Portfolio portfolio2 = new Portfolio(portfolio);
		portfolio2.setTitle("Portfolio #2");

		resp.getWriter().println(portfolio.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		
		portfolio.removeFirstStock(portfolio);
		
		resp.getWriter().println(portfolio.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio2.getStocks()[2].setBid(55.55f);

		resp.getWriter().println(portfolio.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		resp.setContentType("text/html");
	}	
}

