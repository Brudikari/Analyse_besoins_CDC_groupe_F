package fr.noahsigoigne.controlaccess;

public class MoteurOuverture {

    private final PorteInterface[] portes;

    public MoteurOuverture(PorteInterface... portes) {
        this.portes = portes;
    }
    public void interrogerLecteur(LecteurInterface... lecteurs) {
        for(LecteurInterface lecteur : lecteurs ) {
            if (lecteur.aDetecteBadge()) {
                for (PorteInterface porte : lecteur.getPortes()) {
                    porte.ouvrir();
                }
            }
        }
    }

}
