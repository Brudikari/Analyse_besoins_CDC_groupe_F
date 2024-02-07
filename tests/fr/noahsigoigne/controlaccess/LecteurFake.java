package fr.noahsigoigne.controlaccess;

public class LecteurFake implements LecteurInterface {

    private boolean aDetecteBadge;
    public void simulerDetectionBadge(){
        this.aDetecteBadge = true;
    }

    public boolean aDetecteBadge() {
        return aDetecteBadge;
    }
}
