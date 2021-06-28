package dataBaseConnectors;

import model.Continent;
import model.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContinentDAO {

    private EntityManagerFactory emf;

    public ContinentDAO() throws SQLException {
        emf = EMFactory.getEMF ();
    }

    //TODO: create update, delete and Insert methods

    public Continent getContinentById(int id) throws SQLException {
        EntityManager em = emf.createEntityManager ();
        return em.find (Continent.class,id);
    }


    public List<Continent> getAllContinents() throws SQLException {
        EntityManager em = emf.createEntityManager ();
        Query query = em.createQuery ("From Continent", Continent.class);
        List<Continent> continentList = query.getResultList ();
        return continentList;
    }

    /*public Continent getContinentByCountry(Country country) throws SQLException {
        Statement statement = emf.createStatement();
        String select = "SELECT * FROM Continent WHERE id = "+country.getContinent ().getId ()+";";
        ResultSet resultSet = statement.executeQuery(select);
        Continent continent = null;
        while (resultSet.next())
            continent = new Continent(resultSet.getInt("Id"),resultSet.getString("name"));
        return continent;
    }

     */

    public void addContinent(Continent continent) throws SQLException {
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.persist (continent);
        em.getTransaction ().commit ();
    }

    public void updateContinent(Continent continent) throws SQLException {
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.merge (continent);
        em.getTransaction ().commit ();
    }

    public void deleteContinent(Continent continent) throws SQLException {
        EntityManager em = emf.createEntityManager ();
        em.getTransaction ().begin ();
        em.remove (em.find (Continent.class, continent.getId ()));
        em.getTransaction ().commit ();
    }

}
