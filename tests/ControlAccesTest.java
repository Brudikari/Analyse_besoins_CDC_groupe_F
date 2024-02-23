import fr.noahsigoigne.controlaccess.*;
import org.junit.Test;

import static org.junit.Assert.*;


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

        //ET que le lecteur est interrogé
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

        //ET que le lecteur n'est pas interrogé

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

        //ET que le lecteur est interrogé
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

        //ET que le lecteur est interrogé
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

        //QUAND un badge est passé devant chaque lecteur
        lecteur1.simulerDetectionBadge(badge);
        lecteur2.simulerDetectionBadge(badge);

        // ET que les lecteurs sont interrogés
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
        LecteurFake lecteur2 = new LecteurFake(porteSpy2);
        Badge badge = new Badge();
        MoteurOuverture moteurOuverture = new MoteurOuverture();

        // QUAND un badge est passé devant le deuxième lecteur
        lecteur2.simulerDetectionBadge(badge);

        // ET que les lecteurs sont interrogés
        moteurOuverture.interrogerLecteur(lecteur1, lecteur2);

        //ALORS seule la 2e porte est déverrouillée
        assertFalse(porteSpy1.verifierOuvertureDemandee());
        assertTrue(porteSpy2.verifierOuvertureDemandee());
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

        //ET que le lecteur est interrogé
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

        //QUAND un badge est débloqué
        badge.debloquer();

        //ET ce badge est détecté
        lecteur.simulerDetectionBadge(badge);

        //ET que le lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS la porte est déverrouillée
        assertTrue(porteSpy.verifierOuvertureDemandee());
    }

    //Log
    //Quand ouverture valide, mettre dans un journal que ce lecteur a vu passer ce badge
    //{Horodatage} : {badge} sur {lecteur} - OK / KO
    // Exemple : 2024-02-08_09-59-10 : badge_01 sur lecteur_01 - OK \n

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

        //ET le badge est détecté
        lecteur.simulerDetectionBadge(badge);

        //ET que le lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS le log a récupéré les bonnes informations
        String Prevision = "[WARN] " + logSpy.getTime() + " : " + badge.getNom() + " sur " + lecteur.getNom() + " - KO\n";
        assertEquals(Prevision, logSpy.getStockage());
    }

    @Test
    public void casLogsOK() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteur = new LecteurFake("lecteur_01", porteSpy);
        Badge badge = new Badge("badge_01");
        LogSpy logSpy = new LogSpy();
        MoteurOuverture moteurOuverture = new MoteurOuverture(logSpy);

        //QUAND le badge est détecté
        lecteur.simulerDetectionBadge(badge);

        //ET que le lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS le log a récupéré les bonnes informations
        String Prevision = "[INFO] " + logSpy.getTime() + " : " + badge.getNom() + " sur " + lecteur.getNom() + " - OK\n";
        assertEquals(Prevision, logSpy.getStockage());
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
        String Prevision = "[INFO] " + logSpy.getTime() + " : " + badge01.getNom() + " sur " + lecteur01.getNom() + " - OK\n"
                + "[INFO] " + logSpy.getTime() + " : " + badge02.getNom() + " sur " + lecteur02.getNom() + " - OK\n";
        assertEquals(Prevision, logSpy.getStockage());
    }

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
        assertEquals(Prevision, logSpy.getStockage());
    }

    @Test
    public void casLogLancementMoteur() {
        //ETANT DONNE un moteur
        LogSpy logSpy = new LogSpy();
        MoteurOuverture moteurOuverture = new MoteurOuverture("moteur_01",logSpy);

        //ALORS le log a récupéré les bonnes informations
        String Prevision = "[INFO] " +  logSpy.getTime() + " : moteur " + moteurOuverture.getNom() + " démarré\n";
        assertEquals(Prevision, logSpy.getStockage());
    }

    @Test
    public void casLogLevelInfo() {
        //ETANT DONNE un log instancié avec un niveau INFO
        LogSpy logSpy = new LogSpy(LogInterface.INFO);

        //QUAND on envoie un enregistrement de chaque type
        logSpy.log(LogInterface.INFO, "test");
        logSpy.log(LogInterface.WARN, "test");
        logSpy.log(LogInterface.ERROR, "test");

        //ALORS le log a récupéré tous les enregistrements
        String Prevision =
                  "[INFO] " +  logSpy.getTime() + " : test\n"
                + "[WARN] " +  logSpy.getTime() + " : test\n"
                + "[ERROR] " +  logSpy.getTime() + " : test\n";
        assertEquals(Prevision, logSpy.getStockage());
    }

    @Test
    public void casLogLevelWarn() {
        //ETANT DONNE un log instancié avec un niveau WARN
        LogSpy logSpy = new LogSpy(LogInterface.WARN);

        //QUAND on envoie un enregistrement de chaque type
        logSpy.log(LogInterface.INFO, "test");
        logSpy.log(LogInterface.WARN, "test");
        logSpy.log(LogInterface.ERROR, "test");

        //ALORS le log a récupéré uniquement les enregistrements de niveau WARN ou supérieur
        String Prevision =
                "[WARN] " +  logSpy.getTime() + " : test\n"
              + "[ERROR] " +  logSpy.getTime() + " : test\n";
        assertEquals(Prevision, logSpy.getStockage());
    }

    @Test
    public void casLogLevelError() {
        //ETANT DONNE un log instancié avec un niveau ERROR
        LogSpy logSpy = new LogSpy(LogInterface.ERROR);

        //QUAND on envoie un enregistrement de chaque type
        logSpy.log(LogInterface.INFO, "test");
        logSpy.log(LogInterface.WARN, "test");
        logSpy.log(LogInterface.ERROR, "test");

        //ALORS le log a récupéré uniquement les enregistrements de type ERROR
        String Prevision = "[ERROR] " +  logSpy.getTime() + " : test\n";
        assertEquals(Prevision, logSpy.getStockage());
    }



    @Test
    public void casMoteurSansLecteurException() {
        // ETANT DONNE un moteur sans lecteur
        LogSpy logSpy = new LogSpy();
        MoteurOuverture moteurOuverture = new MoteurOuverture("moteur_01", logSpy);

        // QUAND on essaie de démarrer le moteur sans lecteur
        // ALORS une exception LecteurException est levée
        LecteurException exception = assertThrows(LecteurException.class, moteurOuverture::interrogerLecteur);
        assertEquals("Lecteur manquant", exception.getMessage());
    }


