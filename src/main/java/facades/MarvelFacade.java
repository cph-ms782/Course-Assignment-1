package facades;

import entities.Marvel;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MarvelFacade {

    private static MarvelFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MarvelFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MarvelFacade getMarvelFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MarvelFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    


    public Marvel addMarvel(int year, String titel) {
        Marvel marvel = new Marvel( year, titel);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(marvel);
            em.getTransaction().commit();
            return marvel;
        } finally {
            em.close();
        }
    }



    public List<Marvel> allMarvel() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Marvel> query
                    = em.createQuery("Select c from Marvel c", Marvel.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
