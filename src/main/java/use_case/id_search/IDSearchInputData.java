package main.java.use_case.id_search;

import java.util.Objects;

public class IDSearchInputData {
    private String query = "";

    public IDSearchInputData(String query){
        this.query = query;
    }

    public boolean isEmpty(){
        return query.isEmpty();
    }

    public String getQuery(){
        return query;
    }
}
