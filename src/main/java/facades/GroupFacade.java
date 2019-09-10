package facades;

import entities.Group;
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
    public static GroupFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getGroupCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long GroupCount = (long)em.createQuery("SELECT COUNT(r) FROM Group r").getSingleResult();
            return GroupCount;
        }finally{  
            em.close();
        }
    }


    public Group addMember(String title, int year) {
        Group group = new Group(title, year);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(group);
            em.getTransaction().commit();
            return group;
        } finally {
            em.close();
        }
    }

    public Group findByID(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Group group = em.find(Group.class, id);
            return group;
        } finally {
            em.close();
        }
    }

    public Long getNumberOfMembers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> num = em.createQuery("Select COUNT(c) from Group c", Long.class);
            return num.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Group> allMembers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Group> query
                    = em.createQuery("Select group from Group group", Group.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Group> findByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Group> query
                    = em.createQuery("Select group from Group group where group.title = :title", Group.class)
                    .setParameter("title", title);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Group> findByYear(int year) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Group> query
                    = em.createQuery("Select group from Group group where group.year = :year", Group.class)
                    .setParameter("year", year);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public static void main(String[] args) {
        
    }
    
    
}
