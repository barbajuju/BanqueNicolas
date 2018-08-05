package banque.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("paid")

public class PaidAccount extends Account {

	public PaidAccount() {
		super();
	};
	
}