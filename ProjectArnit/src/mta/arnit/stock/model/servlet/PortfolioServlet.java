package mta.arnit.stock.model.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mta.arnit.stock.Stock;
import mta.arnit.stock.model.Portfolio;
import mta.arnit.stock.model.service.PortfolioService;

public class PortfolioServlet extends HttpServlet  {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();	
		
		resp.setContentType("text/html");
	}
}