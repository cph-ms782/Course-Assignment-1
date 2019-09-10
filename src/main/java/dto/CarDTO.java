package dto;

import entities.Car;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msi
 */
public class CarDTO {

    private Long carID;
    private int year;
    private String make;
    private String model;
    private int price;

    private List<Car> list;

    public CarDTO(Car m) {
        this.carID = m.getId();
        this.year = m.getYear();
        this.make = m.getMake();
        this.price = m.getPrice();
    }

    /**
     * Constructor for handling list of cars
     * @param listCars 
     */
    public CarDTO(List<Car> listCars) {
        list = new ArrayList();
        for (Car car : listCars) {
            this.list.add(car);
        }
    }

    public void setId(Long carID) {
        this.carID = carID;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setList(List<Car> listCars) {
        listCars.forEach((car) -> {
            this.list.add(car);
        });
    }

}
