package fr.noahsigoigne.controlaccess;

public interface LogInterface {

    int INFO = 1;
    int WARN = 2;
    int ERROR = 3;

    String getTime();
    void log(int level, String infos);
    String getStockage();
}
