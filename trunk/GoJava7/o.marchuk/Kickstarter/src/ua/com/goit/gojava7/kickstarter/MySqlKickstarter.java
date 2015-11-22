package ua.com.goit.gojava7.kickstarter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.com.goit.gojava7.kickstarter.dao.QuoteDao;
import ua.com.goit.gojava7.kickstarter.dao.mysql.QuoteDaoMySqlImpl;
import ua.com.goit.gojava7.kickstarter.domain.Quote;

public class MySqlKickstarter {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/gojava4omarchuk?user=gojava4omarchuk&password=somepassword");

			QuoteDao quoteDao = new QuoteDaoMySqlImpl(connection);
			Quote quote = quoteDao.getRandomQuote();
			System.out.println(quote);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
