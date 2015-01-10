package mta.arnit.stock.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mta.arnit.stock.exception.BalanceException;
import mta.arnit.stock.exception.InvalidQuantityException;
import mta.arnit.stock.exception.PortfolioFullException;
import mta.arnit.stock.exception.StockAlreadyExistsException;
import mta.arnit.stock.exception.StockNotExistException;
import mta.arnit.stock.model.Portfolio;
import mta.arnit.stock.service.PortfolioService;

/**
 * to show the user the result on the screen
 * @author Arnit
 * @since 2014
 * 01/12/14
 */
public class PortfolioServlet extends HttpServlet  {	

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio;
		try {
			portfolio = portfolioService.getPortfolio();
			resp.getWriter().println(portfolio.getHtmlString());
		} catch (StockAlreadyExistsException e) {
			resp.getWriter().println(e.getMessage());
		} catch (InvalidQuantityException e) {
			resp.getWriter().println(e.getMessage());
		} catch (PortfolioFullException e) {
			resp.getWriter().println(e.getMessage());
		} catch (BalanceException e) {
			resp.getWriter().println(e.getMessage());
		} catch (StockNotExistException e) {
			resp.getWriter().println(e.getMessage());
		}
		
		resp.setContentType("text/html");
	}	
}

