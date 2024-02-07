package fr.noahsigoigne.controlaccess;

public class Badge implements BadgeInterface {
    boolean bloque = false;


    public Badge() {
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
