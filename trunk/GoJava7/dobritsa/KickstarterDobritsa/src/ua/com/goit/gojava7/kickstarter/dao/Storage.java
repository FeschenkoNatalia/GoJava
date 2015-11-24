package ua.com.goit.gojava7.kickstarter.dao;

import java.util.List;

public interface Storage<T> {
	
	List<T> getAll();

	T get(int index);

	int size();

}
