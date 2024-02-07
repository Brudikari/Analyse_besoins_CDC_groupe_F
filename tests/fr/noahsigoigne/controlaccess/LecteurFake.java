package fr.noahsigoigne.controlaccess;

public class LecteurFake implements LecteurInterface {

    private PorteInterface[] portes;
    private boolean aDetecteBadge;
    private boolean aDetecteBadgeCorrect;

    public LecteurFake(PorteInterface... portes) {
        this.portes = portes;
    }

    public void simulerDetectionBadge(BadgeInterface badge){
        aDetecteBadge = true;
        if(!badge.estBloque()) {
            this.aDetecteBadgeCorrect = true;
        }

    }

    public boolean aDetecteBadge() {
        return aDetecteBadge;
    }

    public boolean aDetecteBadgeCorrect() {
        return aDetecteBadgeCorrect;
    }

    public PorteInterface[] getPortes() {
        return portes;
    }
}
