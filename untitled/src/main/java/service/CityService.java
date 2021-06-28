package service;

import dataBaseConnectors.CityDAO;
import dataBaseConnectors.ContinentDAO;
import dataBaseConnectors.CountryDAO;
import model.City;
import model.Country;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class CityService {
    private CityDAO cityDAO;
    private CountryDAO countryDAO;
    private ContinentDAO continentDAO;

    public CityService() throws SQLException {
        cityDAO = new CityDAO ();
        continentDAO = new ContinentDAO ();
        countryDAO = new CountryDAO ();
    }

    public void showAllCity() throws SQLException {
        cityDAO.getAllCities ().forEach (System.out::println);
    }

    public void showCityById() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean rightInput;
        int id= 0;
        do {
            System.out.println("Insert Id");
            try {
                id = Integer.parseInt(scanner.next());
                scanner.nextLine();
                rightInput= true;
            } catch (NumberFormatException e) {
                rightInput =false;
                System.out.println("Id is not correct");
            }
        }while (!rightInput);

        City city = cityDAO.getCityById (id);
        if (city!= null) System.out.println(city);
        else System.out.println("This Id does not have a city");
    }

    public void  addCity() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        //check which country there are
        countryDAO.getAllCountries ().forEach (System.out::println);
        System.out.println("Which one of these country do you want to use? Type in a number.");
        //select country
        boolean exists = false;
        int countryId =0;
        while (!exists) {
            countryId = makeACorrectId();
            if (countryDAO.getCountryById (countryId)!=null)
                exists = true;
            else System.out.println("This continent doesn't exist.");
        }
        System.out.println("Insert cityName");
        String cityName = scanner.next();
        City city = new City (cityName, countryDAO.getCountryById (countryId));
        //insert into table
        cityDAO.addCity (city);
        System.out.println("City was made");

    }

    public void updateCity() throws SQLException {
        cityDAO.getAllCities ().forEach (System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        boolean exist = false;
        int currentId = 0;
        while (!exist){
            currentId = makeACorrectId();
            if (cityDAO.getCityById (currentId)!= null) exist = true;
            else System.out.println("City doesn't exist");
        }
        Scanner scanner = new Scanner(System.in);
        City city = cityDAO.getCityById (currentId);
        System.out.println("Do you want to change the name? NA for nothing");
        String answer = scanner.next();
        if(!answer.toUpperCase(Locale.ROOT).equals("NA")){
            System.out.println("What do you want to change it to?");
            String name = scanner.next();
            city.setName(name);
        }
        System.out.println("Do you want to change the Country? NA for nothing");
        answer = scanner.next();
        if(!answer.toUpperCase(Locale.ROOT).equals("NA")){
            System.out.println("What do you want to change it to?");
            int id = scanner.nextInt();
            city.setCountry (countryDAO.getCountryById (id));

        }

        cityDAO.updateCity (city);
        System.out.println("City updated");

    }

    public void deleteACity() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        cityDAO.getAllCities ().forEach (System.out::println);
        System.out.println("Give id of city you want to delete:");
        int cityId = giveExistingCityId();
        System.out.println("Are you sure you want to delete this city:");
        System.out.println(cityDAO.getCityById (cityId));
        System.out.println("Y/N");
        String answer = scanner.next();
        if (answer.toUpperCase(Locale.ROOT).equals("Y")){
            cityDAO.deleteCity (cityDAO.getCityById (cityId));
            System.out.println("City has been deleted");
        }else System.out.println("City has not been deleted");

    }

    private int giveExistingCityId() throws SQLException {
        boolean exist = false;
        int currentId= 0;
        while (!exist){
            currentId = makeACorrectId();
            if (cityDAO.getCityById (currentId)!= null) exist = true;
            else System.out.println("City doesn't exist");
        }
        return currentId;
    }

    private int makeACorrectId(){
        Scanner scanner = new Scanner(System.in);
        boolean rightInput;
        int id= 0;
        do {
            System.out.println("Insert Id");
            try {
                id = Integer.parseInt(scanner.next());
                scanner.nextLine();
                rightInput= true;
            } catch (NumberFormatException e) {
                rightInput =false;
                System.out.println("Id is not correct");
            }
        }while (!rightInput);
        return id;
    }



    //Methods without checks

    public void showCityByIdWithoutCheck() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give an id of a country");
        int id = scanner.nextInt();
        Country country = countryDAO.getCountryById(id);
        if(country!=null) System.out.println(country);
        else System.out.println("Country doens't exist");


    }

    public void  addCityWithoutCheck() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        continentDAO.getAllContinents().forEach(System.out::println);
        System.out.println("Which one of these continents do you want to use? Type in a number.");
        int continentId = scanner.nextInt();
        System.out.println("Give me the name of the country");
        String name = scanner.next();
        Country country = new Country(name, continentDAO.getContinentById (continentId));
        countryDAO.addCountry(country);
        System.out.println("Country has been added");

    }

    public void updateCityWithoutCheck() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        countryDAO.getAllCountries().forEach(System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        int currentId = scanner.nextInt();
        Country country = countryDAO.getCountryById(currentId);

        System.out.println("Change the CountryName to something new. NA if you don't want to change it.");
        String answer = scanner.next();
        if (!answer.toUpperCase(Locale.ROOT).equals("NA")){
            country.setName(answer);
        }

        System.out.println("Change the ContinentID to something new. NA if you don't want to change it.");
        answer = scanner.next();
        if (!answer.toUpperCase(Locale.ROOT).equals("NA")){
            country.setContinent (continentDAO.getContinentById (Integer.parseInt(answer)));
        }

        countryDAO.updateCountry(country);
        System.out.println("Country has been updated");


    }
}
