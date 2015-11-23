package ua.com.goit.gojava7.kickstarter.dao;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.beans.Category;
import ua.com.goit.gojava7.kickstarter.beans.Project;

public interface ProjectDAO {
	
	public void add(Project project);

	public void remove(Project project);
	
	public List<Project> getAll();
	
	public int getSize();

	public List<Project> getProjectsFromCategory(Category category);

}
