package vadyazakusylo.kickstarter.view;

import java.util.List;

import vadyazakusylo.kickstarter.controller.Controller;
import vadyazakusylo.kickstarter.model.Category;
import vadyazakusylo.kickstarter.model.Model;
import vadyazakusylo.kickstarter.model.Project;
import vadyazakusylo.kickstarter.model.exception.GettingDateException;
import vadyazakusylo.kickstarter.view.factory.State;
import vadyazakusylo.kickstarter.view.input.Input;
import vadyazakusylo.kickstarter.view.output.Output;

public class ProjectsView extends ViewAbstract {

	public ProjectsView(Model model, Controller controller, Input input, Output output) {
		super(model, controller, input, output);
	}

	@Override
	public void printHeader() {
		output.write("------------Choose one of the project-------------");
	}

	List<Project> projectsList;

	@Override
	public void printContent() {
		try {
			Category category = model.getCategory();
			projectsList = model.getProjectsList(category);
			output.write();
			printProjects();
		} catch (GettingDateException e) {
			output.write();
			output.write(e);
		}
		output.write("\n0. Return");
	}

	private void printProjects() {
		for (int oneProject = 1; oneProject <= projectsList.size(); oneProject++) {
			output.write(oneProject + ". " + projectsList.get(oneProject - 1).getName());
			output.write(projectsList.get(oneProject - 1).getShortContent());
		}
	}

	@Override
	public State chooseDirection() {
		int inputNumber = input.readInt();
		if (inputNumber == 0) {
			state = State.CATEGORIES;
		} else if (inputNumber > 0 && inputNumber <= projectsList.size()) {
			state = State.PROJECT;
			setProject(inputNumber - 1);
		} else {
			state = State.ERROR_PROJECTS;
		}
		return state;
	}

	private void setProject(int inputNumber) {
		controller.setProject(projectsList.get(inputNumber));
	}
}