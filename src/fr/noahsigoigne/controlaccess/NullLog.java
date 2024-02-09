package fr.noahsigoigne.controlaccess;

public class NullLog implements LogInterface{
    @Override
    public String getTime() {
        return "";
    }

    @Override
    public void getLogInfos(String infos) {

    }

    @Override
    public String getStockage() {
        return "";
    }
}
