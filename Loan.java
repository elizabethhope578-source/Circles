import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an active loan that connects exactly one Member to one Book.
 * Records the borrow date and the due date (14 days by default).
 */
public class Loan {

    private static final int DEFAULT_LOAN_DAYS = 14;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    private Member     member;
    private Book       book;
    private LocalDate  borrowDate;
    private LocalDate  dueDate;

    /**
     * Creates a Loan with a default 14-day period starting today.
     *
     * @param member the borrowing member (must not be null)
     * @param book   the book being borrowed (must not be null)
     */
    public Loan(Member member, Book book) {
        this.member     = member;
        this.book       = book;
        this.borrowDate = LocalDate.now();
        this.dueDate    = borrowDate.plusDays(DEFAULT_LOAN_DAYS);
    }

    /**
     * Creates a Loan with an explicit borrow date (useful for testing).
     *
     * @param member     the borrowing member
     * @param book       the book being borrowed
     * @param borrowDate the date the book was borrowed
     */
    public Loan(Member member, Book book, LocalDate borrowDate) {
        this.member     = member;
        this.book       = book;
        this.borrowDate = borrowDate;
        this.dueDate    = borrowDate.plusDays(DEFAULT_LOAN_DAYS);
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    /** @return the member who borrowed the book */
    public Member getMember() { return member; }

    /** @return the book that was borrowed */
    public Book getBook() { return book; }

    /** @return the date the book was borrowed */
    public LocalDate getBorrowDate() { return borrowDate; }

    /** @return the date the book is due back */
    public LocalDate getDueDate() { return dueDate; }

    // ── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format("Loan[Member=%s, Book=\"%s\", Borrowed=%s, Due=%s]",
                member.getName(),
                book.getTitle(),
                borrowDate.format(FMT),
                dueDate.format(FMT));
    }
}
