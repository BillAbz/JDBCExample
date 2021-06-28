package dataBaseConnectors;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class EMFactory {


    public static EntityManagerFactory getEMF(){
        return Persistence.createEntityManagerFactory ("bilalAbzDatabase");
    }
}
