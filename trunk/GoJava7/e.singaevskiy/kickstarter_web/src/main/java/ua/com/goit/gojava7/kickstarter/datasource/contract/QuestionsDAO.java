package ua.com.goit.gojava7.kickstarter.datasource.contract;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.domain.Question;

public interface QuestionsDAO extends DataSource<Question>{
    
    List<Question> getByProject(Long projectId);
    
}
