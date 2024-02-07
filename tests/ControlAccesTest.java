import fr.noahsigoigne.controlaccess.MoteurOuverture;
import org.junit.Test;
//import org.junit.jupiter.api.test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.Assert.assertTrue;

import fr.noahsigoigne.controlaccess.LecteurFake;
import fr.noahsigoigne.controlaccess.PorteSpy;

public class ControlAccesTest {

    @Test
    public void casNominal() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteurFake = new LecteurFake(porteSpy);
        BadgeFake badgeFake = new BadgeFake();
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

        //QUAND un badge est passé devant le lecteur
        lecteurFake.simulerDetectionBadge(badgeFake);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteurFake);

        //ALORS la porte est déverrouillée
        assertTrue(porteSpy.verifierOuvertureDemandee());
    }


    @Test
    public void CasSansInterrogation() {
        //ETANT DONNE un lecteur relié à une porte
        LecteurFake lecteurFake = new LecteurFake();
        PorteSpy porteSpy = new PorteSpy();
        BadgeFake badgeFake = new BadgeFake();

        //QUAND un badge est détecté
        lecteurFake.simulerDetectionBadge(badgeFake);

        //ET que ce lecteur n'est pas interrogé

        //ALORS la porte n'est pas déverrouillée
        assertFalse(porteSpy.verifierOuvertureDemandee());
    }

    @Test
    public void CasSansDétection() {
        //ETANT DONNE un lecteur relié à une porte
        LecteurFake lecteurFake = new LecteurFake();
        PorteSpy porteSpy = new PorteSpy();
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

        //QUAND aucun badge n'est détecté

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteurFake);

        //ALORS la porte n'est pas déverrouillée
        assertFalse(porteSpy.verifierOuvertureDemandee());
    }

    @Test
    public void casPlusieursPortes() {
        //ETANT DONNE un lecteur relié à deux porte
        PorteSpy porteSpy1 = new PorteSpy();
        PorteSpy porteSpy2 = new PorteSpy();
        LecteurFake lecteurFake = new LecteurFake(porteSpy1, porteSpy2);
        BadgeFake badgeFake = new BadgeFake();
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy1, porteSpy2);

        //QUAND un badge est détecté
        lecteurFake.simulerDetectionBadge(badgeFake);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteurFake);

        //ALORS les portes sont déverrouillées
        assertTrue(porteSpy1.verifierOuvertureDemandee());
        assertTrue(porteSpy2.verifierOuvertureDemandee());
    }

    @Test
    public void CasPlusieursLecteurs() {
        //ETANT DONNE plusieurs lecteurs reliés à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteurFake1 = new LecteurFake(porteSpy);
        LecteurFake lecteurFake2 = new LecteurFake(porteSpy);
        BadgeFake badgeFake = new BadgeFake();
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

        //QUAND un badge est passé devant le deuxième lecteur
        lecteurFake2.simulerDetectionBadge();
        //QUAND un badge est détecté
        lecteurFake2.simulerDetectionBadge(badgeFake);

        // ET que ces lecteurs sont interrogés
        moteurOuverture.interrogerLecteur(lecteurFake1, lecteurFake2);

        // ALORS la porte est deverrouillée
        assertTrue(porteSpy.verifierOuvertureDemandee());
    }
    @Test
    public void casPlusieursLecteursPlusieursPortes() {
        // ETANT DONNE plusieurs lecteurs reliés chacun à leur porte
        PorteSpy porteSpy1 = new PorteSpy();
        PorteSpy porteSpy2 = new PorteSpy();
        LecteurFake lecteurFake1 = new LecteurFake(porteSpy1);
        BadgeFake badgeFake = new BadgeFake();
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy1);

        // QUAND un badge est passé devant le deuxième lecteur
        lecteurFake1.simulerDetectionBadge(badgeFake);

        // ET que ces lecteurs sont interrogés
        moteurOuverture.interrogerLecteur(lecteurFake1);

        //ALORS seule la 2e porte est déverrouillée
        assertTrue(porteSpy1.verifierOuvertureDemandee());
        assertFalse(porteSpy2.verifierOuvertureDemandee());
    }

    @Test
    public void casBadgeNonValide() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteurFake = new LecteurFake(porteSpy);
        BadgeFake badgeFake = new BadgeFake(false);
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

        //QUAND un badge non valide est détecté
        lecteurFake.simulerDetectionBadge(badgeFake);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteurFake);

        //ALORS la porte n'est pas déverrouillée
        assertFalse(porteSpy.verifierOuvertureDemandee());
    }

    @Test
    public void casBadgeBloque() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteurFake = new LecteurFake(porteSpy);
        BadgeFake badgeFake = new BadgeFake();
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

        //QUAND un badge est bloqué
        badgeFake.bloquer();

        //ET ce badge est détecté
        lecteurFake.simulerDetectionBadge(badgeFake);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteurFake);

        //ALORS la porte n'est pas déverrouillée
        assertFalse(porteSpy.verifierOuvertureDemandee());
    }


    @Test
    public void casBadgeBloquePuisDebloque() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteurFake = new LecteurFake(porteSpy);
        BadgeFake badgeFake = new BadgeFake();
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

        //QUAND un badge est bloqué
        badgeFake.bloquer();

        //QUAND un badge est ddébloqué
        badgeFake.debloquer();

        //ET ce badge est détecté
        lecteurFake.simulerDetectionBadge(badgeFake);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteurFake);

        //ALORS la porte n'est pas déverrouillée
        assertTrue(porteSpy.verifierOuvertureDemandee());
    }

    // Autorisé : 3 portes 2 lecteurs, lecteur 1 lié aux 2 premières portes, le 2e aux 2 dernières
    // le badge est autorisé sur lecteur 1 mais pas le 2
    //voir si la porte 2 est bien ouverte

}