package main.java.data_access;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IDFileDataAccessObjectTest {
    @Test
    void simpleConstructorTest(){
        // Test the constructor with the limited test database
        try{
            IDFileDataAccessObject test = new IDFileDataAccessObject(
                    "test/main/java/data_access/DatabaseTest.csv");
        }catch(RuntimeException e){
            fail("Construction threw runtime exception");
        }
        // Pass test if construction didn't throw error
        assertTrue(true);
    }
    @Test
    void failSimpleConstructorTest(){
        // Test that the constructor will throw an exception if given a bad file
        try{
            IDFileDataAccessObject test = new IDFileDataAccessObject(
                    "hello");
            fail("No exception thrown");
        }catch(RuntimeException e){
            assertTrue(true);
        }
    }
    @Test
    void constructorTest(){
        // Test that the constructor creates the correct values
        try{
            IDFileDataAccessObject test = new IDFileDataAccessObject(
                    "test/main/java/data_access/DatabaseTest.csv");
            assertTrue(test.isPlayer("Ryne Nelson"));
            assertTrue(test.isPlayer(694297));
            assertFalse(test.isPlayer(1));
            assertFalse(test.isPlayer("your mother"));
        }catch(RuntimeException e){
            fail("Construction threw runtime exception");
        }
    }
    @Test
    void getIDTest(){
        // Test the getID() function
        try{
            IDFileDataAccessObject test = new IDFileDataAccessObject(
                    "test/main/java/data_access/DatabaseTest.csv");
            Map<String, Integer> result = test.getID("Kevin");
            assertEquals(result.keySet().size(), 1);
            Map.Entry<String,Integer> entry = result.entrySet().iterator().next();
            String key = entry.getKey();
            int value = entry.getValue();
            assertTrue(key.contains("Kevin"));
            assertEquals(value, 605196);

        }catch(RuntimeException e){
            fail("Construction threw runtime exception");
        }
    }

    @Test
    void getMultipleIDTest(){
        // Test the getID() function with multiple matching players
        try{
            IDFileDataAccessObject test = new IDFileDataAccessObject(
                    "test/main/java/data_access/DatabaseTest.csv");
            Map<String, Integer> result = test.getID("Tyler");
            assertEquals(result.keySet().size(), 2);
            Map.Entry<String,Integer> entry = result.entrySet().iterator().next();
            String key = entry.getKey();
            int value = entry.getValue();
            assertTrue(key.contains("Tyler"));
            assertEquals(value, 461325);

        }catch(RuntimeException e){
            fail("Construction threw runtime exception");
        }
    }
}