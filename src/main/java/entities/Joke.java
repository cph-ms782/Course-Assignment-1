package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Joke.deleteAllRows", query = "DELETE from Joke")
public class Joke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jokeID;
    private String joke;
    private String jokeType;
    private String reference;
    
    public Joke() {
    }

    public Joke(String joke, String jokeType, String reference) {
        this.joke = joke;
        this.jokeType = jokeType;
        this.reference = reference;
    }

    public Long getJokeID() {
        return jokeID;
    }

    public void setJokeID(Long jokeID) {
        this.jokeID = jokeID;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getJokeType() {
        return jokeType;
    }

    public void setJokeType(String jokeType) {
        this.jokeType = jokeType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    
    
}
