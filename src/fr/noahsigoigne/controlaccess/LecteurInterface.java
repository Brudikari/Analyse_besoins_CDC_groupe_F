package fr.noahsigoigne.controlaccess;

public interface LecteurInterface {

    boolean aDetecteBadge();

    boolean aDetecteBadgeCorrect();

    PorteInterface[] getPortes();

    String getNom();
    BadgeInterface getBadge();
}
