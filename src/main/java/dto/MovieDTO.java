package dto;

import entities.Cars;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msi
 */
public class MovieDTO {

    private Long id;
    private String title;
    private int year;
    private List<Cars> list;

    public MovieDTO(Cars m) {
        this.id = m.getId();
        this.title = m.getTitle();
        this.year = m.getYear();
    }

    public MovieDTO(List<Cars> listMovies) {
        list = new ArrayList();
        for (Cars movie : listMovies) {
            this.list.add(movie);
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

    public void setList(List<Cars> listMovies) {
        for (Cars movie : listMovies) {
            this.list.add(movie);
        }
    }

}
