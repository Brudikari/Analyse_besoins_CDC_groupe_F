package fr.noahsigoigne.controlaccess;

public class MoteurOuverture {
    LogInterface log;

    public MoteurOuverture() {
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
                if(log!=null) //TODO nullLogInterface -> NullObject
                    log.getLogInfos(lecteur.getBadge().getNom() + " " + lecteur.getNom() + " OK");
            } else {
                if(log!=null)
                    log.getLogInfos(lecteur.getBadge().getNom() + " " + lecteur.getNom() + " KO");
            }
        }
    }
}
