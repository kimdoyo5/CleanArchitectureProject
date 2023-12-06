package main.java.use_case.id_search;

import main.java.data_access.InMemoryIDFileDataAccessObject;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class IDSearchInteractorTest {
    @Test
    void basicSuccessTest() {
        // Test for searching a valid player and returning one result
        IDSearchInputData inputData = new IDSearchInputData("Tom");
        Map<String, Integer> players = new HashMap<>();
        players.put("Tom Thompson", 85779);
        IDSearchDataAccessInterface database = new InMemoryIDFileDataAccessObject(players);

        IDSearchOutputBoundary idSearchPresenter = new IDSearchOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                // We do not expect this to fail
                fail("Failed with error " + error);
            }

            @Override
            public void prepareSuccessView(IDSearchOutputData results) {
                // Check that only one player is returned (only one should match)
                assertEquals(results.getPlayers().keySet().size(), 1);
                // Check that the full player name is returned
                Object name = results.getPlayers().keySet().toArray()[0];
                assertEquals(name, "Tom Thompson");
                // Check that the player ID is correct
                assertEquals((int)results.getPlayers().get(name), 85779);
            }

            @Override
            public void back() {
                // This should not be called
                fail("back() was called");
            }
        };

        IDSearchInputBoundary interactor = new IDSearchInteractor(idSearchPresenter, database);
        interactor.execute(inputData);
    }

    @Test
    void basicSearchFailTest() {
        // Test for searching an invalid player
        IDSearchInputData inputData = new IDSearchInputData("Jim");
        Map<String, Integer> players = new HashMap<>();
        players.put("Tom Thompson", 85779);
        IDSearchDataAccessInterface database = new InMemoryIDFileDataAccessObject(players);

        IDSearchOutputBoundary idSearchPresenter = new IDSearchOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                // Test to ensure the correct error is passed through
                assertEquals("No players match this name", error);

            }

            @Override
            public void prepareSuccessView(IDSearchOutputData results) {
                // We do not expect this to be called
                fail("Success view was called with the output data:" + results.getPlayers().keySet().toArray()[0]);
            }

            @Override
            public void back() {
                // This should not be called
                fail("back() was called");
            }
        };

        IDSearchInputBoundary interactor = new IDSearchInteractor(idSearchPresenter, database);
        interactor.execute(inputData);
    }

    @Test
    void emptySearchFailTest() {
        // Test for searching without a query
        IDSearchInputData inputData = new IDSearchInputData("");
        Map<String, Integer> players = new HashMap<>();
        players.put("Tom Thompson", 85779);
        IDSearchDataAccessInterface database = new InMemoryIDFileDataAccessObject(players);

        IDSearchOutputBoundary idSearchPresenter = new IDSearchOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                // Test to ensure the correct error is passed through
                assertEquals("No players match this name", error);

            }

            @Override
            public void prepareSuccessView(IDSearchOutputData results) {
                // We do not expect this to be called
                fail("Success view was called with the output data:" + results.getPlayers().keySet().toArray()[0]);
            }

            @Override
            public void back() {
                // This should not be called
                fail("back() was called");
            }
        };

        IDSearchInputBoundary interactor = new IDSearchInteractor(idSearchPresenter, database);
        interactor.execute(inputData);
    }

    @Test
    void searchExceptionTest() {
        // Test for correct behaviour when the data access object throws an error
        IDSearchInputData inputData = new IDSearchInputData("Tom");
        Map<String, Integer> players = new HashMap<>();
        players.put("Tom Thompson", 85779);
        IDSearchDataAccessInterface database = new InMemoryIDFileDataAccessObject(players, false);

        IDSearchOutputBoundary idSearchPresenter = new IDSearchOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                // Test to ensure the correct error is passed through
                assertEquals("Search Error", error);

            }

            @Override
            public void prepareSuccessView(IDSearchOutputData results) {
                // We do not expect this to be called
                fail("Success view was called with the output data:" + results.getPlayers().keySet().toArray()[0]);
            }

            @Override
            public void back() {
                // This should not be called
                fail("back() was called");
            }
        };

        IDSearchInputBoundary interactor = new IDSearchInteractor(idSearchPresenter, database);
        interactor.execute(inputData);
    }

    @Test
    void backTest() {
        // Test that back is called correctly
        Map<String, Integer> players = new HashMap<>();
        players.put("Tom Thompson", 85779);
        IDSearchDataAccessInterface database = new InMemoryIDFileDataAccessObject(players, false);

        IDSearchOutputBoundary idSearchPresenter = new IDSearchOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                // We do not expect this to be called
                fail("Fail view was called with error " + error);

            }

            @Override
            public void prepareSuccessView(IDSearchOutputData results) {
                // We do not expect this to be called
                fail("Success view was called with the output data:" + results.getPlayers().keySet().toArray()[0]);
            }

            @Override
            public void back() {
                // This should not be called
                assertTrue(true);
            }
        };

        IDSearchInputBoundary interactor = new IDSearchInteractor(idSearchPresenter, database);
        interactor.execute();
    }



}