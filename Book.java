/**
 * Represents a single book in the library.
 * Fields are private (encapsulation) and accessed through getters/setters.
 */
public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean available;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        String status = available ? "Available" : "Borrowed";
        return String.format("%-8s %-30s %-22s %s", isbn, title, author, status);
    }
}
