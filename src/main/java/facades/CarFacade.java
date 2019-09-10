package facades;

import entities.Car;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CarFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getCarsCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long carsCount = (long)em.createQuery("SELECT COUNT(c) FROM Car c").getSingleResult();
            return carsCount;
        }finally{  
            em.close();
        }
    }


    public Car addCar(int year, String make, String model, int price, String created, String owner, String notes) {
        Car car = new Car(year, make, model, price, created, owner, notes);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
            return car;
        } finally {
            em.close();
        }
    }

    public Car findByID(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Car c = em.find(Car.class, id);
            return c;
        } finally {
            em.close();
        }
    }

    public Long getNumberOfCars() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> num = em.createQuery("Select COUNT(c) from Car c", Long.class);
            return num.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Car> allCars() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Car> query
                    = em.createQuery("Select c from Car c", Car.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Car> findByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Car> query
                    = em.createQuery("Select c from Car c where c.title = :title", Car.class)
                    .setParameter("title", title);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Car> findByYear(int year) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Car> query
                    = em.createQuery("Select c from Car c where c.year = :year", Car.class)
                    .setParameter("year", year);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public static void main(String[] args) {
        
    }
    
    
}
