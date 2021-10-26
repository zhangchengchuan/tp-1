package manageme.model.link.exceptions;

public class FileNotOpenException extends RuntimeException {

    /**
     * Signals the file opening process failed.
     */
    public FileNotOpenException() {
        super("Cannot open file. Please make sure both the file to open and the program used for opening the file "
                + "exist.");
    }
}
