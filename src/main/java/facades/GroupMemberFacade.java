package facades;

import entities.Car;
import entities.GroupMember;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class GroupMemberFacade {

    private static GroupMemberFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private GroupMemberFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static GroupMemberFacade getGroupMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupMemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    



    public GroupMember addGroupMember(String name, String color, int birthYear, String note) {
        GroupMember m = new GroupMember(name, color, birthYear, note);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            return m;
        } finally {
            em.close();
        }
    }




    public List<GroupMember> allGroupMembers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<GroupMember> query
                    = em.createQuery("Select m from GroupMember m", GroupMember.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

  
    
    
}
