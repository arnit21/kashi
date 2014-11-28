package mta.com.mycompany.stock.model.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mta.com.arnit.Stock;
import mta.com.mycompany.stock.model.Portfolio;
import mta.com.mycompany.stock.model.service.PortfolioService;

public class PortfolioServlet extends HttpServlet  {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();	
		

		resp.setContentType("text/html");
		resp.getWriter().println(getHTMLcode(stocks));
		
	}
	public String getHTMLcode(Stock[] stocks)
	{
		String to_return = "";
		for (int i =0; i< 3; i++)
		{
			to_return += stocks[i].getHtmlDescription();
		}
		return to_return;
	}

}
