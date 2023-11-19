package hei.example.model;

public enum Sex {
    M("male"),
    F("female");

    private final String label;

    Sex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

