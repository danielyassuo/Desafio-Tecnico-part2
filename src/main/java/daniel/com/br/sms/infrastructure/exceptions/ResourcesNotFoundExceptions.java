package daniel.com.br.sms.infrastructure.exceptions;

public class ResourcesNotFoundExceptions extends RuntimeException {
    public ResourcesNotFoundExceptions(String message) {
        super(message);
    }

    public ResourcesNotFoundExceptions (String message, Throwable throwable){
        super(message, throwable);
    }
}
