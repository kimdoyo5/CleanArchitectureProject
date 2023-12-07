package main.java.data_access;

import org.junit.Test;

import java.io.File;

public class DatabaseDataAccessObjectTest {
    @org.junit.Test
    public void createDatabase(){
        File database = new File("database.csv");
        assert (database.delete());
        DatabaseDataAccessObject databaseDataAccessObject = new DatabaseDataAccessObject();
        databaseDataAccessObject.create(2023, 2023);
        File data = new File("database.csv");
        assert (data.length() > 0 );
    }
}
