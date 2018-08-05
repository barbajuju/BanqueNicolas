package banque.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import banque.model.SimpleAccount;
import banque.model.User;

public class MainTest {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque");
		EntityManager em = emf.createEntityManager();
		//User a = new User();
		//a.setName("BERTHET");
		//a.setFirstname("Nicolas");
		//a.setCivility("Mr");
		
		//Account a = new Account(999666111, 4500);
		
		User user = em.find(User.class, "BERTHET");
		SimpleAccount a = new SimpleAccount(56789, user);
		SimpleAccount b = new SimpleAccount(23456, user);
		
		User userb = em.find(User.class, "LEDUC");
		SimpleAccount c = new SimpleAccount(98765, userb);
		SimpleAccount d = new SimpleAccount(6543, userb);
		SimpleAccount e = new SimpleAccount(37649, userb);
		
		//ceci permet d'ouvrir la transaction à direction de la base de données
		em.getTransaction().begin();
		//cela place l'objet a dans le contexte hibernate. il pourrait y avoir plusieurs ligne de commande du type:
		//persist, remove, merge
		em.persist(a);
		em.persist(b);
		em.persist(c);
		em.persist(d);
		em.persist(e);
		
		//em.persist(b);
		//permet de récupérer les données du contexte pour les envoyés vers la base de données
		em.getTransaction().commit();
		
		
		//on peut aller chercher directement des elements sans begin et commit
		//type : find, getReference, refresh
		//Animal b =em.find(Animal.class, 5);
		//System.out.println(b.getNom() + " " + b.getAge());
		
		//autres types lock, contains, flush, clear, getTransaction, close
	}
	
}
