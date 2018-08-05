package banque.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table

public class User {
	
	@Column(name = "user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_id;
	
	@Id
	@Column(name = "name")
	private String name;
	private String firstname;
	private String civility;
	
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
	private List<Account> listeAccount;
	
	public User() {}
	
	public User(String name, String firstname, String civility) {
		this.name = name;
		this.firstname = firstname;
		this.civility = civility;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getCivility() {
		return civility;
	}
	public void setCivility(String civility) {
		this.civility = civility;
	}

	public List<Account> getListeAccount() {
		return listeAccount;
	}

	public void setListeAccount(List<Account> listeAccount) {
		this.listeAccount = listeAccount;
	}
	
	

}
