package main.java.use_case.id_search;


import java.util.List;
import java.util.Map;
import main.java.use_case.id_search.IDSearchInputData;
import main.java.use_case.id_search.IDSearchDataAccessInterface;
import main.java.use_case.id_search.IDSearchOutputBoundary;
import main.java.use_case.id_search.IDSearchOutputBoundary;

/**
 * the interactor for ID search. Take ID search input data and passes the
 * appropriate data to the presenter. This includes the ability to pass errors
 * to the presenter if the player cannot be found or if there is an error thrown
 * in the process of searching.
 */
public class IDSearchInteractor implements IDSearchInputBoundary{
    IDSearchOutputBoundary idSearchOutputBoundary;
    IDSearchDataAccessInterface idSearchDataAccessInterface;

    /**
     * Constructor for ID search interactor
     * @param output the ID search output boundary, in practice this is
     *               the presenter
     * @param da the ID search data access interface
     */
    public IDSearchInteractor(IDSearchOutputBoundary output, IDSearchDataAccessInterface da){
        idSearchOutputBoundary = output;
        idSearchDataAccessInterface = da;
    }

    /**
     * Takes ID search input data containing the name of a player and tries to
     * use the data access object to find the possible IDs of the player. Calls
     * the presenter by giving it either the search result or an appropriate
     * error to show.
     * @param idSearchInputData the information about the player to search for
     */
    public void execute(IDSearchInputData idSearchInputData){
        if (!(idSearchInputData.isEmpty())){
            if (idSearchDataAccessInterface.isPlayer(idSearchInputData.getQuery())) {
                try {
                    Map<String, Integer> searchResult = idSearchDataAccessInterface.getID(idSearchInputData.getQuery());
                    IDSearchOutputData result = new IDSearchOutputData(searchResult);
                    idSearchOutputBoundary.prepareSuccessView(result);
                }catch (RuntimeException e){
                    idSearchOutputBoundary.prepareFailView("Search Error");
                }
            }else{
                idSearchOutputBoundary.prepareFailView("No players match this name");
            }
        }
    }

    /**
     * If this is called, calls the presenter to switch the view to main menu
     */
    public void execute(){
        idSearchOutputBoundary.back();
    }

}
