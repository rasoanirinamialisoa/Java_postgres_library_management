package hei.example.com.model;

public enum BookStatus {
    AVAILABLE("Available"),
    BORROWED("Borrowed");

    private final String label;

    BookStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

