package ua.com.goit.gojava7.kickstarter.dao;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import ua.com.goit.gojava7.kickstarter.beans.Category;
import ua.com.goit.gojava7.kickstarter.beans.FAQ;
import ua.com.goit.gojava7.kickstarter.beans.Project;
import ua.com.goit.gojava7.kickstarter.beans.User;
import ua.com.goit.gojava7.kickstarter.util.Utils;

public class ProjectDAO extends CommonDAO<Project> {

    public ProjectDAO() {

        CategoryDAO categoryDAO = new CategoryDAO();
        CommonDAO<FAQ> faqDAO = new FaqDAO();

        Random rnd = new Random();

        List<FAQ> faqList = faqDAO.getAll();

        String videoUrl = "https://www.youtube.com/watch?v=PaEnaoydUUo";

        dataSource
                .add(Project.newBuilder().setName("Xpand Lacing System")
                        .setDescription(
                                "Get your shoes on in 3 seconds flat! No more bows, no more knots, no more tying!")
                .setStartDate(Utils.dateFromString("25.10.2015")).setEndDate(Utils.dateFromString("25.11.2015"))
                .setAuthor(new User("Charles Harris", "")).addCategory(categoryDAO.getByName("Sports"))
                .setVideoURL(videoUrl).setGoalSum(rnd.nextInt(100) * 100L).setBalanceSum(rnd.nextInt(100) * 100L)
                .addFAQ(faqList.get(rnd.nextInt(faqList.size()))).addFAQ(faqList.get(rnd.nextInt(faqList.size())))
                .build());

        dataSource.add(Project.newBuilder().setName("Draw Like a Boss : The Physical Book")
                .setDescription(
                        "Two years in the making and it's finally ready to become a physical instructional book about drawing.")
                .setStartDate(Utils.dateFromString("11.10.2015")).setEndDate(Utils.dateFromString("27.11.2015"))
                .setAuthor(new User("Ash and Eli", "")).addCategory(categoryDAO.getByName("Art")).setVideoURL(videoUrl)
                .setGoalSum(rnd.nextInt(100) * 100L).setBalanceSum(rnd.nextInt(100) * 100L)
                .addFAQ(faqList.get(rnd.nextInt(faqList.size()))).addFAQ(faqList.get(rnd.nextInt(faqList.size())))
                .build());

        dataSource.add(Project.newBuilder().setName("Mini Museum 2: The Second Edition")
                .setDescription(
                        "Billions of years of life, science and history in the palm of your hand! Curated and handcrafted to inspire for generations.")
                .setStartDate(Utils.dateFromString("30.10.2015")).setEndDate(Utils.dateFromString("25.12.2015"))
                .setAuthor(new User("Hans Fex", "")).addCategory(categoryDAO.getByName("Art")).setVideoURL(videoUrl)
                .setGoalSum(rnd.nextInt(100) * 100L).setBalanceSum(rnd.nextInt(100) * 100L)
                .addFAQ(faqList.get(rnd.nextInt(faqList.size()))).addFAQ(faqList.get(rnd.nextInt(faqList.size())))
                .build());

        dataSource.add(Project.newBuilder().setName("FlyKly Smart Ped")
                .setDescription(
                        "This beautifully practical kick assist e-bike is the smartest move around the city as it extends your ride and folds easily.")
                .setStartDate(Utils.dateFromString("30.10.2015")).setEndDate(Utils.dateFromString("30.11.2015"))
                .setAuthor(new User("FlyKly", "")).addCategory(categoryDAO.getByName("Sports")).setVideoURL(videoUrl)
                .setGoalSum(rnd.nextInt(100) * 100L).setBalanceSum(rnd.nextInt(100) * 100L)
                .addFAQ(faqList.get(rnd.nextInt(faqList.size()))).addFAQ(faqList.get(rnd.nextInt(faqList.size())))
                .build());

        dataSource.add(Project.newBuilder().setName("Music for Cats")
                .setDescription(
                        "We need your help to create an album featuring the first-ever music scientifically proven to enrich cats' lives.")
                .setStartDate(Utils.dateFromString("28.10.2015")).setEndDate(Utils.dateFromString("11.01.2016"))
                .setAuthor(new User("David Teie", "")).addCategory(categoryDAO.getByName("Music")).setVideoURL(videoUrl)
                .setGoalSum(rnd.nextInt(100) * 100L).setBalanceSum(rnd.nextInt(100) * 100L)
                .addFAQ(faqList.get(rnd.nextInt(faqList.size()))).addFAQ(faqList.get(rnd.nextInt(faqList.size())))
                .build());

    }

    public List<Project> getByCategory(Category category) {

        return dataSource.stream().filter(project -> project.getCategories().stream()
                .anyMatch(projectCategory -> projectCategory.equals(category))).collect(Collectors.toList());
    }

}