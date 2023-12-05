package main.java.use_case.id_search;


import java.util.List;
import java.util.Map;
import main.java.use_case.id_search.IDSearchInputData;
import main.java.use_case.id_search.IDSearchDataAccessInterface;
import main.java.use_case.id_search.IDSearchOutputBoundary;
import main.java.use_case.id_search.IDSearchOutputBoundary;

public class IDSearchInteractor implements IDSearchInputBoundary{
    IDSearchOutputBoundary idSearchOutputBoundary;
    IDSearchDataAccessInterface idSearchDataAccessInterface;

    public IDSearchInteractor(IDSearchOutputBoundary output, IDSearchDataAccessInterface da){
        idSearchOutputBoundary = output;
        idSearchDataAccessInterface = da;
    }

    public void execute(IDSearchInputData idSearchInputData) throws RuntimeException{
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

    public void execute(){
        idSearchOutputBoundary.back();
    }

}
