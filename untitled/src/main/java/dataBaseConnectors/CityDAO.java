package dataBaseConnectors;

import model.City;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class CityDAO {
    private EntityManagerFactory emf;

    public CityDAO() throws SQLException {
        emf = EMFactory.getEMF ();
    }

    public City getCityById(int id){
        EntityManager em = emf.createEntityManager ();
        return em.find (City.class, id);
    }

    public List<City> getAllCities(){
        EntityManager em = emf.createEntityManager ();
        Query query =  em.createQuery ("From City", City.class);
        List<City> cityList = query.getResultList ();
        return cityList;

    }

    public void addCity(City city){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.persist (city);
        em.getTransaction ().commit ();
    }

    public void updateCity(City city){
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.merge (city);
        em.getTransaction ().commit ();

    }

    public void deleteCity(City city) {
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.remove (em.find (City.class, city.getId ()));
        em.getTransaction ().commit ();
    }

}
