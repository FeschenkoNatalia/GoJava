package ua.com.goit.gojava7.kickstarter.dao.file;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.dao.FileDao;
import ua.com.goit.gojava7.kickstarter.dao.storage.ProjectStorage;
import ua.com.goit.gojava7.kickstarter.domain.Project;

public class ProjectFileDao extends FileDao<Project> implements ProjectStorage {

	public ProjectFileDao(List<Project> data) {
		super(data);
	}
	
	@Override
	public Project getByNumber(int number) {
		int index = number - 1;
		return get(index);
	}

}
