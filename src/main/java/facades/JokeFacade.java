package facades;

import entities.Joke;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Simon
 */
public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getJokesFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Joke addJoke(String joke, String jokeType, String reference) {
        Joke j = new Joke(jokeType, jokeType, reference);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(j);
            em.getTransaction().commit();
            return j;
        } finally {
            em.close();
        }
    }

    public Joke findByID(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Joke joke = em.find(Joke.class, id);
            return joke;
        } finally {
            em.close();
        }
    }

    public List<Joke> allJokes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Joke> query
                    = em.createQuery("Select j from Joke j", Joke.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {

    }

}
