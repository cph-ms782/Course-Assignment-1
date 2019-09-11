/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.GroupMember;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rest.ApplicationConfig;
import utils.EMF_Creator;

/**
 *
 * @author frede
 */
public class GroupMemberFacadeTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    //Read this line from a settings-file  since used several places
    private static final String TEST_DB = "jdbc:mysql://localhost:3307/caone_test";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }
    
    public GroupMemberFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        //NOT Required if you use the version of EMF_Creator.createEntityManagerFactory used above        
        //System.setProperty("IS_TEST", TEST_DB);
        //We are using the database on the virtual Vagrant image, so username password are the same for all dev-databases
        
        httpServer = startServer();
        
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
   
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterAll
    public static void tearDownClass() {
        httpServer.shutdownNow();
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNativeQuery("DELETE FROM GROUPMEMBER").executeUpdate();
            //em.createNativeQuery("DROP TABLE MOVIE").executeUpdate(); 
            em.persist(new GroupMember("test", "aaa", 123, "bbb"));
            em.persist(new GroupMember("ttt", "fff", 987, "hhh"));         
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() {
    }


    /**
     * Test of addGroupMember method, of class GroupMemberFacade.
     * Tests if the created groupmember recieves and ID
     */
    @Test
    public void testAddGroupMember() {
        System.out.println("addGroupMember");
        GroupMemberFacade gmf = GroupMemberFacade.getGroupMemberFacade(emf);
        Long groupMemberID = gmf.addGroupMember("a", "b", 1, "s").getId();
        System.out.println(groupMemberID);
        assertNotNull(groupMemberID);
    }

    /**
     * Test of allGroupMembers method, of class GroupMemberFacade.
     * Tests if we get two groupMembers when we ask for all.
     */
    @Test
    public void testAllGroupMembers() {
        System.out.println("allGroupMembers");
        GroupMemberFacade gmf = GroupMemberFacade.getGroupMemberFacade(emf);
        List<GroupMember> result = gmf.allGroupMembers();
        assertEquals(2, result.size());
    }
    
}
