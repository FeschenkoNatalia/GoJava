package ua.nenya.project;

import java.util.ArrayList;
import java.util.List;

public class Project implements GettingNameInterface {
	private String name;
	private String description;
	private int neededAmount;
	private int availableAmount;
	private int daysRemain;
	private String history = "";
	private String video = "";
	private List<Question> questions = new ArrayList<>();
	private Reward anyAmountReward = new Reward();
	private List<Reward> rewards = new ArrayList<>(); 
	

	public Project() {
		anyAmountReward.setName("Any amount");
		anyAmountReward.setDescription("");
		rewards.add(anyAmountReward);
	}


	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getNeededAmount() {
		return neededAmount;
	}

	public int getAvailableAmount() {
		return availableAmount;
	}
	
	public void setAvailableAmount(int availableAmount) {
		this.availableAmount = availableAmount;
	}

	public int getDaysRemain() {
		return daysRemain;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public List<Reward> getRewards() {
		return rewards;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setNeededAmount(int allAmount) {
		this.neededAmount = allAmount;
	}


	public void setDaysRemain(int daysRemain) {
		this.daysRemain = daysRemain;
	}


	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}


	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
}
