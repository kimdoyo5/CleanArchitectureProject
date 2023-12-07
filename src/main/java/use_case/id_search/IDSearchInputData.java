package main.java.use_case.id_search;

/**
 * Input data to be created by the ID search controller and passed
 * in to the interactor
 */
public class IDSearchInputData {
    private String query = "";

    /**
     * Constructs ID search input data given the query the user types
     * @param query a String, typically the name of a player
     */
    public IDSearchInputData(String query){
        this.query = query;
    }

    /**
     * Determine if the query was empty
     * @return true if the query is empty, false otherwise
     */
    public boolean isEmpty(){
        return query.isEmpty();
    }

    /**
     * Give the query that is stored in this object
     * @return the query
     */
    public String getQuery(){
        return query;
    }
}
