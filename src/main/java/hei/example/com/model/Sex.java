package hei.example.com.model;

public enum Sex {
    M("Male"),
    F("Female");

    private final String label;

    Sex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

