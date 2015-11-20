package ua.com.goit.gojava7.kickstarter.storage_in_memory;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.beans.Payment;
import ua.com.goit.gojava7.kickstarter.beans.Project;
import ua.com.goit.gojava7.kickstarter.dao.AbstractMemoryStorage;

public class PaymentStorage extends AbstractMemoryStorage<Payment> {
	
	public int getSumProjectPayments(Project project) {
		List<Payment> allProjectPayments = getAll();
		
		if (allProjectPayments.size() == 0) {
			return 0;
		} else {
			int result = 0;		
			for (int index = 0; index < allProjectPayments.size(); index++) {
				Payment payment = allProjectPayments.get(index);
				if (payment.getProjectID() == project.getUniqueID()) {
					result += payment.getDonatingSum();
				}
			}
			return result;
		}
	}
}
