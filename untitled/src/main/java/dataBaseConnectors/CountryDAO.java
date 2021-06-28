package dataBaseConnectors;

import model.Continent;
import model.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    private EntityManagerFactory emf;

    public CountryDAO() throws SQLException {
        emf = EMFactory.getEMF ();
    }


    public Country getCountryById(int id) throws SQLException {
        EntityManager em  = emf.createEntityManager ();
        return em.find (Country.class, id);
    }

    public List<Country> getAllCountries() throws SQLException {
        EntityManager em = emf.createEntityManager ();
        Query query = em.createQuery ("From Country", Country.class);
        List<Country> countryList = query.getResultList ();
        return countryList;
    }

    public void addCountry(Country country) throws SQLException {
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.persist (country);
        em.getTransaction ().commit ();
    }

    public void updateCountry(Country country) throws SQLException {

        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.merge (country);
        em.getTransaction ().commit ();

    }
    /*
    public void deleteCountryWithId(int id) throws SQLException {

        PreparedStatement preparedStatement = emf.prepareStatement ("DELETE FROM Country WHERE id =?");
        preparedStatement.setInt (1, id);
        preparedStatement.execute ();
    }
     */

    public void deleteCountry(Country country) throws SQLException {
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.remove (em.find (Country.class, country.getId ()));
        em.getTransaction ().commit ();
    }
}
