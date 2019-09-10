package dto;

import entities.Cars;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msi
 */
public class CarsDTO {

    private Long id;
    private String title;
    private int year;
    private List<Cars> list;

    public CarsDTO(Cars m) {
        this.id = m.getId();
//        this.title = m.getTitle();
//        this.year = m.getYear();
    }

    public CarsDTO(List<Cars> listCars) {
        list = new ArrayList();
        for (Cars car : listCars) {
            this.list.add(car);
        }
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setList(List<Cars> listCars) {
        for (Cars car : listCars) {
            this.list.add(car);
        }
    }

}
