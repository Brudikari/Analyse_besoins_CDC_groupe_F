package fr.noahsigoigne.controlaccess;

public class LecteurFake implements LecteurInterface {

    private String nom;
    private PorteInterface[] portes;
    private boolean aDetecteBadge;
    private boolean aDetecteBadgeCorrect;
    private BadgeInterface badge;

    public LecteurFake(PorteInterface... portes) {
        this.portes = portes;
        this.badge = new NullBadge();
    }

    public LecteurFake(String nomDuLecteur, PorteInterface... portes) {
        this.nom = nomDuLecteur;
        this.portes = portes;
        this.badge = new NullBadge();
    }
    public void simulerDetectionBadge(BadgeInterface badge){
        aDetecteBadge = true;
        this.badge = badge;
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
    public String getNom() {
        return nom;
    }
    public BadgeInterface getBadge() {
        return badge;
    }
}
