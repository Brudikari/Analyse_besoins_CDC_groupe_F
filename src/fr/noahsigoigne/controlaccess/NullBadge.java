package fr.noahsigoigne.controlaccess;

public class NullBadge implements BadgeInterface{

    @Override
    public void bloquer() {

    }

    @Override
    public void debloquer() {

    }

    @Override
    public boolean estBloque() {
        return false;
    }

    @Override
    public String getNom() {
        return "";
    }
}
