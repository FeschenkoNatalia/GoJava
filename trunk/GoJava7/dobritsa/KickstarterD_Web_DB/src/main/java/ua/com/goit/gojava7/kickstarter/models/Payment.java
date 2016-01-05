package ua.com.goit.gojava7.kickstarter.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long paymentId;
	@Column
	private String user;
	@Column
	private String card;
	@Column
	private Integer amount;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id")
	private Project project = new Project();

	public Payment() {		
	}

	public Payment(String user, String card, Integer amount, Project project) {			
		this.user = user;
		this.card = card;
		this.amount = amount;
		this.project = project;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}	

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "paymentId: " + paymentId + "; user: " + user + "; card: " + card + "; amount: " + amount + "; projectId: " + project.getProjectId() + ";";
	}
}
