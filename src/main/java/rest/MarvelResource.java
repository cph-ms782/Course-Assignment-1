package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarDTO;
import dto.MarvelDTO;
import entities.Marvel;
import utils.EMF_Creator;
import facades.MarvelFacade;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Christian
 */
@Path("marvel")
public class MarvelResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/caone",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final MarvelFacade FACADE = MarvelFacade.getMarvelFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMarvel() {
        List<Marvel> list = FACADE.allMarvel();
        List<MarvelDTO> newList = new ArrayList();
        for (Marvel marvel : list) {
            newList.add(new MarvelDTO(marvel));
        }
        return GSON.toJson(newList);
    }


    @Path("fill")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarsPerTitle() {
        FACADE.addMarvel(2008,"Iron Man");
        FACADE.addMarvel(2008,"The Incredible Hulk");
        FACADE.addMarvel(2010,"Iron Man2");
        FACADE.addMarvel(2011,"Thor");
        FACADE.addMarvel(2011,"Captain America");
        FACADE.addMarvel(2012,"Avengers");
        
        
        return GSON.toJson("Database filled");
    }

}
