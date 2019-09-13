package dto;

import entities.Marvel;
import java.util.List;

/**
 *
 * @author Christian
 */
public class MarvelDTO {

    private Long marvelID;
    private int marvelYear;
    private String titel;

    public MarvelDTO(Marvel m) {
        this.marvelID = m.getMarvelID();
        this.marvelYear = m.getYear();
        this.titel = m.getTitel();
    }

    public void setMarvelID(Long marvelID) {
        this.marvelID = marvelID;
    }

    public void setYear(int year) {
        this.marvelYear = year;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

   }
