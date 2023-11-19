package hei.example.model;

public enum BookStatus {
    available ("available"),
    borrowed ("borrowed");

    private final String label;

    BookStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

