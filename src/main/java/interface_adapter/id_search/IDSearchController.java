package main.java.interface_adapter.id_search;

import main.java.use_case.id_search.IDSearchInputBoundary;
import main.java.use_case.id_search.IDSearchInputData;
import main.java.use_case.id_search.IDSearchInputBoundary;

import java.io.IOException;

public class IDSearchController {
    IDSearchInputBoundary idSearchInteractor;
    public IDSearchController(IDSearchInputBoundary idSearchInteractor){
        this.idSearchInteractor = idSearchInteractor;
    }

    public void execute(String query) throws IOException {
        IDSearchInputData data = new IDSearchInputData(query);
        idSearchInteractor.execute(data);
    }

    public void execute(){
        idSearchInteractor.execute();
    }

}
