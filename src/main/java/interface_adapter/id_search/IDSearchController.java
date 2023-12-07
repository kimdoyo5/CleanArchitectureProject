package main.java.interface_adapter.id_search;

import main.java.use_case.id_search.IDSearchInputBoundary;
import main.java.use_case.id_search.IDSearchInputData;
import main.java.use_case.id_search.IDSearchInputBoundary;

import java.io.IOException;

/**
 * ID Search controller, turns the user input into ID search input data, and passes
 * this data to the interactor.
 */
public class IDSearchController {
    IDSearchInputBoundary idSearchInteractor;

    /**
     * Initializes the class
     * @param idSearchInteractor the input boundary, in practice will be the
     *                           interator
     */
    public IDSearchController(IDSearchInputBoundary idSearchInteractor){
        this.idSearchInteractor = idSearchInteractor;
    }

    /**
     * Behaviour for when "search" is clicked, creates IDsearch input data and
     * calls the interactor on it
     * @param query what the user types
     */
    public void execute(String query) throws IOException{
        IDSearchInputData data = new IDSearchInputData(query);
        idSearchInteractor.execute(data);
    }

    /**
     * Behaviour for when "back" is clicked, calls the interactor
     * without a parameter
     */
    public void execute(){
        idSearchInteractor.execute();
    }

}
