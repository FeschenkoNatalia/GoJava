package main.ua.com.goit.gojava7.kickstarter.view;

import java.util.List;

import main.ua.com.goit.gojava7.kickstarter.beans.Category;
import main.ua.com.goit.gojava7.kickstarter.beans.Project;
import main.ua.com.goit.gojava7.kickstarter.beans.Quote;
import main.ua.com.goit.gojava7.kickstarter.dao.CategoryDao;
import main.ua.com.goit.gojava7.kickstarter.dao.FaqDao;
import main.ua.com.goit.gojava7.kickstarter.dao.PaymentDao;
import main.ua.com.goit.gojava7.kickstarter.handlers.TextModifer;

public class ConsolePrinter {
	private static final TextModifer TEXT_MODIFER = new TextModifer();

	public void print(Quote quote) {
		String result = TEXT_MODIFER.getModifiedQuote(quote.getQuoteText(), quote.getAuthor());
		System.out.println(result);
	}

	public void print(Category category) {
		System.out.println("Category : " + category.getName());
	}

	public void print(String string) {
		System.out.println(string);
	}

	public void printShortProjectInfo(Project project, FaqDao faqs, PaymentDao payments) {
		System.out.println("Title : " + project.getTitle());
		System.out.println("Short description : " + project.getBriefDescription());
		System.out.println("Required amount : " + project.getRequiredSum());
		System.out.println("Gathered amount : " + payments.getSumProjectPayments(project.getUniqueID()));
		System.out.println("Days left : " + project.getDaysLeft());
		System.out.println("FAQ : " + faqs.getProjectFaqs(project.getUniqueID()));
	}

	public void printFullProjectInfo(Project project, FaqDao faqStorage, PaymentDao paymentStorage) {
		printShortProjectInfo(project, faqStorage, paymentStorage);
		System.out.println("Full description : " + project.getFullDescription());
		System.out.println("Video : " + project.getVideoLink());
	}

	public void printCategories(CategoryDao allCategories) {
		List<Category> categories = allCategories.getAll();
		
		System.out.println("All categories : ");
		for (int index = 0; index < categories.size(); index++) {
			Category category = categories.get(index);
			System.out.println((index + 1) + " : " + category.getName());
		}
	}

	public void printProjects(List<Project> projects, FaqDao faqStorage, PaymentDao paymentStorage) {
		System.out.println("All projects from selected category : ");
		for (int index = 0; index < projects.size(); index++) {
			Project project = projects.get(index);				
			System.out.println("Project � " + (index + 1));
			printShortProjectInfo(project, faqStorage, paymentStorage);
			System.out.println();		
		}	
	}
}
