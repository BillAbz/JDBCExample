package service;

import dataBaseConnectors.ContinentDAO;
import dataBaseConnectors.CountryDAO;
import model.Continent;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class ContinentService {
    private ContinentDAO continentDAO;
    private CountryDAO countryDAO;

    public ContinentService() throws SQLException {
        continentDAO = new ContinentDAO ();
        countryDAO = new CountryDAO ();
    }

    public void showAllContinents() throws SQLException {
        continentDAO.getAllContinents ().forEach (System.out::println);
    }

    public void showContinentById() throws SQLException {
        Continent continent = continentDAO.getContinentById (giveExistingContinentId ());
        if (continent!= null) System.out.println (continent);
        else System.out.println ("This Id does not have a continent!");
    }

    public void addContinent() throws SQLException {
        Scanner scanner = new Scanner (System.in);
        System.out.println ("Insert continent name:");
        String continentName = scanner.next ();
        Continent continent = new Continent (continentName);
        continentDAO.addContinent (continent);
        System.out.println ("Continent was made!");
    }

    public void updateContinent() throws SQLException {
        continentDAO.getAllContinents ().forEach (System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        Scanner scanner = new Scanner (System.in);
        Continent continent = continentDAO.getContinentById (giveExistingContinentId ());
        System.out.println ("Do you want to change the Id? NA for nothing");
        String answer = scanner.next ();
        if(!answer.toUpperCase(Locale.ROOT).equals("NA")){
            System.out.println("What do you want to change it to?");
            int id = scanner.nextInt();
            continent.setId(id);

        }
        System.out.println("Do you want to change the name? NA for nothing");
        answer = scanner.next();
        if(!answer.toUpperCase(Locale.ROOT).equals("NA")){
            System.out.println("What do you want to change it to?");
            String name = scanner.next();
            continent.setName(name);
        }

        continentDAO.updateContinent (continent);
        System.out.println ("Continent up");

    }

    public void deleteContinent() throws SQLException {
        Scanner scanner = new Scanner (System.in);
        continentDAO.getAllContinents ().forEach (System.out::println);
        System.out.println("Give id of Continent you want to delete:");
        int continentId = giveExistingContinentId ();
        System.out.println("Are you sure you want to delete this Continent:");
        System.out.println (continentDAO.getContinentById (continentId));
        System.out.println ("Y/N");
        String answer = scanner.next ();
        if (answer.toUpperCase(Locale.ROOT).equals("Y")){
            continentDAO.deleteContinent (continentDAO.getContinentById (continentId));
            System.out.println("Continent has been deleted");
        }else System.out.println("Continent has not been deleted");
    }

    private int giveExistingContinentId() throws SQLException {
        boolean exist = false;
        int currentId = 0;
        while (!exist){
            currentId = makeACorrectId ();
            if (continentDAO.getContinentById (currentId)!=null) exist = true;
            else System.out.println ("Continent doesn't exist");
        }
        return currentId;
    }

    private int makeACorrectId(){
        Scanner scanner = new Scanner (System.in);
        boolean rightInput;
        int id= 0;
        do {
            System.out.println ("Insert Id:");
            try {
                id = Integer.parseInt(scanner.next ());
                scanner.nextLine ();
                rightInput = true;
            } catch (NumberFormatException e){
                rightInput = false;
                System.out.println ("Id is not correct!");
            }

        }while (!rightInput);
        return id;
    }


}
