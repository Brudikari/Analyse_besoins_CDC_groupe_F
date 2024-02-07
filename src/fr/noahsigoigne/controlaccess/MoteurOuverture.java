package fr.noahsigoigne.controlaccess;

public class MoteurOuverture {

    private final PorteInterface[] portes;
    public MoteurOuverture(PorteInterface... portes) {
        this.portes = portes;
    }
    public void interrogerLecteur(LecteurInterface lecteur) {
        if(lecteur.aDetecteBadge()) {
            for(PorteInterface porte : portes ) {
                porte.ouvrir();
            }
        }
    }

}
