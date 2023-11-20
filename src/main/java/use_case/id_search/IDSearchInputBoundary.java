package main.java.use_case.id_search;

public interface IDSearchInputBoundary {
    void execute(IDSearchInputData idSearchInputData) throws RuntimeException;
    void execute();
}
