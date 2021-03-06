package ua.com.goit.gojava7.kickstarter.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import ua.com.goit.gojava7.kickstarter.DAO.dbStorage.mysql.CategoryDbStorage;
import ua.com.goit.gojava7.kickstarter.DAO.dbStorage.mysql.PaymentDbStorage;
import ua.com.goit.gojava7.kickstarter.DAO.dbStorage.mysql.ProjectDbStorage;
import ua.com.goit.gojava7.kickstarter.model.Project;

@WebServlet("/category")

public class CategoryPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	ProjectDbStorage projectStorage;
	@Autowired
	PaymentDbStorage paymentStorage;
	@Autowired
	CategoryDbStorage categoryStorage;
       
    @Override
    public void init() throws ServletException {
//    	WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//    	projectStorage = webApplicationContext.getBean("projectDbStorage", ProjectDbStorage.class);
//    	paymentStorage = webApplicationContext.getBean("paymentDbStorage", PaymentDbStorage.class);
//    	categoryStorage = webApplicationContext.getBean("categoryDbStorage", CategoryDbStorage.class);
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		List<Project> projects = projectStorage.getProjectsFromSelectedCategory(categoryId);
		request.setAttribute("projects", projects);
		request.setAttribute("payments", paymentStorage);
		request.setAttribute("categoryName", categoryStorage.getCategoryById(categoryId).getCategoryName());
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/jsp/category.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
