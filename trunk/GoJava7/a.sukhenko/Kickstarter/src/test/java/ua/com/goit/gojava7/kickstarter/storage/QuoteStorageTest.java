package ua.com.goit.gojava7.kickstarter.storage;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import ua.com.goit.gojava7.kickstarter.domain.Quote;

import static org.hamcrest.CoreMatchers.is;

public class QuoteStorageTest {
	private QuoteStorage quoteStorage = new QuoteStorage();

	@Before
	public void addQuote() {
		quoteStorage.addQuote(new Quote("Quote", "Author"));
	}

	@Test
	public void testQuoteStorageAddQuote() {
		assertThat(quoteStorage.getQuotes().get(0).getQuoteName(), is("Quote"));
		assertThat(quoteStorage.getQuotes().get(0).getAuthor(), is("Author"));
	}

	@Test
	// Testing our getRandomQuote to be sure that every quote appears at least 1
	// time after calling getRandomQuote() 100 times.
	public void testRandomQuote() {
		HashSet<Quote> quotesRandomHolder = new HashSet<>();
		quoteStorage.addQuote(new Quote("Q1", "A1"));
		quoteStorage.addQuote(new Quote("Q2", "A2"));
		quoteStorage.addQuote(new Quote("Q3", "A3"));
		quoteStorage.addQuote(new Quote("Q4", "A4"));
		for (int i = 0; i < 100; i++) {
			quotesRandomHolder.add(quoteStorage.getRandomQuote());
		}
		assertTrue(quotesRandomHolder.size() == 5);

	}

}
