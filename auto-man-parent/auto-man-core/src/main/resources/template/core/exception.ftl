package ${projectBasePackage}.exception;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public class ${projectName.capFirst}Exception extends RuntimeException{
    public ${projectName}Exception() {
        super();
    }

    public ${projectName.capFirst}Exception(String message) {
        super(message);
    }

    public ${projectName.capFirst}Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public ${projectName.capFirst}Exception(Throwable cause) {
        super(cause);
    }
}
