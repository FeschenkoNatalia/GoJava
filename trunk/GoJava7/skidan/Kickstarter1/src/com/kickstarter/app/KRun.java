package com.kickstarter.app;

import com.kickstarter.db.QuoteStorage;
import com.kickstarter.manager.CategoryManager;
import com.kickstarter.manager.PaymentSystem;
import com.kickstarter.manager.ProjectManager;
import com.kickstarter.manager.QuestionSystem;
import com.kickstarter.util.ConsolePrintView;
import com.kickstarter.util.UserConsoleInputReader;

public class KRun {
	static QuestionSystem questionSystem = new QuestionSystem();
	static QuoteStorage qs = new QuoteStorage();
	static PaymentSystem ps = new PaymentSystem();
	static ConsolePrintView consolePrint = new ConsolePrintView();
	static CategoryManager categoryManager = new CategoryManager();
	static ProjectManager projectManager = new ProjectManager();
	static int EXIT_SIGN = 0;



	public  void categorySelector() {
		consolePrint.qoutePrint(qs.getQuote());
		try {
			consolePrint.allCategoriesView(categoryManager.getAllCategories());
			consolePrint.categorySelectionInform();
			int categoryNumber = UserConsoleInputReader.readInput();
			if (categoryNumber == EXIT_SIGN) {
				consolePrint.programExitInform();
			} else {
				String categoryTitle = categoryManager.getCategorieByNumber(categoryNumber).getTitle();
				consolePrint.selectedCategoryInformer(categoryTitle);
				consolePrint.categorysProjectsView(projectManager.getAll(categoryTitle));
				// consolePrint.exitInformer();
				projectSelector(categoryNumber, categoryTitle);
			}
		} catch (Exception e) {
			System.out.println("There is no such category available");
			categorySelector();
		}

	}

	public  void projectSelector(int categoryNumber, String categoryTitle) {
		try {
			consolePrint.projectSelectionInform();
			consolePrint.exitInformer();
			projectManager.getAll(categoryTitle);
			consolePrint.viewSelectedCategoryProjects(projectManager.getAll(categoryTitle));
			while (true) {
				int projectNumber = UserConsoleInputReader.readInput();
				if (projectNumber == EXIT_SIGN) {
					categorySelector();
				} else {
					String title = projectManager.getOne(categoryTitle, projectNumber).getTitle();
					consolePrint.choosenProjectTitleInform(title);
					consolePrint.singleCategorysProjectsView(projectManager.getOne(categoryTitle, projectNumber));
					consolePrint.posobilitiesInfirm();
					int selectedAction = UserConsoleInputReader.readInput();
					int payChoise = 200;
					int quastionChoice = 300;
					if (selectedAction == payChoise) {
						ps.makePayment(projectNumber, categoryNumber, categoryTitle);
					}
					if (selectedAction == quastionChoice) {
						questionSystem.provideNewQuestion(projectNumber, categoryNumber, categoryTitle);

					} else {
						projectSelector(categoryNumber, categoryTitle);

					}

				}

			}
		} catch (Exception e) {
			System.out.println("No such project available");
			projectSelector(categoryNumber, categoryTitle);

		}

	}
}