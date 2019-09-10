package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarDTO;
import entities.Car;
import utils.EMF_Creator;
import facades.CarFacade;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public String getAllCars() {
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
    
    @Path("fill")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarsPerTitle() {
        FACADE.addCar(1976, "Toyota", "Corolla", 2000, LocalDate.of(2015, 12, 31), "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(1985, "VW", "Polo", 2000, LocalDate.of(2013,5,1), "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(2012, "Volvo", "Corolla", 10000, LocalDate.of(2015,9,22), "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(1995, "VW", "Golf", 10000, LocalDate.of(2016,1,10), "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(2010, "Toyota", "Aygo", 12542, LocalDate.of(2015,5,12), "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(2015, "Toyota", "Corolla", 210500, LocalDate.of(2017,9,25), "Frederik", "Der er en ridse i lakken");
        FACADE.addCar(2009, "Volvo", "180", 100000, LocalDate.of(2018,5,10), "Frederik", "Der er en ridse i lakken");
        return GSON.toJson("Database filled");
    }

}
