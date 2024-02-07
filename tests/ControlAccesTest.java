import org.junit.Test;
//import org.junit.jupiter.api.test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.Assert.assertTrue;

import fr.noahsigoigne.controlaccess.LecteurFake;
import fr.noahsigoigne.controlaccess.PorteSpy;
import fr.noahsigoigne.controlaccess.Badge;
import fr.noahsigoigne.controlaccess.MoteurOuverture;

public class ControlAccesTest {

    @Test
    public void casNominal() {
        //ETANT DONNE un lecteur relié à une porte
        PorteSpy porteSpy = new PorteSpy();
        LecteurFake lecteur = new LecteurFake(porteSpy);
        Badge badge = new Badge();
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

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
    public void CasSansDétection() {
        //ETANT DONNE un lecteur relié à une porte
        LecteurFake lecteur = new LecteurFake();
        PorteSpy porteSpy = new PorteSpy();
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

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
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy1, porteSpy2);

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
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

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
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy1);

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
        Badge badge = new Badge(); //TODO supprimer  de badge
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

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
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

        //QUAND un badge est bloqué
        badge.bloquer();

        //QUAND un badge est ddébloqué
        badge.debloquer();

        //ET ce badge est détecté
        lecteur.simulerDetectionBadge(badge);

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteur);

        //ALORS la porte n'est pas déverrouillée
        assertTrue(porteSpy.verifierOuvertureDemandee());
    }

    // Autorisé : 3 portes 2 lecteurs, lecteur 1 lié aux 2 premières portes, le 2e aux 2 dernières
    // le badge est autorisé sur lecteur 1 mais pas le 2
    //voir si la porte 2 est bien ouverte

    //Log
    //Output dans un string
    //Quand ouverture valide, mettre dans un journal que ce lecteur a vu passer ce badge
    //{Horodatage} : {badge} sur {lecteur} - OK / KO
}