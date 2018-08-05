package banque.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("simple")


public class SimpleAccount extends Account {
	
	public SimpleAccount() {
		super();
	};
	
	public SimpleAccount (int number, User user) {
		super(number,user); 
	}

}
