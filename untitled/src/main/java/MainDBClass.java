import dataBaseConnectors.CountryDAO;
import model.Country;
import service.CityService;
import service.ContinentService;
import service.CountryService;

import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainDBClass {

        private static int choiceOne= 9;
        private static int choiceTwo= 9;
        private static boolean continueThis= true;

        public static void main(String[] args) {

            try {

                while (continueThis){
                    getChoice();
                    choices();
                }



            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public static void getChoice(){
            Scanner scanner = new Scanner(System.in);

            while (choiceOne==9) {
                System.out.println("What do you want to look at? \n1: Countries \n2: Continents\n3: City\n0: End");
                choiceOne = scanner.nextInt();
                if(choiceOne==0)break;
                if(choiceOne<1||choiceOne>3){
                    choiceOne = 9;
                    System.out.println("Invalid choice.");
                }else {
                    while (choiceTwo==9) {
                        System.out.println("What do you want to look at? \n1: See All \n2: See One \n3: Add One \n4: Edit One \n5: Delete One\n0: End");
                        choiceTwo = scanner.nextInt();
                        if (choiceTwo==0)break;
                        if(choiceTwo<1||choiceTwo>5){
                            choiceTwo = 9;
                            System.out.println("Invalid choice.");
                        }
                    }
                }
            }

        }


        private static void choices() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            CountryService countryService = new CountryService();
            ContinentService continentService = new ContinentService ();
            CityService cityService = new CityService ();
            if (choiceTwo!=0)
                if (choiceOne==1){

                    switch (choiceTwo){
                        case 1:countryService.showAllCountries();break;//see All Countries
                        case 2:countryService.showCountryByIdWithoutCheck();break;//see One Country By Id
                        case 3:countryService.addCountryWithoutCheck();break;//add One new Country
                        case 4:countryService.updateCountryWithoutCheck();break;//edit One Country
                        case 5:countryService.deleteACountry();break;//delete One Country
                    }
                    System.out.println("We did a country thing!");


                } else if (choiceOne==2) {
                    switch (choiceTwo) {
                        case 1:
                            continentService.showAllContinents ();
                            break;//TODO see All Continents
                        case 2:
                            continentService.showContinentById ();
                            break;//TODO see One Continent By Id
                        case 3:
                            continentService.addContinent ();
                            break;//TODO add One new Continent
                        case 4:
                            continentService.updateContinent ();
                            break;//TODO edit One Continent
                        case 5:
                            continentService.deleteContinent ();
                            break;//TODO delete One Continent
                    }
                    System.out.println ("We did a continent thing!");

                }else if (choiceOne==3){
                        switch (choiceTwo) {
                            case 1:
                                cityService.showAllCity ();
                                break;//TODO see All City
                            case 2:
                                cityService.showCityById ();
                                break;//TODO see One City By Id
                            case 3:
                                cityService.addCity ();
                                break;//TODO add One new City
                            case 4:
                                cityService.updateCity ();
                                break;//TODO edit One City
                            case 5:
                                cityService.deleteACity ();
                                break;//TODO delete One City
                        }
                        System.out.println("We did a continent thing!");

                }
            choiceOne =9;
            choiceTwo =9;
            boolean goodAnswer;
            do {
                System.out.println("Do you want to Try again? Y/N");
                String answer = scanner.next();
                if (answer.toUpperCase(Locale.ROOT).equals("N")){
                    System.out.println("Bye!");
                    continueThis = false;
                    break;
                }
                if (!answer.toUpperCase(Locale.ROOT).equals("Y")) {
                    goodAnswer = false;
                    System.out.println(answer+ " is not a good answer.");
                }
                else goodAnswer = true;
            }while (!goodAnswer);



        }



        /*try {

//            Connection connection = DriverManager.getConnection("jdbc:mysql://moktok.intecbrussel.org:33062/bilalabz",
//                    "bilalabz","bilalabz2021");
//
//
//
//            Statement statement = connection.createStatement();
//
//            //TODO: voeg onderstaande tabel toe aan je database in Squirrel
//
//
//            //CREATE TABLE Animal(
//            //         id INT AUTO_INCREMENT,
//            //         name VARCHAR(255),
//            //         country VARCHAR(255),
//            //         foodId INT,
//            //         countryId INT,
//            //         PRIMARY KEY (id));
//
//
//
//             String create = "CREATE TABLE Animal(\n" +
//                    "                    id INT AUTO_INCREMENT,\n" +
//                    "                    name VARCHAR(255),\n" +
//                    "                    country VARCHAR(255),\n" +
//                    "                    foodId INT,\n" +
//                    "                    countryId INT,\n" +
//                    "                    PRIMARY KEY (id));";
//
//
//
//            statement.executeUpdate(create);
//
//
//
//            String select = "SELECT * FROM Animal;";
//
//            //String insert = "INSERT INTO Persons (ID, LastName, FirstName, Age) VALUES (7, 'Mat', 'Kat', 25);";
//
//            String insert = "INSERT INTO Animal (id , name , country , foodId , countryId) \n" +
//                    "            VALUES (1 , 'zebra' , 'Kenya' , null , 1), (2 , 'lion' , 'Kenya' , 1 , 1),\n" +
//                    "                    (3 , 'leopard' , 'Kenya' , 1 , 1),\n" +
//                    "                    (4 , 'hyena' , 'Kenya' , 1 , 1),\n" +
//                    "                    (5 , 'fox' , 'Belgium' , 7 , null),\n" +
//                    "                    (6 , 'walibi' , 'Australia' , null , null),\n" +
//                    "                    (7 , 'chicken' , 'Belgium' , null , null),\n" +
//                    "                    (8 , 'dingo' , 'Australia' , 6 , null),\n" +
//                    "                    (9 , 'cat' , null , 7 , null),\n" +
//                    "                    (10 , 'polar bear' , 'North Pole' , 5 , null);\n" ;
//
//
//            //String update =  "UPDATE Persons SET LastName = 'Tanriverdi' WHERE ID =1;";
//
////            String delete = "DELETE FROM Animal WHERE ID = 11;";
//            statement.executeUpdate(insert);
//
//
//
//            ResultSet resultSet = statement.executeQuery(select);
//
//            while (resultSet.next()){
//                System.out.print(resultSet.getInt("Id")+"--");
//                System.out.print(resultSet.getString("name")+" ");
//                System.out.println(resultSet.getString("country"));
//            }

            Scanner scanner = new Scanner(System.in);
//            System.out.println("Insert a Country you want to add:");
//            String countryName = scanner.next();
//            System.out.println("Insert a continent id:");
//            int continentId = scanner.nextInt();
//            Country countryIJustMade = new Country(countryName, continentId);

//            System.out.println("Insert a Country you want to update:");
//            String countryName = scanner.next();
//            System.out.println("Insert a continentId you want to update:");
//            int continentId = scanner.nextInt();
//            System.out.println ("Enter the id of the country you want to update:");
//            int id = scanner.nextInt ();
//            Country countryUpdate = new Country (countryName, continentId);

            System.out.println ("Insert the id of the country you want to delete:");
            int id = scanner.nextInt ();











            CountryDAO countryDAO = new CountryDAO();
//            countryDAO.addCountry(countryIJustMade);
//            countryDAO.updateCountry (countryUpdate,id);
            countryDAO.deleteCountryWithId (id);

            List<Country> countryList = countryDAO.getAllCountries();



            for (Country country: countryList){
                System.out.println(country);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

         */
}

