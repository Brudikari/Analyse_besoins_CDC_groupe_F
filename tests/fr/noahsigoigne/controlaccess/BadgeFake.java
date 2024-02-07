package fr.noahsigoigne.controlaccess;

public class BadgeFake implements BadgeInterface {
    boolean valide;
    boolean bloque = false;

    public BadgeFake(boolean valide) {
        this.valide = valide;
    }

    public BadgeFake() {
        this.valide = true;
    }

    @Override
    public boolean estValide() {
        return valide;
    }

    @Override
    public void bloquer() {
        this.bloque = true;
    }

    @Override
    public void debloquer() {
        this.bloque = false;
    }

    @Override
    public boolean estBloque() {
        return bloque;
    }
}
