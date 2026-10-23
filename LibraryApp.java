import java.util.List;
import java.util.Scanner;

/** Console menu for the Library Management System. */
public class LibraryApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        loadSampleData();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose (1-8): ");
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addMember();
                    break;
                case 3:
                    showBooks(library.getBooks());
                    break;
                case 4:
                    searchBooks();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    showMemberBooks();
                    break;
                case 8:
                    System.out.println("Allah Hafiz!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Library Management System ===");
        System.out.println("1. Add book");
        System.out.println("2. Add member");
        System.out.println("3. Show all books");
        System.out.println("4. Search book by title");
        System.out.println("5. Borrow book");
        System.out.println("6. Return book");
        System.out.println("7. Show a member's borrowed books");
        System.out.println("8. Exit");
    }

    private static void loadSampleData() {
        library.addBook(new Book("B101", "Clean Code", "Robert C. Martin"));
        library.addBook(new Book("B102", "Head First Java", "Kathy Sierra"));
        library.addBook(new Book("B103", "Python Crash Course", "Eric Matthes"));
        library.addBook(new Book("B104", "Artificial Intelligence", "Stuart Russell"));
        library.addMember(new Student("S1", "Ayesha"));
        library.addMember(new Teacher("T1", "Sir Ahmed"));
    }

    private static void addBook() {
        String isbn = readLine("ISBN / Book ID: ");
        String title = readLine("Title: ");
        String author = readLine("Author: ");
        if (isbn.isEmpty() || title.isEmpty() || author.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }
        if (library.addBook(new Book(isbn, title, author))) {
            System.out.println("Book added.");
        } else {
            System.out.println("A book with this ID already exists.");
        }
    }

    private static void addMember() {
        String id = readLine("Member ID: ");
        String name = readLine("Name: ");
        String type = readLine("Type (S = Student, T = Teacher): ");
        if (id.isEmpty() || name.isEmpty()) {
            System.out.println("ID and name are required.");
            return;
        }
        Member member;
        if (type.equalsIgnoreCase("S")) {
            member = new Student(id, name);
        } else if (type.equalsIgnoreCase("T")) {
            member = new Teacher(id, name);
        } else {
            System.out.println("Invalid type. Use S or T.");
            return;
        }
        if (library.addMember(member)) {
            System.out.println("Member added: " + member);
        } else {
            System.out.println("A member with this ID already exists.");
        }
    }

    private static void showBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        System.out.println(String.format("%-8s %-30s %-22s %s", "ID", "Title", "Author", "Status"));
        System.out.println("-".repeat(70));
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private static void searchBooks() {
        String keyword = readLine("Enter title keyword: ");
        showBooks(library.searchByTitle(keyword));
    }

    private static void borrowBook() {
        String memberId = readLine("Member ID: ");
        String isbn = readLine("Book ID: ");
        System.out.println(library.borrowBook(memberId, isbn));
    }

    private static void returnBook() {
        String memberId = readLine("Member ID: ");
        String isbn = readLine("Book ID: ");
        System.out.println(library.returnBook(memberId, isbn));
    }

    private static void showMemberBooks() {
        String memberId = readLine("Member ID: ");
        Member member = library.findMember(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        System.out.println(member);
        showBooks(member.getBorrowedBooks());
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "";
    }

    private static int readInt(String prompt) {
        String input = readLine(prompt);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
