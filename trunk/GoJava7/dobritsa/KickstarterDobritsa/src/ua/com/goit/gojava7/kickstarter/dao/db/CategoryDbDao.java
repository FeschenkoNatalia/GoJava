package ua.com.goit.gojava7.kickstarter.dao.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.com.goit.gojava7.kickstarter.dao.DbDao;
import ua.com.goit.gojava7.kickstarter.dao.storage.CategoryStorage;
import ua.com.goit.gojava7.kickstarter.domain.Category;

public class CategoryDbDao extends DbDao<Category> implements CategoryStorage {

	private static final String TABLE = "category";
	private static final String FIELDS = "id, name";

	public CategoryDbDao(Connection connection) {
		super(connection, FIELDS, TABLE);
	}

	@Override
	protected Category readElement(ResultSet resultSet) throws SQLException {
		Category category = new Category();
		category.setId(resultSet.getInt("id"));
		category.setName(resultSet.getString("name"));
		return category;
	}
}
