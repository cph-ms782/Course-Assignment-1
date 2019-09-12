
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.GroupMember;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

public class GroupMemberResourceTest {
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
    
    public GroupMemberResourceTest() {
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
    
//    @AfterEach
//    public void tearDown() {
//    }

    /**
     * Tests if the server is up.
     * 
     * Frederik
     */
   @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/groupmembers").then().statusCode(200);
    }
    
    
    /**
     * Tests if we get the right message when we access the root of the REST endpoint.
     * @throws Exception 
     * 
     * Frederik
     */
    @Test
    public void testDummyMsg() throws Exception {
        given()
        .contentType("application/json")
        .get("/groupmembers/").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("msg", equalTo("Hello World"));   
    }

    /**
     * Test of getAllGroupMembers method, of class GroupMemberResource.
     * Tests if we have two items with the right names.
     * 
     * Frederik
     */
    @Test
    public void testGetAllGroupMembers() {
        System.out.println("getAllGroupMembers");
        given()
                .contentType("application/json")
                .get("/groupmembers/all")
                .then().log().body().assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("name", hasItems("test","ttt"));
    }


    
}
