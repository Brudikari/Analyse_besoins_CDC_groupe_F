import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import fr.noahsigoigne.controlaccess.LecteurFake;
import fr.noahsigoigne.controlaccess.PorteSpy;
import fr.noahsigoigne.controlaccess.Badge;
import fr.noahsigoigne.controlaccess.MoteurOuverture;
import fr.noahsigoigne.controlaccess.LogSpy;

public class ControlAccesTest {

    @Test
    public void casNominal() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteur = new LecteurFake(porteSpy);
        Badge badge = new Badge();
        MoteurOuverture moteurOuverture = new MoteurOuverture();

        //QUAND un badge est passé devant le lecteur
        lecteur.simulerDetectionBadge(badge);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS la porte est déverrouillée
        assertTrue(porteSpy.verifierOuvertureDemandee());
    }

    @Test
    public void CasSansInterrogation() {
        //ETANT DONNE un lecteur relié à une porte
        LecteurFake lecteur = new LecteurFake();
        PorteSpy porteSpy = new PorteSpy();
        Badge badge = new Badge();

        //QUAND un badge est détecté
        lecteur.simulerDetectionBadge(badge);

        //ET que ce lecteur n'est pas interrogé

        //ALORS la porte n'est pas déverrouillée
        assertFalse(porteSpy.verifierOuvertureDemandee());
    }

    @Test
    public void CasSansDetection() {
        //ETANT DONNE un lecteur relié à une porte
        LecteurFake lecteur = new LecteurFake();
        PorteSpy porteSpy = new PorteSpy();
        MoteurOuverture moteurOuverture = new MoteurOuverture();

        //QUAND aucun badge n'est détecté

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS la porte n'est pas déverrouillée
        assertFalse(porteSpy.verifierOuvertureDemandee());
    }

    @Test
    public void casPlusieursPortes() {
        //ETANT DONNE un lecteur relié à deux porte
        PorteSpy porteSpy1 = new PorteSpy();
        PorteSpy porteSpy2 = new PorteSpy();
        LecteurFake lecteur = new LecteurFake(porteSpy1, porteSpy2);
        Badge badge = new Badge();
        MoteurOuverture moteurOuverture = new MoteurOuverture();

        //QUAND un badge est détecté
        lecteur.simulerDetectionBadge(badge);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS les portes sont déverrouillées
        assertTrue(porteSpy1.verifierOuvertureDemandee());
        assertTrue(porteSpy2.verifierOuvertureDemandee());
    }

    @Test
    public void CasPlusieursLecteurs() {
        //ETANT DONNE plusieurs lecteurs reliés à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteur1 = new LecteurFake(porteSpy);
        LecteurFake lecteur2 = new LecteurFake(porteSpy);
        Badge badge = new Badge();
        MoteurOuverture moteurOuverture = new MoteurOuverture();

        //QUAND un badge est passé devant le deuxième lecteur
        lecteur2.simulerDetectionBadge(badge);
        //QUAND un badge est détecté
        lecteur2.simulerDetectionBadge(badge);

        // ET que ces lecteurs sont interrogés
        moteurOuverture.interrogerLecteur(lecteur1, lecteur2);

        // ALORS la porte est deverrouillée
        assertTrue(porteSpy.verifierOuvertureDemandee());
    }

    @Test
    public void casPlusieursLecteursPlusieursPortes() {
        // ETANT DONNE plusieurs lecteurs reliés chacun à leur porte
        PorteSpy porteSpy1 = new PorteSpy();
        PorteSpy porteSpy2 = new PorteSpy();
        LecteurFake lecteur1 = new LecteurFake(porteSpy1);
        Badge badge = new Badge();
        MoteurOuverture moteurOuverture = new MoteurOuverture();

        // QUAND un badge est passé devant le deuxième lecteur
        lecteur1.simulerDetectionBadge(badge);

        // ET que ces lecteurs sont interrogés
        moteurOuverture.interrogerLecteur(lecteur1);

        //ALORS seule la 2e porte est déverrouillée
        assertTrue(porteSpy1.verifierOuvertureDemandee());
        assertFalse(porteSpy2.verifierOuvertureDemandee());
    }

    @Test
    public void casBadgeBloque() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteur = new LecteurFake(porteSpy);
        Badge badge = new Badge();
        MoteurOuverture moteurOuverture = new MoteurOuverture();

        //QUAND un badge est bloqué
        badge.bloquer();

        //ET ce badge est détecté
        lecteur.simulerDetectionBadge(badge);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS la porte n'est pas déverrouillée
        assertFalse(porteSpy.verifierOuvertureDemandee());
    }

    @Test
    public void casBadgeBloquePuisDebloque() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteur = new LecteurFake(porteSpy);
        Badge badge = new Badge();
        MoteurOuverture moteurOuverture = new MoteurOuverture();

        //QUAND un badge est bloqué
        badge.bloquer();

        //QUAND un badge est ddébloqué
        badge.debloquer();

        //ET ce badge est détecté
        lecteur.simulerDetectionBadge(badge);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS la porte est déverrouillée
        assertTrue(porteSpy.verifierOuvertureDemandee());
    }

    @Test
    public void casLogsKO() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteur = new LecteurFake("lecteur_01", porteSpy);
        Badge badge = new Badge("badge_01");
        LogSpy logSpy = new LogSpy();
        MoteurOuverture moteurOuverture = new MoteurOuverture(logSpy);

        //QUAND un badge est bloqué
        badge.bloquer();

        //ET ce badge est détecté puis logger
        lecteur.simulerDetectionBadge(badge);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS le log à récupéré les bonnes informations
        String Prevision = logSpy.getTime() + " : " + badge.getNom() + " sur " + lecteur.getNom() + " - KO\n";
        assertEquals(logSpy.getStockage(), Prevision);
    }
    @Test
    public void casLogsOK() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteur = new LecteurFake("lecteur_01", porteSpy);
        Badge badge = new Badge("badge_01");
        LogSpy logSpy = new LogSpy();
        MoteurOuverture moteurOuverture = new MoteurOuverture(logSpy);

        //QUAND un badge est détecté
        lecteur.simulerDetectionBadge(badge);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS le log à récupéré les bonnes informations
        String Prevision = logSpy.getTime() + " : " + badge.getNom() + " sur " + lecteur.getNom() + " - OK\n";
        assertEquals(logSpy.getStockage(), Prevision);
    }
    @Test
    public void casPlusieursLogs() {
        //ETANT DONNE deux lecteurs relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteur01 = new LecteurFake("lecteur_01", porteSpy);
        LecteurFake lecteur02 = new LecteurFake("lecteur_02", porteSpy);
        Badge badge01 = new Badge("badge_01");
        Badge badge02 = new Badge("badge_02");
        LogSpy logSpy = new LogSpy();
        MoteurOuverture moteurOuverture = new MoteurOuverture(logSpy);

        //QUAND un premier badge est détecté par un premier lecteur
        lecteur01.simulerDetectionBadge(badge01);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur01);

        //ET un nouveau badge est détecté par un second lecteur
        lecteur02.simulerDetectionBadge(badge02);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur02);

        //ALORS le log à récupéré les infos des 2 tentatives d'entrée
        String Prevision = logSpy.getTime() + " : " + badge01.getNom() + " sur " + lecteur01.getNom() + " - OK\n"
                + logSpy.getTime() + " : " + badge02.getNom() + " sur " + lecteur02.getNom() + " - OK\n";
        assertEquals(logSpy.getStockage(), Prevision);
    }
    //Log
    //Output dans un string
    //Quand ouverture valide, mettre dans un journal que ce lecteur a vu passer ce badge
    //{Horodatage} : {badge} sur {lecteur} - OK / KO

    // 2024-02-08_09-59-10 : badge_01 sur lecteur_01 - OK \n
    //Tests -> OK, KO

    //TODO Tester si aucune infos dans le log

    @Test
    public void casAucuneInfoDansleLog() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteur = new LecteurFake("lecteur_01", porteSpy);
        LogSpy logSpy = new LogSpy();
        MoteurOuverture moteurOuverture = new MoteurOuverture();

        //QUAND le lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS le log ne récupère aucune info
        String Prevision = "";
        assertEquals(logSpy.getStockage(), Prevision);
    }

    //TODO Tester si plusieurs infos dans le log
}
