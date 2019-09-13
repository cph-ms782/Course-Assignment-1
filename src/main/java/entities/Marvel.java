package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * 
 * @author christian
 */
@Entity
@NamedQuery(name = "Marvel.deleteAllRows", query = "DELETE from Marvel")
public class Marvel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marvelID;
    private int year;
    private String titel;
    
    
    public Marvel() {
    }

    public Marvel( int year, String titel) {
        this.year = year;
        this.titel = titel;
    }

    public Long getMarvelID() {
        return marvelID;
    }

    public void setMarvelID(Long marvelID) {
        this.marvelID = marvelID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

   

}
