package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "continent")
    private List<Country> countryList;

    public Continent() {
    }

    public Continent(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Continent(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
