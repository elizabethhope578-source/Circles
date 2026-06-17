/**
 * LibraryDemo – demonstrates the library management system.
 *
 * Demonstrates:
 *  - Creating books and members
 *  - Lending books successfully
 *  - Attempting to lend an already-on-loan book (graceful rejection)
 *  - Returning a book
 *  - Printing library state before and after operations
 */
public class LibraryDemo {

    public static void main(String[] args) {

        // ── 1. Set up library ─────────────────────────────────────────────
        Library library = new Library("Kampala Community Library");

        // Three books (mix of constructors)
        Book b1 = new Book("978-0-13-468599-1", "Clean Code");                       // constructor 1
        Book b2 = new Book("978-0-13-235088-4", "The Pragmatic Programmer", "Hunt"); // constructor 2
        Book b3 = new Book("978-0-20-163361-0", "Design Patterns",
                           "Gang of Four");                                            // constructor 2

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        // Two members
        Member alice = new Member("M001", "Alice Nakato");
        Member bob   = new Member("M002", "Bob Ssentongo");

        library.registerMember(alice);
        library.registerMember(bob);

        // ── 2. State BEFORE operations ────────────────────────────────────
        System.out.println("\n>>> STATE BEFORE ANY LOANS <<<");
        library.printState();

        // ── 3. Lend operations ────────────────────────────────────────────
        System.out.println(">>> LOAN OPERATIONS <<<");

        // Alice borrows Clean Code
        library.lendBook("M001", "978-0-13-468599-1");

        // Bob borrows Design Patterns
        library.lendBook("M002", "978-0-20-163361-0");

        // Bob also borrows The Pragmatic Programmer (member can hold many loans)
        library.lendBook("M002", "978-0-13-235088-4");

        // ── 4. Attempt to lend an already-on-loan book (must be rejected) ─
        System.out.println("\n>>> ATTEMPTED DUPLICATE LOAN (should be rejected) <<<");
        library.lendBook("M001", "978-0-20-163361-0"); // Design Patterns is already with Bob

        // ── 5. State AFTER loans ──────────────────────────────────────────
        System.out.println("\n>>> STATE AFTER LOANS <<<");
        library.printState();

        // ── 6. Return a book ──────────────────────────────────────────────
        System.out.println(">>> RETURN OPERATION <<<");
        library.returnBook("M002", "978-0-20-163361-0"); // Bob returns Design Patterns

        // ── 7. Now lend the returned book to Alice ─────────────────────────
        System.out.println("\n>>> LENDING RETURNED BOOK TO ALICE <<<");
        library.lendBook("M001", "978-0-20-163361-0");

        // ── 8. Search by title ─────────────────────────────────────────────
        System.out.println("\n>>> SEARCH: 'design' <<<");
        for (Book found : library.searchByTitle("design")) {
            System.out.println("  Found: " + found);
        }

        // ── 9. Final state ─────────────────────────────────────────────────
        System.out.println("\n>>> FINAL STATE <<<");
        library.printState();
    }
}
