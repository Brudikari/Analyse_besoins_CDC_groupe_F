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

        //QUAND le badge est détecté
        lecteur.simulerDetectionBadge(badge);

        //ET que le lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS le log a récupéré les bonnes informations
        String Prevision = logSpy.getTime() + " : " + badge.getNom() + " sur " + lecteur.getNom() + " - OK\n";
        assertEquals(logSpy.getStockage(), Prevision);
    }

    //Log
    //Output dans un string
    //Quand ouverture valide, mettre dans un journal que ce lecteur a vu passer ce badge
    //{Horodatage} : {badge} sur {lecteur} - OK / KO

    // 2024-02-08_09-59-10 : badge_01 sur lecteur_01 - OK \n
    //Tests -> OK, KO

    //TODO Tester si aucune infos dans le log
    //TODO Tester si plusieurs infos dans le log
}