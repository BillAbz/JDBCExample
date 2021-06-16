package dataBaseConnectors;

import model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    private Connection connection;

    public CountryDAO() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://moktok.intecbrussel.org:33062/bilalabz",
                "bilalabz", "bilalabz2021");
    }


    public Country getCountryById(int id) throws SQLException {
        Statement statement = connection.createStatement ();
        String select = "SELECT * FROM Country WHERE id = "+id+";";
        ResultSet resultSet = statement.executeQuery (select);
        Country country = null;
        while (resultSet.next ()){
            country = new Country (resultSet.getInt ("id"), resultSet.getString ("name"), resultSet.getInt ("continent"));
        }

        return country;
    }

    public List<Country> getAllCountries() throws SQLException {
        Statement statement = connection.createStatement();
        String select = "SELECT * FROM Country;";
        ResultSet resultSet = statement.executeQuery(select);
        List<Country> countryList = new ArrayList<Country> ();
        while (resultSet.next()){
            Country country = new Country(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("continentId"));
            countryList.add(country);
        }
        return countryList;
    }

    public void addCountry(Country country) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Country (name, continentId) VALUES (?,?);");
        preparedStatement.setString(1, country.getName());
        preparedStatement.setInt(2, country.getContinentId());
        preparedStatement.execute();
    }

    public void updateCountry(Country country, int id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement ("UPDATE Country SET name = ?, continentId = ? WHERE id = ?");
        preparedStatement.setString (1, country.getName ());
        preparedStatement.setInt (2, country.getContinentId ());
        preparedStatement.setInt (3, id);
        preparedStatement.execute ();

    }
    public void deleteCountryWithId(int id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement ("DELETE FROM Country WHERE id =?");
        preparedStatement.setInt (1, id);
        preparedStatement.execute ();



    }

    public void deleteCountry(Country country) throws SQLException {
        Statement statement = connection.createStatement();
        String delete= "Delete FROM Country WHERE id = "+country.getId()+";";
        statement.executeUpdate(delete);
    }
}
