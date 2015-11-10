package ua.com.goit.gojava7.kickstarter.view;

import java.util.Iterator;
import java.util.Set;


import ua.com.goit.gojava7.kickstarter.model.Category;
import ua.com.goit.gojava7.kickstarter.model.Project;
import ua.com.goit.gojava7.kickstarter.model.Quote;

public class ConsolePrinter {
	private static final String MOVE_TO_THE_NEXT_LINE = "\n";
	private static final String DOUBLE_MOVE_TO_THE_NEXT_LINE = "\n\n";
	private static final String USD = " USD";
	private static final char SPACE = ' ';
	private static final TextModifer TEXT_MODIFER = new TextModifer();

	public void print(Quote quote) {
		String result = TEXT_MODIFER.modifyQuoteTextBeforePrint(quote.getQuoteText(), quote.getAuthor());
		System.out.println(result);
	}

	public void printCategories(Set<Category> categories) {
		Iterator<Category> categoryIterator = categories.iterator();
		StringBuilder result = new StringBuilder();
		
		result.
			append("All categories:").
			append(MOVE_TO_THE_NEXT_LINE);
			
		int numberOfProject = 0;
		while (categoryIterator.hasNext()) {
			numberOfProject ++;
			Category category = categoryIterator.next();
			result.
				append(numberOfProject).
				append(" : ").
				append(category.getName()).
				append(MOVE_TO_THE_NEXT_LINE);
		}
		System.out.println(result.toString());
	}
		
	public void printProjects(Set<Project> projects) {	
		StringBuilder result = new StringBuilder();
		
		if (projects.isEmpty()) {
			result.
				append("There is no any project in selected category.");
			System.out.println(result.toString());
		} else {
			result.
				append("All projects from selected category : ").
				append(DOUBLE_MOVE_TO_THE_NEXT_LINE);
			
			int stepCounter = 0;
			Iterator<Project> projectIterator = projects.iterator();
			while (projectIterator.hasNext()) {				
				result.
					append("Project � : ").
					append(++ stepCounter).
					append(MOVE_TO_THE_NEXT_LINE).
					append(getBriefInfoProject(projectIterator.next()));
			}
			System.out.print(result.toString());
		}
	}	
		
	public String getBriefInfoProject(Project project) {
		StringBuilder result = new StringBuilder();
		result.
			append("Project title : ").
			append(TEXT_MODIFER.getModifiedString(project.getTitle())).
			append(MOVE_TO_THE_NEXT_LINE).
			append("About : ").
			append(TEXT_MODIFER.getModifiedString(project.getBriefDescription())).
			append(MOVE_TO_THE_NEXT_LINE).
			append("Required amount of $ : ").
			append(project.getRequiredAmountOfMoney()).
			append(USD).
			append(MOVE_TO_THE_NEXT_LINE).
			append("Current collected amount of $ : ").
			append(project.getCurrentAmoutOfMoney()).
			append(USD).
			append(MOVE_TO_THE_NEXT_LINE).
			append("Days to go : ").
			append(project.getExpiryDays()).
			append(DOUBLE_MOVE_TO_THE_NEXT_LINE);
		return result.toString();
	}
	
		public void printFullInfoProject(Project project) {
			StringBuilder result = new StringBuilder();
			result.
				append("Project title : ").
				append(TEXT_MODIFER.getModifiedString(project.getTitle())).
				append(DOUBLE_MOVE_TO_THE_NEXT_LINE).
				append("About : ").
				append(TEXT_MODIFER.getModifiedString(project.getBriefDescription())).
				append(DOUBLE_MOVE_TO_THE_NEXT_LINE).
				append("Required amount of $ : ").
				append(project.getRequiredAmountOfMoney()).
				append(USD).
				append(DOUBLE_MOVE_TO_THE_NEXT_LINE).
				append("Current collected amount of $ : ").
				append(project.getCurrentAmoutOfMoney()).
				append(USD).
				append(DOUBLE_MOVE_TO_THE_NEXT_LINE).
				append("Days to go : ").
				append(project.getExpiryDays()).
				append(DOUBLE_MOVE_TO_THE_NEXT_LINE).
				append("About this project : ").
				append(TEXT_MODIFER.getModifiedString(project.getFullDescription())).
				append(DOUBLE_MOVE_TO_THE_NEXT_LINE).
				append("Link on viedo with demo : ").
				append(project.getLinkOnVideo()).
				append(DOUBLE_MOVE_TO_THE_NEXT_LINE).
				append("Question : ").
				append(TEXT_MODIFER.getModifiedString(project.getQuestion())).
				append(MOVE_TO_THE_NEXT_LINE).
				append("Answer : ").
				append(TEXT_MODIFER.getModifiedString(project.getAnswer()));
			System.out.println(result.toString());
		}
	
	public void print(Category category) {
		System.out.println("Category : " + category.getName());
	}

	public void print(String string) {
		System.out.println(string);
	}		
}
