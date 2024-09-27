import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Abstraction: Abstraction hides the complex reality while exposing only the necessary parts
public interface LoanBook {
    boolean checkAvailability();
    void markAsBorrowed();
    void markAsReturn();
}

// Book class implementing LoanBook
public class Book implements LoanBook {
    private String title;
    private String author;
    private String isbn;
    private int availableCopies; // Changed to int

    public Book(String title, String author, String isbn, int availableCopies) { // Changed to int
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAvailableCopies() { // Changed to return int
        return availableCopies;
    }

    @Override
    public boolean checkAvailability() {
        return availableCopies > 0;
    }

    @Override
    public void markAsBorrowed() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    @Override
    public void markAsReturn() {
        availableCopies++;
    }
}

// User class
public abstract class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

// Member class
public class Member extends User {
    public Member(String name, String email) {
        super(name, email);
    }
}

// Librarian class
public class Librarian extends Member {
    public Librarian(String name, String email) {
        super(name, email);
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
    }

    public void removeBook(Library library, Book book) {
        library.removeBook(book);
    }
}

// Loan class
public class Loan {
    private String loanId;
    private Book book;
    private User user;
    private Date loanDate;
    private Date dueDate;

    public Loan(String loanId, Book book, User user, Date loanDate, Date dueDate) {
        this.loanId = loanId;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public boolean isOverdue() {
        return new Date().after(dueDate);
    }
}

// Library class
public class Library {
    private List<Book> books = new ArrayList<>(); //private encapsulation
    private List<Loan> loans = new ArrayList<>(); //private encapsulation

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
    //Polymorphism: Polymorphism allows methods to perform different tasks based on the object that it is acting upon, even if they share the same name.
    public void borrowBook(User user, Book book) {
        if (book.checkAvailability()) {
            book.markAsBorrowed();
            Loan loan = new Loan("L" + (loans.size() + 1), book, user, new Date(), new Date(System.currentTimeMillis() + (14 * 24 * 60 * 60 * 1000))); // 2 weeks
            loans.add(loan);
            System.out.println(user.getName() + " borrowed book " + book.getTitle());
        } else {
            System.out.println("The book is not available.");
        }
    }

    public void returnBook(Loan loan) {
        loan.book.markAsReturn();
        loans.remove(loan);
        System.out.println(loan.user.getName() + " returned book " + loan.book.getTitle());
    }

    public List<Loan> getLoans() { // Added accessor method for loans
        return loans;
    }
}

// Main Class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("Solethu", "Solethu@example.com");
        Member member = new Member("Sox", "Sox@example.com");

        // Librarian adds a book
        Book book1 = new Book("To Kill a Mockingbird", "Harper Lee", "123456789", 3); // Changed book title
        librarian.addBook(library, book1);

        // Member borrows a book
        library.borrowBook(member, book1);

        // Returning the book
        Loan lastLoan = library.getLoans().get(library.getLoans().size() - 1); // Use the accessor method
        library.returnBook(lastLoan);
    }
}


