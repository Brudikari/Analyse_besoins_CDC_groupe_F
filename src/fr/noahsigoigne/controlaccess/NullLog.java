package fr.noahsigoigne.controlaccess;

public class NullLog implements LogInterface{
    @Override
    public String getTime() {
        return "";
    }

    @Override
    public void log(int level, String infos) {

    }

    @Override
    public String getStockage() {
        return "";
    }
}
