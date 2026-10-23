/** A student member: can borrow up to 3 books for 14 days. */
public class Student extends Member {

    public Student(String id, String name) {
        super(id, name);
    }

    @Override
    public int getBorrowLimit() {
        return 3;
    }

    @Override
    public int getLoanDays() {
        return 14;
    }

    @Override
    public String getType() {
        return "Student";
    }
}
