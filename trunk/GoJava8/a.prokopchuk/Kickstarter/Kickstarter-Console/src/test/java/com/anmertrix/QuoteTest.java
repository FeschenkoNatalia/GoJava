package com.anmertrix;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QuoteTest {

	@Test
	public void getQuoteTest() {
		Quote quote = new Quote("author", "test");
		assertTrue("test" == quote.getQuoteText());
	}
}