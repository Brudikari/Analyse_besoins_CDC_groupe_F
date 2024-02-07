package fr.noahsigoigne.controlaccess;

public interface BadgeInterface {

    boolean estValide();
    void bloquer();
    void debloquer();
    boolean estBloque();
}
