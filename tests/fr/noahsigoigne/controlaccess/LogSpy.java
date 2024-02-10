package fr.noahsigoigne.controlaccess;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
public class LogSpy implements LogInterface {

    private String stockage;
    private int logAcceptationLevel;

    public LogSpy(){
        stockage = "";
    }

    public LogSpy(int logAcceptationLevel){
        stockage = "";
        this.logAcceptationLevel = logAcceptationLevel;
    }

    public String getTime() {
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime datetime = ZonedDateTime.now(zid);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu.MM.dd.HH:mm:ss");
        return datetime.format(formatter);
    }

    public void log(int level, String infos) {
        String logLevel = switch (level) {
            case LogInterface.INFO -> "[INFO]";
            case LogInterface.WARN -> "[WARN]";
            case LogInterface.ERROR -> "[ERROR]";
            default -> "";
        };
        if(level >= logAcceptationLevel)
            stockage += logLevel + " " + getTime() + " : " + infos + "\n";
    }

    public String getStockage() {
        return stockage;
    }
}