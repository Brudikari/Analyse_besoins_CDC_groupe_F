package fr.noahsigoigne.controlaccess;

public class LecteurFake implements LecteurInterface {

    private PorteInterface[] portes;
    private boolean aDetecteBadge;

    public LecteurFake(PorteInterface... portes) {
        this.portes = portes;
    }

    public void simulerDetectionBadge(){
        this.aDetecteBadge = true;
    }

    public boolean aDetecteBadge() {
        return aDetecteBadge;
    }

    public PorteInterface[] getPortes() {
        return portes;
    }
}
