package model;

public class Currency {

    private final String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        return code != null ? code.equalsIgnoreCase(currency.code) : currency.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                '}';
    }

    public Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
