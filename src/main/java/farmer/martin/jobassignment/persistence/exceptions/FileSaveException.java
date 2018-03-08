package farmer.martin.jobassignment.persistence.exceptions;

/**
 * Exception to track an error saving the file to disk.
 * 
 * @author martinfarmer
 */
public class FileSaveException extends Exception {

    private String filename;
    
    public FileSaveException(String filename, String message) {
        super(message);
        this.filename = filename;
    }

    public FileSaveException(String filename, String message, Throwable cause) {
        super(message, cause);
        this.filename = filename;
    }    
}
