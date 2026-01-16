package unl.edu.ec.jbrew.exception;

public class CredentialInvalidException extends Exception{

    public CredentialInvalidException() {
        super("Invalid credentials");
    }

    public CredentialInvalidException(String message) {
        super(message);
    }

    public CredentialInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
