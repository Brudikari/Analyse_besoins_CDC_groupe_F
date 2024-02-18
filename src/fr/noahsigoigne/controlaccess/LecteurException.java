package fr.noahsigoigne.controlaccess;

public class LecteurException extends RuntimeException {

        public LecteurException() {
            super();
        }

        public LecteurException(String message) {
            super(message);
        }

        public LecteurException(String message, Throwable cause) {
            super(message, cause);
        }

        public LecteurException(Throwable cause) {
            super(cause);
        }

}
