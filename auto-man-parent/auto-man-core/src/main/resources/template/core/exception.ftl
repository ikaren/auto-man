package ${domain.packageName};

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public class ${domain.simpleName}Exception extends RuntimeException{
    public ${domain.simpleName}Exception() {
        super();
    }

    public ${domain.simpleName}Exception(String message) {
        super(message);
    }

    public ${domain.simpleName}Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public ${domain.simpleName}Exception(Throwable cause) {
        super(cause);
    }
}
