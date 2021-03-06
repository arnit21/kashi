package mta.arnit.stock.servlet;

import mta.arnit.stock.dto.PortfolioEditDto;
import mta.arnit.stock.exception.BalanceException;
import mta.arnit.stock.exception.InvalidQuantityException;
import mta.arnit.stock.exception.PortfolioFullException;
import mta.arnit.stock.exception.StockAlreadyExistsException;
import mta.arnit.stock.exception.StockNotExistException;
import mta.arnit.stock.exception.SymbolNotFoundInNasdaq;
import mta.arnit.stock.model.Portfolio;
import mta.arnit.stock.model.StockStatus;
import mta.arnit.stock.service.PortfolioService;
import mta.arnit.stock.service.PortfolioService.OPERATION;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PortfolioEditServlet extends AbstractAlgoServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		Portfolio portfolio = portfolioService.getPortfolio();
		StockStatus[] stockStatusArray = portfolio.getStocks();
		List<StockStatus> stockStatusList = new ArrayList<>();
		for (StockStatus ss : stockStatusArray) {
			if(ss != null)
				stockStatusList.add(ss);
		}

		PortfolioEditDto ret = new PortfolioEditDto();
		ret.setTitle(portfolio.getTitle());
		ret.setBalance(portfolio.getBalance());
		ret.setStockStatusList(stockStatusList);

		resp.getWriter().print(withNullObjects().toJson(ret));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		try {
			String title = req.getParameter("title");
			if(title != null && !title.isEmpty()) {
				portfolioService.setTitle(title);
			}

			String balance = req.getParameter("balance");
			if(balance != null && !balance.isEmpty()) {
				portfolioService.setBalance(Float.valueOf(balance));
			}

			String symbol = req.getParameter("symbol");

			String qStr = req.getParameter("quantityforaction");
			int quantity;
			if(qStr == null || qStr.isEmpty()) {
				quantity = -1;
			}else {
				quantity = Integer.valueOf(qStr);					
			}

			String operationStr = req.getParameter("operation");
			if (operationStr != null) {
				PortfolioService.OPERATION operation = OPERATION.valueOf(operationStr.toUpperCase());

				switch (operation) {
				case ADD:
					portfolioService.addStock(symbol);
					break;

				case BUY:
					portfolioService.buyStock(symbol, quantity);
					break;

				case SELL:
					portfolioService.sellStock(symbol, quantity);
					break;

				case REMOVE:
					portfolioService.removeStock(symbol);
					break;

				default:
					break;
				}
			}
			resp.sendRedirect("/portfolioedit.jsp");
		}catch(StockNotExistException e) {
			resp.sendRedirect("/portfolioedit.jsp?error=Stock not exist in portfolio");
		}catch(StockAlreadyExistsException e) {
			resp.sendRedirect("/portfolioedit.jsp?error=Stock already exists in portfolio");
		}catch(PortfolioFullException | SymbolNotFoundInNasdaq | InvalidQuantityException | BalanceException e) {
			resp.sendRedirect("/portfolioedit.jsp?error=" + e.getMessage());
		}catch(Exception e) {
			log.severe("Error: " + e.getMessage());
			resp.sendRedirect("/portfolioedit.jsp?error=General error occured.");
		}
	}
}
