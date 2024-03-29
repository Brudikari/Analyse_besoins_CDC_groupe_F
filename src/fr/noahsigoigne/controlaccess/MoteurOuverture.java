package fr.noahsigoigne.controlaccess;

public class MoteurOuverture {
    private final LogInterface log;

    private String nom;

    public MoteurOuverture() {
        this.log = new NullLog();
    }

    public MoteurOuverture(LogInterface log) {
        this.log = log;
    }

    public MoteurOuverture(String nom, LogInterface log) {
        this.nom = nom;
        this.log = log;
        log.log(log.INFO, "moteur " + nom + " démarré");
    }

    public String getNom() {
        return nom;
    }

    public void interrogerLecteur(LecteurInterface... lecteurs) throws LecteurException {
        if(lecteurs.length == 0) {
            log.log(log.ERROR, "moteur " + nom + " : Lecteur manquant");
            throw new LecteurException("Lecteur manquant");
        }
        for(LecteurInterface lecteur : lecteurs ) {
            if (lecteur.aDetecteBadgeCorrect()) {
                for (PorteInterface porte : lecteur.getPortes()) {
                    porte.ouvrir();
                }
                log.log(log.INFO, lecteur.getBadge().getNom() + " sur " + lecteur.getNom() + " - OK");
            } else {
                log.log(log.WARN, lecteur.getBadge().getNom() + " sur " + lecteur.getNom() + " - KO");
            }
        }
    }
}
