package constants;

/**
 * A enum class that holds all the validation error messages
 */
public enum Error {
    INVALID_SYMBOL("Symbols '%s' are invalid for date"),
    INVALID_BASE("Base '%s' is not supported.");

    private String errorMessage;

    Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }
}
