package main.java.use_case.id_search;

import java.util.List;
import java.util.Map;

public class IDSearchInteractor {
    IDSearchOutputBoundary idSearchOutputBoundary;
    IDSearchInputBoundary idSearchInputBoundary;
    IDSearchDataAccessInterface idSearchDataAccessInterface;

    public IDSearchInteractor(IDSearchInputBoundary input, IDSearchOutputBoundary output, IDSearchDataAccessInterface da){
        idSearchInputBoundary = input;
        idSearchOutputBoundary = output;
        idSearchDataAccessInterface = da;
    }

    public void execute(IDSearchInputData idSearchInputData){
        if (!(idSearchInputData.isEmpty())){
            if (idSearchDataAccessInterface.isPlayer(idSearchInputData.getQuery())) {
                try {
                    Map<String, Integer> searchResult = idSearchDataAccessInterface.getID(idSearchInputData.getQuery());
                    if (searchResult.isEmpty()){
                        idSearchOutputBoundary.prepareFailView("No players match this name");
                    }
                    else{
                        idSearchOutputBoundary.prepareSuccessView(searchResult);
                    }
                }catch (RuntimeException e){
                    idSearchOutputBoundary.prepareFailView("Search Error");
                }
            }else{
                idSearchOutputBoundary.prepareFailView("Please enter a name");
            }
        }
    }

}
