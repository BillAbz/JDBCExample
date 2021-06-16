package model;

public class Country {
    private int id;
    private String name;
    private int continentId;

    public Country(String name, int continentId) {
        this.name = name;
        this.continentId = continentId;
    }

    public Country(int id, String name, int continentId) {
        this.id = id;
        this.name = name;
        this.continentId = continentId;
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

    public int getContinentId() {
        return continentId;
    }

    public void setContinentId(int continentId) {
        this.continentId = continentId;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", continentId=" + continentId +
                '}';
    }

}
