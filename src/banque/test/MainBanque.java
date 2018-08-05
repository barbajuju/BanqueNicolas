package banque.test;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;

import banque.model.*;

public class MainBanque {

    private static Scanner scanner = new Scanner( System.in );

    public static void main( String[] args ) {

        dspMainMenu();
    }

    public static void dspMainMenu() {
        int response;
        do {
            System.out.println( "*****************************************" );
            System.out.println( "*****************Menu********************" );
            System.out.println( "* 1 - Lister les comptes                *" );
            System.out.println( "* 2 - Ajouter un compte                 *" );
            System.out.println( "* 3 - Gérer un compte                   *" );
            System.out.println( "*****************************************" );
            System.out.println( "*****************************************" );
            System.out.print( "* Entrez votre choix : " );
            response = scanner.nextInt();
        } while ( 0 >= response || response > 7 );
        scanner.nextLine();
        switch ( response ) {
            case 1:
                listAccount();
                break;
            case 2:
                addAccount();
                break;
            case 3:
                ManageAccount();
                break;
        }
    }

    public static void listAccount() {
        
        String response;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque");
 		EntityManager em = emf.createEntityManager();
    	
    	System.out.println( "************************************************" );
        System.out.println( "****************Veuillez renseigner***************" );
        System.out.println( "Votre nom (vous devez deja être client chez nous) :" );
        response = scanner.nextLine();
        
        User user = em.find(User.class, response);
        
        List<Account> listeAccount = user.getListeAccount();
        
        System.out.println( "***************************************************" );
        System.out.println( "****************Liste des comptes******************" );
        System.out.println( "Voici votre liste de compte(s) M " + response + ": " );
    }

    public static void addAccount() {
    	
    	int response;
    	
    	do {
        System.out.println( "************************************************" );
        System.out.println( "****************Ajout d'un compte***************" );
        System.out.println( "*****************************************" );
        System.out.println( "1 Simple Account" );
        System.out.println( "2 Paid Account" );
        System.out.println( "3 Saving Account" );
        System.out.print( "Entrez le type de compte à créer : " );
        
        response = scanner.nextInt();
        
	    } while ( 0 >= response || response > 3 );
	    scanner.nextLine();
	    switch ( response ) {
	        case 1:
	            SimpleAccountMain();
	            break;
	        case 2:
	           // PaidAccount();
	            break;
	        case 3:
	            //SavingAccount();
	            break;
	    }
       
    }
    
    public static void SimpleAccountMain() {
    	
    	String response; 
    	int response1;
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque");
 		EntityManager em = emf.createEntityManager();
    	
    	System.out.println( "************************************************" );
        System.out.println( "****************Veuillez renseigner***************" );
        System.out.println( "Votre nom (vous devez deja être client chez nous) :" );
        response = scanner.nextLine();
        
        System.out.println( "Un numéro de compte à 9 chiffres qui ne se suivent pas :" );
        response1 = scanner.nextInt();
    	
        User user = em.find(User.class, response);
		SimpleAccount a = new SimpleAccount(response1, user);
		
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		
		System.out.println( "M " + user.getName() + " vous venez de créer le compte " + (em.find(SimpleAccount.class, response1 )).getNumber() );
        
    }

    public static void ManageAccount() {
    	
    	int response;

    	do {
        System.out.println( "************************************************" );
        System.out.println( "**************Gestion d'un compte***************" );
        System.out.println( "Quel type de compte souhaitez vous gérer ?");
        System.out.println( "1 Simple Account" );
        System.out.println( "2 Paid Account" );
        System.out.println( "3 Saving Account" );
        System.out.println( "*****************************************" );
        System.out.print( "* Entrez votre choix : " );
        
        response = scanner.nextInt();
        
	    } while ( 0 >= response || response > 3 );
	    scanner.nextLine();
	    switch ( response ) {
	        case 1:
	            ManageSimpleAccountMain();
	            break;
	        case 2:
	           // ManagePaidAccountMain();
	            break;
	        case 3:
	            //ManageSavingAccountMain();
	            break;
	    }
 
    }
    
    public static void ManageSimpleAccountMain() {
    	
    	int response;
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque");
 		EntityManager em = emf.createEntityManager();
    	
 		System.out.println( "******************************************" );
    	System.out.print( "Entrez le numéro du compte simple à gérer : " );
        response = scanner.nextInt();
 		
        
        SimpleAccount current = em.find(SimpleAccount.class, response);
        
        do {
		System.out.println("******************");
		System.out.println("Votre compte numero : " + current.getNumber() + " possede un solde de : " + current.getAmount());
		System.out.println("******************");
		System.out.println("Que souhaitez-vous faire comme opération ?");
		System.out.println("Tapez 1 pour créditer le compte");
		System.out.println("Tapez 2 pour débiter le compte");
		System.out.println( "*****************************************" );
        System.out.print( "* Entrez votre choix : " );
    	
		 response = scanner.nextInt();
	        
	    } while ( 0 >= response || response > 2 );
	    scanner.nextLine();
	    switch ( response ) {
	        case 1:
	        	CreditSimpleAccountMain(current);
	            break;
	        case 2:
	            DebitSimpleAccountMain(current);
	            break;
	    }
    }
	    
   public static void CreditSimpleAccountMain(SimpleAccount simpleAccount) {
	    	
	   	int response;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque");
		EntityManager em = emf.createEntityManager();
		
		System.out.println( "*****************************************" );
		System.out.println("Saisissez le montant à créditer : ");
		
		response = scanner.nextInt();
		
		simpleAccount.creditAccount(response);
		
		em.getTransaction().begin();
		em.persist(simpleAccount);
		em.getTransaction().commit();
		
		System.out.println("******************");
		System.out.println("Votre nouveau solde est de : " + simpleAccount.getAmount());
	    }
   
   public static void DebitSimpleAccountMain(SimpleAccount simpleAccount) {
   	
	    int response;
	    
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque");
		EntityManager em = emf.createEntityManager();
		
		System.out.println( "*****************************************" );
		System.out.println("Saisissez le montant à débiter : ");
		
		response = scanner.nextInt();
		
		simpleAccount.debitAccount(response);
		
		em.getTransaction().begin();
		em.persist(simpleAccount);
		em.getTransaction().commit();
		
		System.out.println("******************");
		System.out.println("Votre nouveau solde est de : " + simpleAccount.getAmount());
	    
	   
   }
    
}
