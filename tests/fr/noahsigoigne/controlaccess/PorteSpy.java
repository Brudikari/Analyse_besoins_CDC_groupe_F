package fr.noahsigoigne.controlaccess;

public class PorteSpy implements PorteInterface{
    private boolean ouvert;
    public PorteSpy(LecteurFake lecteurFake) {
        this.ouvert = false;
    }

    public boolean verifierOuvertureDemandee() {
        return ouvert;
    }

    @Override
    public void ouvrir() {
        this.ouvert = true;
    }
}
