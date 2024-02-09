package fr.noahsigoigne.controlaccess;

public class MoteurOuverture {
    LogInterface log;

    public MoteurOuverture() {
        this.log = new NullLog();
    }

    public MoteurOuverture(LogInterface log) {
        this.log = log;
    }

    public void interrogerLecteur(LecteurInterface... lecteurs) {
        for(LecteurInterface lecteur : lecteurs ) {
            if (lecteur.aDetecteBadgeCorrect()) {
                for (PorteInterface porte : lecteur.getPortes()) {
                    porte.ouvrir();
                }
                log.getLogInfos(lecteur.getBadge().getNom() + " " + lecteur.getNom() + " OK");
            } else {
                log.getLogInfos(lecteur.getBadge().getNom() + " " + lecteur.getNom() + " KO");
            }
        }
    }
}
