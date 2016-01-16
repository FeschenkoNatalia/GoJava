package ua.com.goit.gojava7.kickstarter.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import ua.com.goit.gojava7.kickstarter.beans.Category;
import ua.com.goit.gojava7.kickstarter.beans.Quote;
import ua.com.goit.gojava7.kickstarter.dao.CategoryDao;
import ua.com.goit.gojava7.kickstarter.dao.QuoteDao;

//@WebServlet("/")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MainServlet.class);

	@Autowired
	private QuoteDao quoteDao;

	@Autowired
	private CategoryDao categoryDao;

	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doGet");

		Quote quote = quoteDao.getRandomQuote();
		log.info("Random quote : " + quote);

		List<Category> categories = categoryDao.getAll();
		log.info("All categories : " + categories);

		List<Object[]> top10Categories = categoryDao.getTop10Categories();
		log.info("Top 10 categories : " + top10Categories);

		request.setAttribute("categories", categories);
		request.setAttribute("top10Categories", top10Categories);
		request.setAttribute("quote", quote);
		request.getRequestDispatcher("WEB-INF/pages/index.jsp").forward(request, response);
	}
}