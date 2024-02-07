package fr.noahsigoigne.controlaccess;

public interface LecteurInterface {

    boolean aDetecteBadge();

    boolean aDetecteBadgeCorrect();

    PorteInterface[] getPortes();
}
