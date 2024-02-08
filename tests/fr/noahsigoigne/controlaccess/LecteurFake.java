package fr.noahsigoigne.controlaccess;

public class LecteurFake implements LecteurInterface {

    private String nom;
    private PorteInterface[] portes;
    private boolean aDetecteBadge;
    private boolean aDetecteBadgeCorrect;

    public LecteurFake(PorteInterface... portes) {
        this.portes = portes;
    }

    public LecteurFake(String nomDuLecteur, PorteInterface... portes) {
        this.nom = nomDuLecteur;
        this.portes = portes;
    }
    public String simulerDetectionBadge(BadgeInterface badge){
        aDetecteBadge = true;
        if(!badge.estBloque()) {
            this.aDetecteBadgeCorrect = true;
            return badge.getNom() + " " + this.nom + " OK";
        }
        return badge.getNom() + " " + this.nom + " KO";
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

}
