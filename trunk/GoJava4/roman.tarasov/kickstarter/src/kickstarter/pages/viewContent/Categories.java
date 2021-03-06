package kickstarter.pages.viewContent;

import java.sql.SQLException;
import java.util.List;

import kickstarter.dao.defaultServices.ServiceException;
import kickstarter.entity.Category;
import kickstarter.entity.Quote;
import kickstarter.mvc.viewState.ViewValues;

public class Categories extends PageView {

	@Override
	public String getHeader() throws ServiceException {
		StringBuilder header = new StringBuilder();

		header.append("\n-----Quote------");
		header.append("\n");
		try {
			Quote quote = idao.getQuoteService().getRandomQuote();
			header.append(quote.getQuote());
		} catch (SQLException | NullPointerException e) {
			throw new ServiceException("");
		}
		header.append("\n----------------");
		header.append("\n=========================");
		header.append("\n|     Categories        |");
		header.append("\n=========================");
		header.append("\n");
		header.append(getListAllCategories());
		header.append("\n------------------------");
		header.append("\nSelect category by ID:<ID>");
		header.append("\nOptions:  <d> - DAO menu;  \n          <u> - User account menu \n          <e> - The End");
		return header.toString();
	}

	private String getListAllCategories() throws ServiceException {
		StringBuilder result = new StringBuilder();
		List<Category> listAllCategories=null;
		try {
			listAllCategories = idao.getCategoryService().getAll();
		} catch (SQLException e) {
			throw new ServiceException("");
		}
		int length = listAllCategories.size();
		strValues = new String[length];
		intValues = new int[length];
		for (int index = 0; index < length; index++) {
			Category currentCategory = listAllCategories.get(index);
			result.append("ID:<");
			result.append(currentCategory.getID());
			result.append("> name:<");
			result.append(currentCategory.getName());
			result.append(">\n");

			strValues[index] = Integer.toString(currentCategory.getID());
			intValues[index] = currentCategory.getID();
		}
		ViewValues ViewValues = iview.getViewValues();
		ViewValues.setIntCategories(intValues);
		ViewValues.setStrCategories(strValues);
		return result.toString();
	}
}