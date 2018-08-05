package banque.test;

import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;

import banque.model.*;


public class Main {
	
	private static Scanner scanner = new Scanner( System.in );
	
	public static void main( String[] args ) {
		
		dspMainMenu();
	}

	public static void dspMainMenu() {
        int response;
        do {
            System.out.println( "****************************************" );
            System.out.println( "*****************Menu*******************" );
            System.out.println( "* 1 - Ajouter un compte                *" );
            System.out.println( "* 2 - D�tail d' un compte              *" );            
            System.out.println( "* 3 - Cr�diter un compte               *" );
            System.out.println( "* 4 - Retirer sur un compte            *" );
            System.out.println( "* 5 - Lister les comptes               *" );
            System.out.println( "* 6 - Quitter                          *" );
            System.out.println( "****************************************" );
            System.out.println( "****************************************" );
            System.out.print( "* Entrez votre choix : " );
            response = scanner.nextInt();
        } while ( 0 >= response || response > 6 );
        scanner.nextLine();
        switch ( response ) {
	        case 1:
	        	addAccount();
	        	break;
            case 2:
                detailAccount();
                break;
            case 3:
                creditAccountMain();
                break;
            case 4:
                debitAccountMain();
                break;
            case 5:
                listAccount();
                break;
        }
    }
	
	public static void addAccount() {}
	
	public static void detailAccount() {
		
		int response;
		
		System.out.print( "* Entrez votre num�ro de compte : " );
		response = scanner.nextInt();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque");
		EntityManager em = emf.createEntityManager();
		
		Account current = em.find(Account.class, response);
		System.out.println("******************");
		System.out.println("Votre compte numero : " + current.getNumber() + " possede un solde de : " + current.getAmount());
		System.out.println("******************");

	}
	
	public static void creditAccountMain() {
		
		int response;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque");
		EntityManager em = emf.createEntityManager();
		
		System.out.print( "* Entrez votre num�ro de compte : " );
		response = scanner.nextInt();
		
		Account current = em.find(Account.class, response);
		System.out.println("******************");
		System.out.println("Votre compte numero : " + current.getNumber() + " possede un solde de : " + current.getAmount());
		System.out.println("******************");

		
		System.out.println("Saisissez le montant � cr�diter : ");
		
		response = scanner.nextInt();
		
		current.creditAccount(response);
		
		em.getTransaction().begin();
		em.persist(current);
		em.getTransaction().commit();
		
		System.out.println("******************");
		System.out.println("Votre nouveau solde est de : " + current.getAmount());
		
	}
	
	public static void debitAccountMain() {
		
		int response;
		
		System.out.print( "* Entrez votre num�ro de compte : " );
		response = scanner.nextInt();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque");
		EntityManager em = emf.createEntityManager();
		
		Account current = em.find(Account.class, response);
		System.out.println("******************");
		System.out.println("Votre compte numero : " + current.getNumber() + " possede un solde de : " + current.getAmount());
		System.out.println("Saisissez le montant � d�biter : ");
		
		response = scanner.nextInt();
		
		current.debitAccount(response);
		
		em.getTransaction().begin();
		em.persist(current);
		em.getTransaction().commit();
		
		System.out.println("******************");
		System.out.println("Votre nouveau solde est de : " + current.getAmount());
	}
	
	public static void listAccount() {}
	
}
