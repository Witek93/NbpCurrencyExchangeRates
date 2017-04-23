package model;

public class StatisticsResult {
    private Status status;
    private String info;

    public StatisticsResult(Status status, String info) {
        this.status = status;
        this.info = info;
    }

    public Status getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatisticsResult that = (StatisticsResult) o;

        if (status != that.status) return false;
        return info != null ? info.equals(that.info) : that.info == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StatisticsResult{" +
                "status=" + status +
                ", info='" + info + '\'' +
                '}';
    }

    public enum Status {
        SUCCESS,
        RATES_SERVICE_FAILED,
        RATES_SERVICE_INVALID_CURRENCY,
        FAILURE;
    }
}
