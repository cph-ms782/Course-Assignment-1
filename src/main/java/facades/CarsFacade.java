package facades;

import entities.Cars;
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
    
    public long getMovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
    }


    public Cars addMovie(String title, int year) {
        Cars movie = new Cars(title, year);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return movie;
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

    public Long getNumberOfMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> num = em.createQuery("Select COUNT(c) from Movie c", Long.class);
            return num.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Cars> allMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cars> query
                    = em.createQuery("Select movie from Movie movie", Cars.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Cars> findByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cars> query
                    = em.createQuery("Select movie from Movie movie where movie.title = :title", Cars.class)
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
                    = em.createQuery("Select movie from Movie movie where movie.year = :year", Cars.class)
                    .setParameter("year", year);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public static void main(String[] args) {
        
    }
    
    
}
