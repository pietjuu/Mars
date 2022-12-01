package be.howest.ti.mars.web.exceptions;

public class TransporterAPIException extends RuntimeException{

    private final int statusCode;

    public TransporterAPIException(int statusCode, String message){
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
