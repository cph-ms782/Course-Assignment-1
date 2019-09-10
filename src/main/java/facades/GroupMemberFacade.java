package facades;

import entities.Cars;
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
    public static GroupMemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupMemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    



    public GroupMember addMember(String name, String color, int birthYear, String note) {
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




    public List<GroupMember> allMembers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<GroupMember> query
                    = em.createQuery("Select m from GroupMember m", GroupMember.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

  
    
    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        GroupMemberFacade mf = GroupMemberFacade.getMemberFacade(emf);
        GroupMember m = mf.addMember("test", "test", 0, "test");
        System.out.println(m);
    }
    
    
}
