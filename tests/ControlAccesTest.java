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
    public void TestOk() {
        assertTrue(true);
    }


    @Test
    public void casNominal() {
        //ETANT DONNE un lecteur relié à une porte
        LecteurFake lecteurFake = new LecteurFake();
        PorteSpy porteSpy = new PorteSpy(lecteurFake);
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

        //QUAND un badge est détecté
        lecteurFake.simulerDetectionBadge();

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteurFake);

        //ALORS la porte est déverrouillée
        assertTrue(porteSpy.verifierOuvertureDemandee());
    }


    @Test
    public void casPasNominal() {
        //ETANT DONNE un lecteur relié à une porte
        LecteurFake lecteurFake = new LecteurFake();
        PorteSpy porteSpy = new PorteSpy(lecteurFake);
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy);

        //QUAND un badge est détecté
        lecteurFake.simulerDetectionBadge();

        //ET que ce lecteur est interrogé

        //ALORS la porte est déverrouillée
        assertFalse(porteSpy.verifierOuvertureDemandee());
    }

    @Test
    public void casPlusieursPortes() {
        //ETANT DONNE un lecteur relié à deux porte
        LecteurFake lecteurFake = new LecteurFake();
        PorteSpy porteSpy1 = new PorteSpy(lecteurFake);
        PorteSpy porteSpy2 = new PorteSpy(lecteurFake);
        MoteurOuverture moteurOuverture = new MoteurOuverture(porteSpy1, porteSpy2);

        //QUAND un badge est détecté
        lecteurFake.simulerDetectionBadge();

        //ET que ce lecteur est interrogé
        moteurOuverture.interrogerLecteur(lecteurFake);

        //ALORS la porte est déverrouillée
        assertTrue(porteSpy1.verifierOuvertureDemandee());
        assertTrue(porteSpy2.verifierOuvertureDemandee());
    }
}