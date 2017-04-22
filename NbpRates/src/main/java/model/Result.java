package model;

public enum Result {
    SUCCESS(""),
    FAILURE(""),
    INCOMPLETE_RESPONSE("");

    private final String details;

    public String getDetails() {
        return details;
    }

    Result(String details) {
        this.details = details;
    }
}
