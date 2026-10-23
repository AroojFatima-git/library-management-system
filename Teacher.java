/** A teacher member: can borrow up to 5 books for 30 days. */
public class Teacher extends Member {

    public Teacher(String id, String name) {
        super(id, name);
    }

    @Override
    public int getBorrowLimit() {
        return 5;
    }

    @Override
    public int getLoanDays() {
        return 30;
    }

    @Override
    public String getType() {
        return "Teacher";
    }
}
