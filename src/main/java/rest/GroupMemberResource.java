
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.CarsFacade;
import facades.GroupMemberFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

@Path("groupmembers")
public class GroupMemberResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/caone",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final GroupMemberFacade FACADE = GroupMemberFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMembers(){
        return GSON.toJson(FACADE.allMembers());
    }
    
    @Path("fill")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String fill(){
        FACADE.addMember("Mads-Ulrik Hansen", "yellow", 1992, "Han hedder Madsu");
        FACADE.addMember("Frederik Thorup", "yellow", 1989, "Hellerup er lækker");
        FACADE.addMember("Christian Kehr", "yellow", 1988, "Vil gerne hjem");
        FACADE.addMember("Martin Sander-Thomsen", "red", 1969, "Han er smart");
        FACADE.addMember("Simon Kruse", "Orange", 1991, "Han skal til at flytte");
        return GSON.toJson("Database filled with members");
    }
    
}