//    @Test
//    public void casLogMoteurSansLecteurException() {
//        // ETANT DONNE un moteur sans lecteur
//        LogSpy logSpy = new LogSpy(LogInterface.ERROR);
//        MoteurOuverture moteurOuverture = new MoteurOuverture("moteur_01", logSpy);
//
//        // QUAND on essaie de démarrer le moteur sans lecteur
//        // ET qu'une exception LecteurException est levée
//
//            LecteurException exception = assertThrows(LecteurException.class, moteurOuverture::interrogerLecteur);
//            assertEquals("Lecteur manquant", exception.getMessage());
//
//            // ALORS le log récupère les bonnes informations
//            String Prevision = "[ERROR] " +  logSpy.getTime() + " : moteur " + moteurOuverture.getNom() + "Lecteur manquant\n";
//            assertEquals(Prevision, logSpy.getStockage());
//
//    }

    @Test
    public void casLogMoteurSansLecteurException() {
        // ETANT DONNE un moteur sans lecteur
        LogSpy logSpy = new LogSpy(LogInterface.ERROR);
        MoteurOuverture moteurOuverture = new MoteurOuverture("moteur_01", logSpy);

        // QUAND on essaie de démarrer le moteur sans lecteur
        LecteurException exception = assertThrows(LecteurException.class, moteurOuverture::interrogerLecteur);
        assertEquals("Lecteur manquant", exception.getMessage());

        // ALORS le log récupère les bonnes informations
        String Prevision = "[ERROR] " +  logSpy.getTime() + " : moteur " + moteurOuverture.getNom() + " : Lecteur manquant\n";
        assertEquals(Prevision, logSpy.getStockage());
    }


    //Log
    // -> Démmarage moteur
    // [INFO]  liste des lecteurs
    // -> Exception
    // [ERROR] message d'exception
    // -> Tentative accès
    // [INFO] si ok
    // [WARN] si ko
    //3 niveaux de démmarage : ERROR > WARN > INFO
    // -> ignore les niveaux inférieurs

    //Tests possibles :
    // OK Tester log lancement moteur
    //Tester moteur sans lecteur Exception (assertThrows)
    // OK Tester log chaque niveau moteur -> filtré ou non (3 tests)
    // OK Tester log moteur lecteur OK [INFO]
    // OK Tester log moteur lecteur KO [WARN]
    //Tester log moteur sans lecteur Exception [ERROR]
}