package fr.noahsigoigne.controlaccess;

public class MoteurOuverture {


    public MoteurOuverture() {
    }
    public void interrogerLecteur(LecteurInterface... lecteurs) {
        for(LecteurInterface lecteur : lecteurs ) {
            if (lecteur.aDetecteBadge() && lecteur.aDetecteBadgeCorrect()) {
                for (PorteInterface porte : lecteur.getPortes()) {
                    porte.ouvrir();
                }
            }
        }
    }
}
