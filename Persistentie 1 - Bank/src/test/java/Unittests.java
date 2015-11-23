/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bank.dao.AccountDAOJPAImpl;
import bank.domain.Account;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DatabaseCleaner;

/**
 *
 * @author Jelle
 */
public class Unittests {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");

    public Unittests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /*
     1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     De eerste assert returned null, omdat er nog geen commit is uitgevoerd en het ID dus nog steeds NULL is.
     In de tweede assert wordt er gecontroleerd of het account ID groter is dan 0.
     Aangezien de commit al is geweest, is het ID van het account object dus bijgewerkt.
     2.	Welke SQL statements worden gegenereerd?
     DELETE FROM ACCOUNT
     INSERT INTO ACCOUNT (ACCOUNTNR, BALANCE, THRESHOLD) VALUES (?, ?, ?) bind => [111, 0, 0]
     SELECT LAST_INSERT_ID()
     3.	Wat is het eindresultaat in de database?
     ID: 1 ACCOUNTNR: 111 BALANCE: 0 THRESHOLD: 0
     */
    @Test
    public void vraag1() throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();
        DatabaseCleaner cleaner = new DatabaseCleaner(em2);
        cleaner.clean();
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        //TODO: verklaar en pas eventueel aan
        assertNull(account.getId());
        em.getTransaction().commit();
        System.out.println("AccountId: " + account.getId());
        //TODO: verklaar en pas eventueel aan
        assertTrue(account.getId() > 0L);
    }

    /*
     1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     De eerste assert returned null, omdat er geen commit is uitgevoerd. De tweede assert is null, dus er staan geen accounts in de database.
     2.	Welke SQL statements worden gegenereerd?
     DELETE FROM ACCOUNT
     SELECT ID, ACCOUNTNR, BALANCE, THRESHOLD FROM ACCOUNT
     3.	Wat is het eindresultaat in de database?
     De database is leeg.
     */
    @Test
    public void vraag2() throws SQLException {
        EntityManager em = emf.createEntityManager();
        AccountDAOJPAImpl DAO = new AccountDAOJPAImpl(em);
        EntityManager em2 = emf.createEntityManager();
        DatabaseCleaner cleaner = new DatabaseCleaner(em2);
        cleaner.clean();
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        assertNull(account.getId());
        em.getTransaction().rollback();
        assertTrue(DAO.findAll().isEmpty());
    }

    /*
     1.	Wat is de waarde van asserties en printstatements? Corrigeer verkeerde asserties zodat de test ‘groen’ wordt.
     De eerste assert heb ik aangepast zodat deze true returned.
     De tweede assert is niet true, omdat er een flush() is uitgevoerd en het account ID dus is aangepast door de database.
     2.	Welke SQL statements worden gegenereerd?
     DELETE FROM ACCOUNT
     INSERT INTO ACCOUNT (ACCOUNTNR, BALANCE, THRESHOLD) VALUES (?, ?, ?)
     bind => [111, 0, 0]
     SELECT LAST_INSERT_ID()
     3.	Wat is het eindresultaat in de database?
     ID: 13 ACCOUNTNR: 111 BALANCE: 0 THRESHOLD: 0
     */
    @Test
    public void vraag3() throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();
        DatabaseCleaner cleaner = new DatabaseCleaner(em2);
        cleaner.clean();
        Long expected = -100L;
        Account account = new Account(111L);
        account.setId(expected);
        em.getTransaction().begin();
        em.persist(account);
        //TODO: verklaar en pas eventueel aan
        assertEquals(expected, account.getId()); //Expected: -100, actual: -100
        em.flush();
        //TODO: verklaar en pas eventueel aan
        assertNotEquals(expected, account.getId()); //Expected: -100, actual: 13
        System.out.println(account.getId()); //13
        em.getTransaction().commit();
    }

    /*
     1.	Welke SQL statements worden gegenereerd?
     DELETE FROM ACCOUNT
     INSERT INTO ACCOUNT (ACCOUNTNR, BALANCE, THRESHOLD) VALUES (?, ?, ?)
     bind => [114, 400, 0]
     SELECT LAST_INSERT_ID()
     SELECT ID, ACCOUNTNR, BALANCE, THRESHOLD FROM ACCOUNT WHERE (ID = ?)
     bind => [16]
     2.	Wat is het eindresultaat in de database?
     ID: 22 ACCOUNTNR: 114 BALANCE: 400 THRESHOLD: 0
     */
    @Test
    public void vraag4() throws SQLException {
        EntityManager em = emf.createEntityManager();
        DatabaseCleaner cleaner = new DatabaseCleaner(emf.createEntityManager());
        cleaner.clean();

        Long expectedBalance = 400L;
        Account account = new Account(114L);
        em.getTransaction().begin();
        em.persist(account);
        account.setBalance(expectedBalance);
        em.getTransaction().commit();

        assertEquals(expectedBalance, account.getBalance());
        System.out.println("Account balance: " + account.getBalance()); //400
        //TODO: verklaar de waarde van account.getBalance
        //account.getBalance() returned 400, omdat het balance is veranderd naar 400 en vervolgens is er een commit() aangeroepen.
        Long acId = account.getId();
        account = null;
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        Account found = em2.find(Account.class, acId);
        //TODO: verklaar de waarde van found.getBalance
        //Het account met het ID van account (dezelfde row dus) wordt opgehaald uit de database.
        //Vervolgens wordt het balans opgevraagd van dit account.
        System.out.println("Found balance: " + found.getBalance()); //400
        assertEquals(expectedBalance, found.getBalance());
    }

    /*
     1.	Welke SQL statements worden gegenereerd?
     DELETE FROM ACCOUNT
     INSERT INTO ACCOUNT (ACCOUNTNR, BALANCE, THRESHOLD) VALUES (?, ?, ?)
     bind => [114, 400, 0]
     SELECT LAST_INSERT_ID()
     SELECT ID, ACCOUNTNR, BALANCE, THRESHOLD FROM ACCOUNT WHERE (ID = ?)
     bind => [30]
     2.	Wat is het eindresultaat in de database?
     ID: 30  ACCOUNTNR: 114 BALANCE: 400 THRESHOLD: 0
     */
    @Test
    public void vraag5() throws SQLException {
        EntityManager em = emf.createEntityManager();
        DatabaseCleaner cleaner = new DatabaseCleaner(emf.createEntityManager());
        cleaner.clean();

        Long expectedBalance = 400L;
        Account account = new Account(114L);
        em.getTransaction().begin();
        em.persist(account);
        account.setBalance(expectedBalance);
        em.getTransaction().commit();
        assertEquals(expectedBalance, account.getBalance());
        //TODO: verklaar de waarde van account.getBalance
        //account.getBalance() returned 400, omdat het balance is veranderd naar 400 en vervolgens is er een commit() aangeroepen.
        Long acId = account.getId();
        account = null;

        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        Account found = em2.find(Account.class, acId);
        found.setBalance(-200L);
        em2.persist(found);
        account = em2.find(Account.class, found.getId());
        //TODO: verklaar de waarde van found.getBalance
        //Het balans van found wordt veranderd naar -200 en dan wordt persist() aangeroepen.
        //De balans wordt dus veranderd, maar nog niet in de database gezet.
        System.out.println(account.getBalance()); //-200L
        System.out.println(found.getBalance()); //-200L
        assertEquals(account.getBalance(), found.getBalance());
    }

    @Test
    public void vraag6() throws SQLException {
        EntityManager em = emf.createEntityManager();
        AccountDAOJPAImpl DAO = new AccountDAOJPAImpl(em);
        DatabaseCleaner cleaner = new DatabaseCleaner(emf.createEntityManager());
        cleaner.clean();

        Account acc = new Account(1L);
        Account acc2 = new Account(2L);
        Account acc9 = new Account(9L);

        // scenario 1
        Long balance1 = 100L;
        em.getTransaction().begin();
        em.persist(acc);
        acc.setBalance(balance1);
        em.getTransaction().commit();
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifieren.
        assertEquals(balance1, acc.getBalance());
        assertEquals(new Long(0), acc.getThreshold());
        assertEquals(new Long(1), acc.getAccountNr());
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id.
        acc = em.find(Account.class, acc.getId());
        assertEquals(balance1, acc.getBalance());
        assertEquals(new Long(0), acc.getThreshold());
        assertEquals(new Long(1), acc.getAccountNr());

        // scenario 2
        Long balance2a = 211L;
        acc = new Account(2L);
        em.getTransaction().begin();
        acc9 = em.merge(acc);
        acc.setBalance(balance2a);
        acc9.setBalance(balance2a + balance2a);
        em.getTransaction().commit();
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifiëren.
        assertEquals(new Long(2), acc.getAccountNr());
        assertEquals(new Long(0), acc.getThreshold());
        assertEquals(balance2a, acc.getBalance());
        assertEquals(new Long(balance2a + balance2a), acc9.getBalance());
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id. 
        // HINT: gebruik acccountDAO.findByAccountNr
        acc = DAO.findByAccountNr(acc.getAccountNr());
        assertEquals(new Long(2), acc.getAccountNr());
        assertEquals(new Long(0), acc.getThreshold());
        assertEquals(new Long(balance2a + balance2a), acc.getBalance());
        assertEquals(new Long(balance2a + balance2a), acc9.getBalance());

        // scenario 3
        Long balance3b = 322L;
        Long balance3c = 333L;
        acc = new Account(3L);
        em.getTransaction().begin();
        acc2 = em.merge(acc);
        assertFalse(em.contains(acc)); // verklaar
        assertTrue(em.contains(acc2)); // verklaar
        assertNotEquals(acc, acc2);  //verklaar
        acc2.setBalance(balance3b);
        acc.setBalance(balance3c);
        em.getTransaction().commit();
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifiëren.
        assertEquals(new Long(3), acc.getAccountNr());
        assertEquals(balance3b, acc2.getBalance());
        assertEquals(balance3c, acc.getBalance());
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id.
        acc = em.find(Account.class, acc2.getId());
        assertEquals(new Long(3), acc2.getAccountNr());
        assertEquals(balance3b, acc2.getBalance());

        // scenario 4
        Account account = new Account(114L);
        account.setBalance(450L);
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        em2.persist(account);
        em2.getTransaction().commit();
        //Account:
        //Nummer: 114 Balans: 450

        Account account2 = new Account(114L);
        Account tweedeAccountObject = account2;
        tweedeAccountObject.setBalance(650l);
        assertEquals((Long) 650L, account2.getBalance());  //verklaar
        //tweedeAccountObject == account2, dus ook hetzelfde balans.
        account2.setId(account.getId());
        em2.getTransaction().begin();
        account2 = em2.merge(account2);
        assertSame(account, account2);  //verklaar
        //account en account2 hebben hetzelfde ID.
        assertTrue(em2.contains(account2));  //verklaar
        //account2 is managed door em2.
        assertFalse(em2.contains(tweedeAccountObject));  //verklaar
        //tweedeAccountObject is managed door em2.
        tweedeAccountObject.setBalance(850l);
        assertEquals((Long) 650L, account.getBalance());  //verklaar
        //account == account2, dus ook zelfde balans.
        assertEquals((Long) 650L, account2.getBalance());  //verklaar
        //account == account2, dus ook zelfde balans.
        em2.getTransaction().commit();
        em2.close();
    }

    @Test
    public void vraag7() {
        EntityManager em = emf.createEntityManager();
        Account acc1 = new Account(77L);
        em.getTransaction().begin();
        em.persist(acc1);
        em.getTransaction().commit();
        //Database bevat nu een account.

        // scenario 1        
        Account accF1;
        Account accF2;
        accF1 = em.find(Account.class, acc1.getId());
        accF2 = em.find(Account.class, acc1.getId());
        assertSame(accF1, accF2);

        // scenario 2        
        accF1 = em.find(Account.class, acc1.getId());
        em.clear();
        accF2 = em.find(Account.class, acc1.getId());
        assertNotSame(accF1, accF2);
        //TODO verklaar verschil tussen beide scenario's
        /*
         Scenario 1:
         Beide accounts zijn gelijk.
         Scenario 2:
         Doordat clear() wordt gebruikt worden alle managed entities verwijderd en daardoor zijn de objecten niet meer gelijk aan elkaar.
         */
    }

    @Test
    public void vraag8() {
        EntityManager em = emf.createEntityManager();
        Account acc1 = new Account(88L);
        em.getTransaction().begin();
        em.persist(acc1);
        em.getTransaction().commit();
        Long id = acc1.getId();
        //Database bevat nu een account.

        em.remove(acc1);
        assertEquals(id, acc1.getId());
        Account accFound = em.find(Account.class, id);
        assertNull(accFound);
        //TODO: verklaar bovenstaande asserts
        /*
         Voor de eerste assertEquals wordt acc1 uit de EntityManager verwijderd.
         In de tweede assertEquals is accFound null, omdat...
         */
    }

    @Test
    public void vraag9() throws SQLException {
        EntityManager em = emf.createEntityManager();
        DatabaseCleaner cleaner = new DatabaseCleaner(emf.createEntityManager());
        cleaner.clean();
        
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        assertNull(account.getId());
        em.getTransaction().commit();
        System.out.println("AccountId: " + account.getId());
        assertTrue(account.getId() > 0L);
    }

}
