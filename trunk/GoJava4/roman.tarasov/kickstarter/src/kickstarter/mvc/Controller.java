package kickstarter.mvc;

import kickstarter.pages.Page;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public void printView() {
		view.print();
	}

	public void executeCommand(String command) {
		model.update(command);
	}

	public void setPage(int pageIndex) {
		model.setPage(pageIndex);
	}

	public void addPage(Page page, ModelPage modelPage) {
		model.add(page, modelPage);
	}
}
