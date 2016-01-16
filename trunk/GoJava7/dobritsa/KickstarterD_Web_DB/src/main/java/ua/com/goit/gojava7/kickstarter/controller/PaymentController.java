package ua.com.goit.gojava7.kickstarter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.goit.gojava7.kickstarter.validator.MyValidator;
import ua.com.goit.gojava7.kickstarter.dao.PaymentDao;
import ua.com.goit.gojava7.kickstarter.dao.ProjectDao;
import ua.com.goit.gojava7.kickstarter.dao.RewardDao;
import ua.com.goit.gojava7.kickstarter.model.Project;
import ua.com.goit.gojava7.kickstarter.model.Reward;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Transactional
@Controller
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private RewardDao rewardDao;
    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private MyValidator myValidator;

    private Long rewardId;
    private Long projectId;
    private String amount;
    private ModelAndView modelAndView;

//TODO check 500
//TODO arg return
    @RequestMapping("/payment")
    public ModelAndView showPayment(@RequestParam Long rewardId, @RequestParam(required = false) Long projectId,
                                    @RequestParam(required = false) String amount) throws ServletException, IOException {

        //TODO log
        log.info("showPayment()...");
        this.rewardId = rewardId;
        modelAndView = new ModelAndView();
        this.amount = amount;
        this.projectId = projectId;

        if (rewardExists()) {
            return payWithReward();
        }

        if(amountIsValid()) {
            return payWithAmount();
        }

        Project project = projectDao.get(projectId);

        modelAndView.addObject(project);
        modelAndView.addObject("category", project.getCategory());



        return returnWarning();
    }

    private boolean rewardExists() {
        log.info("rewardExists()...");
        return rewardId != 0;
    }

    private ModelAndView payWithReward() {
        log.info("payWithReward()...");

        Reward reward = rewardDao.get(rewardId);
        Long amount = reward.getAmount();
        Project project = reward.getProject();

        modelAndView.setViewName("payment");
        modelAndView.addObject("amount", amount);
        modelAndView.addObject("project", project);
        modelAndView.addObject("category", project.getCategory());
        return modelAndView;
    }

    private boolean amountIsValid() {
        log.info("amountIsValid()...");
        return myValidator.validateAmountOfPledge(amount);
    }

    public ModelAndView payWithAmount(){
        log.info("payWithAmount()...");
        modelAndView.setViewName("payment");
        modelAndView.addObject("amount", Long.parseLong(amount));
        return modelAndView;
    }

    private ModelAndView returnWarning() {
        log.info("returnWarning()...");
        List<Reward> rewards = rewardDao.getByProject(projectId);

        modelAndView.setViewName("reward");
        modelAndView.addObject(rewards);
        modelAndView.addObject("message", "-----Wrong amount-----");
        return modelAndView;
    }

    @RequestMapping(value = "/paymentCheck", method = RequestMethod.POST)
    public ModelAndView checkPayment(@RequestParam Long projectId, @RequestParam Long amount,
                                     @RequestParam String name, @RequestParam String card) throws ServletException, IOException {
        log.info("checkPayment()...");

        Project project = projectDao.get(projectId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category", projectDao.getCategory(project));
        modelAndView.addObject("project", project);
        modelAndView.addObject("amount", amount);
//TODO move valid to here
        if (paymentDao.createPayment(name, card, amount, project)) {
            modelAndView.setViewName("paymentOk");
            return modelAndView;
        }

        modelAndView.setViewName("payment");
        modelAndView.addObject("message", "-----Wrong data-----");
        return modelAndView;
    }
}