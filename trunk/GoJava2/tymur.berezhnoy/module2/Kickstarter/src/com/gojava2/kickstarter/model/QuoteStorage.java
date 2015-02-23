package com.gojava2.kickstarter.model;
import java.util.ArrayList;
import java.util.List;

public class QuoteStorage {
	
	private List<Quote> listOfQuotes;

	public QuoteStorage() {
		listOfQuotes = new ArrayList<Quote>();
		listOfQuotes.add(new Quote("Sometimes when you innovate, you make mistakes."
				+ "\n It is best to admit them quickly, and get on with\n improving your other innovations.", "Steve Jobs"));
		listOfQuotes.add(new Quote("The common question that gets asked in business is, 'why?'."
				+ "\n That's a good question, but an equally valid question is, 'why not?'", "Jeff Bezos"));
		listOfQuotes.add(new Quote("If there is anything that a man can do well,\n I say let him do it. Give him a chance.", "Abraham Lincoln"));
		listOfQuotes.add(new Quote("Great leaders, like Steve Jobs or Jeff Bezos, also focused on the long term.", "Reed Hastings"));
		listOfQuotes.add(new Quote("When you're curious, you find lots of interesting things to do.", "Walt Disney"));
	}
	
	public List<Quote> getContent() {
		return listOfQuotes;
	}
	
	public Quote getRandomQuote() {
		int randomInex = (int)(Math.random() * listOfQuotes.size());
        return listOfQuotes.get((randomInex));
	}
	
	public static void main(String[] args) {
		QuoteStorage s = new QuoteStorage();
		System.out.println(s.getRandomQuote().getContent());
	}
}