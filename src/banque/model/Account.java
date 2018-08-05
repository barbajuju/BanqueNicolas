package banque.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
@Table(name="account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")


abstract class Account {

	@Column(name = "account_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int account_id;
	
	@Id
    @Column(name = "number")
	protected int number;
	
    @Column(name = "amount")
	protected int amount;

    @Column(name = "type", updatable=false, insertable=false)
    protected String type;
    
    @ManyToOne
    protected User user;
    
    public int getAccountId () {
    	return account_id;
    }
    
	public Account() {}
	
	
	
	public Account(int number, User user) {
		this.number = number;
		this.user = user;
		}
		public Account(int account_id, int number, int amount, User user, String type) {
		super();
		this.account_id = account_id;
		this.number = number;
		this.amount = amount;
		this.user = user;
		this.type = type;
	}

	
	public Account(int number, int amount) {
		this.number = number;
		this.amount = amount;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	public void creditAccount (int a) {
	
		this.amount += a;
	
	}
		
	public void debitAccount (int a) {
		
		this.amount -= a;
		
	}

}
