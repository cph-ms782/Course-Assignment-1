package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarDTO;
import entities.Car;
import utils.EMF_Creator;
import facades.CarFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author martin
 */
@Path("cars")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/caone",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final CarFacade FACADE =  CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<Car> list = FACADE.allCars();
        return GSON.toJson(new CarDTO(list));
    }
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarsCount() {
        long count = FACADE.getCarsCount();
        System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarsPerYear(@PathParam("id") Long id) {
        Car cars = FACADE.findByID(id);
        return GSON.toJson(new CarDTO(cars));
    }
    
    @Path("year/{year}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMoviesPerYear(@PathParam("year") int year) {
        List<Car> list = FACADE.findByYear(year);
        return GSON.toJson(new CarDTO(list));
    }
    
    @Path("movie/{title}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMoviesPerTitle(@PathParam("title") String title) {
        List<Car> list = FACADE.findByTitle(title);
        return GSON.toJson(new CarDTO(list));
    }
    
    @Path("fill")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMoviesPerTitle() {
        FACADE.addCar(1976, "Toyota", "Corolla", 2000, "10-02-2013", "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(1985, "VW", "Corolla", 5000, "08-10-2014", "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(2012, "Volvo", "Corolla", 10000, "12-06-2015", "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(1995, "VW", "Corolla", 10000, "01-10-2016", "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(2015, "Toyota", "Corolla", 210505, "25-09-2017", "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(2009, "Volvo", "180", 100000, "19-05-2018", "Frederik", "Der er en ridse i lakken");
        return GSON.toJson("Database filled");
    }

    
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Car entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Car entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
    
    
    
    
}
