package banque.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("saving")

public class SavingAccount extends Account {
	
	public SavingAccount() {
		super();
	};

}