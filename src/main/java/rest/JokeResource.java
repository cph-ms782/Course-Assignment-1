package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Joke;
import facades.JokeFacade;
import utils.EMF_Creator;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Simon
 */
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

    @Path("random")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandomJokeById(@PathParam("random") Long id) {
        int amount = FACADE.allJokes().size();
        Random rand = new Random();
        long number = (long) (Math.ceil(rand.nextDouble() * amount));
        Joke joke = FACADE.findByID(number);
        return GSON.toJson(joke);
    }

    @Path("fill")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String Fill() {
        FACADE.addJoke("Hvor mange sten gik der til det Runde Tårn? - Ingen, fordi sten kan jo ikke gå.", "Plat", "de-sjove-jokes.dk/daarlige-jokes/");
        FACADE.addJoke("Gæsten til tjeneren: Hvad er det for et grimt billede, i har hængende der? - Det er ikke et billede, hr, det er et spejl.", "Plat", "de-sjove-jokes.dk/daarlige-jokes/");
        FACADE.addJoke("Lille Hanne har været med sine forældre ude og flyve. Da hun går gennem tolden, vil Tolderen drille hende lidt. Har du nogen cigaretter i lommen, lille frøken? Nej dem har mor i hatten.", "Plat", "de-sjove-jokes.dk/daarlige-jokes/");
        FACADE.addJoke("Hvorfor skulle skyen i skole? Fordi den skulle lære og regne.", "Plat", "de-sjove-jokes.dk/daarlige-jokes/");
        FACADE.addJoke("Hvornår kan man komme op til solen uden og brænde sig? Om natten.", "Plat", "de-sjove-jokes.dk/daarlige-jokes/");
        return GSON.toJson("Database filled");
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Joke entity) {
        throw new UnsupportedOperationException();
    }

}
