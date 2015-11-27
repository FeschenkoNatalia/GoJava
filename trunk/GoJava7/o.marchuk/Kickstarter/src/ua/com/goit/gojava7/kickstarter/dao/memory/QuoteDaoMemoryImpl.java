package ua.com.goit.gojava7.kickstarter.dao.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ua.com.goit.gojava7.kickstarter.dao.QuoteDao;
import ua.com.goit.gojava7.kickstarter.domain.Quote;

public class QuoteDaoMemoryImpl implements QuoteDao {

	private Random random;

	private List<Quote> quotes = new ArrayList<>();

	public QuoteDaoMemoryImpl(Random random) {
		this.random = random;
	}

	@Override
	public Quote getRandomQuote() {
		int randomNumber = random.nextInt(quotes.size());
		return quotes.get(randomNumber);
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}
}