import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all library members.
 * Subclasses (Student, Teacher) decide their own borrow limit and loan period.
 */
public abstract class Member {
    private final String id;
    private final String name;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /** Maximum number of books this type of member can borrow at once. */
    public abstract int getBorrowLimit();

    /** Number of days a book can be kept. */
    public abstract int getLoanDays();

    /** Member type label, e.g. "Student". */
    public abstract String getType();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    public boolean hasReachedLimit() {
        return borrowedBooks.size() >= getBorrowLimit();
    }

    public void borrow(Book book) {
        borrowedBooks.add(book);
    }

    public boolean giveBack(Book book) {
        return borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - ID: %s, Books: %d/%d",
                name, getType(), id, borrowedBooks.size(), getBorrowLimit());
    }
}
