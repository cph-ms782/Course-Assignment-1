package facades;

import entities.Car;
import java.time.LocalDate;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//@Disabled
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade facade;
    private static Car m1 = new Car(1980, "Volvo", "m1", 2000, LocalDate.of(2012, 12, 05), "F", "N");
    private static Car m2 = new Car(1975, "BMW", "m2", 2010, LocalDate.of(2013, 11, 05), "E", "S");

    public CarFacadeTest() {
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMMENDED strategy
     */
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = CarFacade.getCarFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
//            em.createNativeQuery("DELETE FROM Car").executeUpdate();
            em.persist(m1);
            em.persist(m2);

            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getCarsCount(), "Expects two rows in the database");
    }

    @Test
    public void allCars() {
        assertEquals(2, facade.allCars().size(), "Expects two rows in the database");
    }

}
