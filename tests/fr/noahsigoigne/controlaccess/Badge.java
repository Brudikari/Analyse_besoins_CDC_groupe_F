package fr.noahsigoigne.controlaccess;

public class Badge implements BadgeInterface {
    private boolean bloque = false;

    private String nom;

    public Badge() {
    }
    public Badge(String nomDuBadge) {
        this.nom = nomDuBadge;
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
    public String getNom() {
        return nom;
    }

}
