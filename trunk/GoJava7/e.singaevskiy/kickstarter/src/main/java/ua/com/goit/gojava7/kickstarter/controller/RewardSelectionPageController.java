package ua.com.goit.gojava7.kickstarter.controller;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.beans.Project;
import ua.com.goit.gojava7.kickstarter.beans.Reward;

public class RewardSelectionPageController extends PageController<Project> {

    private List<Reward> rewards;

    @Override
    protected void handle() {
        rewards = storageFactory.getRewardDAO().getByProject(request);
        page.showRewards(rewards);

    }

    @Override
    protected boolean isDone() {
        int option_anyAmount = rewards.size() + 1;

        int option = getMenuOptionFromUser(option_anyAmount);
        
        if (option == OPTION_EXIT) {
            return true;
        } else if (option == option_anyAmount) {
            dispatchNext(request, new PaymentPageController());
        } else {
            dispatchNext(rewards.get(option-1), new PaymentWithRewardPageController());
        }

        return true;
    }
}
