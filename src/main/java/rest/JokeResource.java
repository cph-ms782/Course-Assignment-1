package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Joke;
import facades.JokeFacade;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/caone",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final JokeFacade FACADE = JokeFacade.getJokesFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        List<Joke> list = FACADE.allJokes();
        return GSON.toJson(list);
    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeById(@PathParam("id") Long id) {
        Joke joke = FACADE.findByID(id);
        return GSON.toJson(joke);
    }

    @Path("fill")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String Fill() {
        FACADE.addJoke("a", "Offensive", "web");
        FACADE.addJoke("b", "Offensive", "web");
        FACADE.addJoke("c", "Offensive", "web");
        FACADE.addJoke("d", "Offensive", "web");
        FACADE.addJoke("e", "Mild", "web");
        FACADE.addJoke("f", "Mild", "web");
        FACADE.addJoke("g", "Mild", "web");
        FACADE.addJoke("h", "Mild", "web");
        FACADE.addJoke("i", "Mild", "web");
        FACADE.addJoke("j", "Mild", "web");
        FACADE.addJoke("k", "Mild", "web");
        FACADE.addJoke("l", "Mild", "web");
        FACADE.addJoke("m", "Mild", "web");
        return GSON.toJson("Database filled");
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Joke entity) {
        throw new UnsupportedOperationException();
    }

}
