package ua.com.goit.gojava7.kickstarter.config;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

import ua.com.goit.gojava7.kickstarter.dao.QuoteDao;
import ua.com.goit.gojava7.kickstarter.dao.QuoteReader;
import ua.com.goit.gojava7.kickstarter.dao.file.FileQuoteReader;
import ua.com.goit.gojava7.kickstarter.dao.memory.MemoryQuoteReader;
import ua.com.goit.gojava7.kickstarter.dao.memory.QuoteDaoMemoryImpl;
import ua.com.goit.gojava7.kickstarter.dao.mysql.QuoteDaoMySqlImpl;

public class DaoProvider {

	private static final File QUOTES_FILE = new File("./quotes.txt");

	private DataSource dataSource;

	private Connection connection = null;

	public DaoProvider(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void open() {
		if (dataSource == DataSource.MYSQL) {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/gojava4omarchuk?user=gojava4omarchuk&password=somepassword");
			} catch (SQLException e) {
				throw new IllegalStateException("Cannot open connection. " + e.getMessage(), e);
			}
		}
	}

	public void close() {
		if (dataSource == DataSource.MYSQL) {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new IllegalStateException("Cannot close connection " + connection + ". " + e.getMessage(), e);
			}
		}
	}

	public QuoteDao getQuoteDao() {
		QuoteDao quoteDao;

		if (dataSource == DataSource.MEMORY) {
			QuoteDaoMemoryImpl quoteDaoMemoryImpl = new QuoteDaoMemoryImpl(new Random());
			QuoteReader quoteReader = new MemoryQuoteReader();
			quoteDaoMemoryImpl.setQuotes(quoteReader.readQuotes());
			quoteDao = quoteDaoMemoryImpl;
		} else if (dataSource == DataSource.FILE) {
			QuoteDaoMemoryImpl quoteDaoMemoryImpl = new QuoteDaoMemoryImpl(new Random());
			QuoteReader quoteReader = new FileQuoteReader(QUOTES_FILE);
			quoteDaoMemoryImpl.setQuotes(quoteReader.readQuotes());
			quoteDao = quoteDaoMemoryImpl;
		} else if (dataSource == DataSource.MYSQL) {
			QuoteDaoMySqlImpl quoteDaoMySqlImpl = new QuoteDaoMySqlImpl(connection);
			quoteDao = quoteDaoMySqlImpl;
		} else {
			throw new IllegalArgumentException("Unknown data source " + dataSource);
		}

		return quoteDao;
	}

}