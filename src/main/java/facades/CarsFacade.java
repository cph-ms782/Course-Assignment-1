package facades;

import entities.Cars;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CarsFacade {

    private static CarsFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CarsFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarsFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarsFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getCarsCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM Cars r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
    }


    public Cars addCar(int year, String make, String model, int price, String created, String owner, String notes) {
        Cars car = new Cars(year, make, model, price, created, owner, notes);
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

    public Cars findByID(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Cars book = em.find(Cars.class, id);
            return book;
        } finally {
            em.close();
        }
    }

    public Long getNumberOfCars() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> num = em.createQuery("Select COUNT(c) from Cars c", Long.class);
            return num.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Cars> allCars() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cars> query
                    = em.createQuery("Select cars from Cars cars", Cars.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Cars> findByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cars> query
                    = em.createQuery("Select cars from Movie cars where Cars.title = :title", Cars.class)
                    .setParameter("title", title);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Cars> findByYear(int year) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cars> query
                    = em.createQuery("Select cars from Cars cars where cars.year = :year", Cars.class)
                    .setParameter("year", year);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public static void main(String[] args) {
        
    }
    
    
}