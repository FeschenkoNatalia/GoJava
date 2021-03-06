package com.kickstarter.dao.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.kickstarter.model.Quote;

public class DbQuoteImpl extends DBCon implements QuoteDaoInterface {

	public Quote get() {
		ResultSet rs = null;
		PreparedStatement pStatement = null;
		List<Quote> list = new ArrayList<>();

		try (Connection conection = getConnection()) {
			pStatement = conection.prepareStatement("select * from quotes");
			rs = pStatement.executeQuery();

			while (rs.next()) {
				Quote quote = new Quote();
				quote.setAuthor(rs.getString("author"));
				quote.setQuoteText(rs.getString("quote"));
				list.add(quote);
			}
		} catch (SQLException e) {
			System.out.println("Quote MySql connection problem");
		}
		return list.get((int) (Math.random() * list.size()));
	}
}
