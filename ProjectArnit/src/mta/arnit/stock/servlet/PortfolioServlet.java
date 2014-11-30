package mta.arnit.stock.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mta.arnit.stock.Stock;
import mta.arnit.stock.model.Portfolio;
import mta.arnit.stock.service.PortfolioService;

public class PortfolioServlet extends HttpServlet  {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();	
	
		String to_return = "";
		for (int i =0; i < portfolio.getProtofoloSize(); i++)
		{
			to_return += stocks[i].getHtmlDescription() + "<br>";
		}
		resp.getWriter().println(to_return);
		resp.setContentType("text/html");
	}	
}

