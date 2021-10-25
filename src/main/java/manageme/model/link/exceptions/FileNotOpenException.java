package manageme.model.link.exceptions;

public class FileNotOpenException extends RuntimeException {
    public FileNotOpenException() {
        super("Cannot open file. Please make sure both the file to open and the program used for opening the file " +
                "exist.");
    }
}
