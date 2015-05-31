package com.goit.kickstarter.glmax.model;

import java.util.*;

import com.goit.kickstarter.glmax.controller.Position;
import com.goit.kickstarter.glmax.enteties.Category;
import com.goit.kickstarter.glmax.enteties.Project;

public class LocalDataSource implements DataSource {
	private ArrayList<Category> categories = new ArrayList<Category>();
	private HashMap<Category, ArrayList<Project>> projects = new HashMap<Category, ArrayList<Project>>();

	public LocalDataSource() {
		for (int i = 0; i < 6; i++) {
			Category category = new Category(i, "Category " + i);
			categories.add(category);
			projects.put(category, new ArrayList<Project>());
			for (int j = 0; j < 6; j++) {
				Project project = new Project(i * j, "project " + i * j,
						"short description of project" + i * j, i * j * 10, i
								* j, i * j / 2, "some histry", "video URL",
						null);
				projects.get(category).add(project);
			}
		}
	}

	@Override
	public ArrayList<Category> getCategoriesList() {
		return categories;
	}

	@Override
	public String getSomeQuote() {
		return "some qoute";
	}

	@Override
	public ArrayList<Project> getProjectsList(int categoryIndex) {
		return projects.get(categories.get(categoryIndex - 1));
	}

	@Override
	public String getCategoryName(int categoryIndex) {
		return categories.get(categoryIndex - 1).getName();
	}

	@Override
	public Project getProject(int categoryIndex, int projectIndex) {
		return getProjectsList(categoryIndex).get(projectIndex - 1);
	}


	@Override
	public ArrayList<Integer> getChoisList(Position currentLevel,
			Integer currentMenuObjectIndex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		if (currentLevel == Position.Main) {
			for (Category category : categories) {
				result.add(result.size()+1);
			}
			result.add(0);
		} else if (currentLevel == Position.Category) {
			for (Project project : projects.get(categories.get(currentMenuObjectIndex))) {
				result.add(result.size()+1);
			}
			result.add(0);
		} else if (currentLevel == Position.Project) {
			result.add(1);
			result.add(2);
			result.add(0);
		} else if (currentLevel == Position.Payment) {
			result.add(0);
		} else if (currentLevel == Position.Question) {
			result.add(0);
		}
		return result;
	}

	@Override
	public void persistData() {
		// TODO Auto-generated method stub
		
	}


}