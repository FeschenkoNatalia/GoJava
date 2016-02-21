package ua.com.goit.gojava7.kickstarter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.goit.gojava7.kickstarter.dao.db.ProjectDao;
import ua.com.goit.gojava7.kickstarter.dao.db.QuestionDao;
import ua.com.goit.gojava7.kickstarter.model.Project;
import ua.com.goit.gojava7.kickstarter.model.Question;
import ua.com.goit.gojava7.kickstarter.validator.QuestionValidator;

import java.util.Optional;

@Transactional
@Controller
@RequestMapping(value = "/question")
public class QuestionController{
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    private QuestionValidator   validator;
    @Autowired
    private QuestionDao         questionDao;
    @Autowired
    private ProjectDao          projectDao;

    @ModelAttribute("question")
    public Question createEmployeeModel() {
        return new Question();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addQuestion(@RequestParam(name = "projectId") int projectId) {
        ModelAndView modelAndView = new ModelAndView("questionAdd");
        modelAndView.addObject("projectId", projectId);
        logger.info("Returning questionAdd.jsp page");
        return modelAndView;
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public String saveQuestionAction(@RequestParam(name = "projectId") int projectId,
            @ModelAttribute("question") Question question,
            BindingResult bindingResult, Model model) {
        Project project = projectDao.getProject(projectId);
        Optional<Project> optional = Optional.of(project);
        if (!optional.isPresent()) {
            return "questionAdd";
        }
        question.setProject(project);
        validator.validate(question, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.info("Returning empSave.jsp page");
            return "questionAdd";
        }

        logger.info("Returning questionAddSuccess.jsp page");
        model.addAttribute("question", question);
        questionDao.add(question);
        return "questionAddSuccess";
    }

}
