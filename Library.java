import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds all books and members and contains the main library logic:
 * adding, searching, borrowing and returning.
 */
public class Library {
    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    public boolean addBook(Book book) {
        if (findBook(book.getIsbn()) != null) {
            return false; // duplicate ISBN
        }
        books.add(book);
        return true;
    }

    public boolean addMember(Member member) {
        if (findMember(member.getId()) != null) {
            return false; // duplicate ID
        }
        members.add(member);
        return true;
    }

    public Book findBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                return b;
            }
        }
        return null;
    }

    public Member findMember(String id) {
        for (Member m : members) {
            if (m.getId().equalsIgnoreCase(id)) {
                return m;
            }
        }
        return null;
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public List<Member> getMembers() {
        return Collections.unmodifiableList(members);
    }

    /** Borrow a book. Returns a message describing what happened. */
    public String borrowBook(String memberId, String isbn) {
        Member member = findMember(memberId);
        if (member == null) {
            return "Member not found.";
        }
        Book book = findBook(isbn);
        if (book == null) {
            return "Book not found.";
        }
        if (!book.isAvailable()) {
            return "Sorry, this book is already borrowed.";
        }
        if (member.hasReachedLimit()) {
            return member.getName() + " has reached the limit of "
                    + member.getBorrowLimit() + " books.";
        }
        book.setAvailable(false);
        member.borrow(book);
        return "Done! " + member.getName() + " borrowed \"" + book.getTitle()
                + "\". Please return within " + member.getLoanDays() + " days.";
    }

    /** Return a book. Returns a message describing what happened. */
    public String returnBook(String memberId, String isbn) {
        Member member = findMember(memberId);
        if (member == null) {
            return "Member not found.";
        }
        Book book = findBook(isbn);
        if (book == null) {
            return "Book not found.";
        }
        if (!member.giveBack(book)) {
            return member.getName() + " has not borrowed this book.";
        }
        book.setAvailable(true);
        return "Thank you! \"" + book.getTitle() + "\" has been returned.";
    }
}
