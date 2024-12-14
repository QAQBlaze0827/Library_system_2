import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<Integer, Book> books; // 使用 Map 儲存書籍 (key: bookID, value: Book object)

    public Library() {
        books = new HashMap<>();
    }

    // 新增書籍
    public void addBook(String bookName, int bookID) {
        if (books.containsKey(bookID)) {
            
            System.out.println("Book with ID " + bookID + " already exists.");
        } else {
            AddBook addBookInstance = new AddBook();
            addBookInstance.addBooktolist(bookName , bookID , false);
            // Book book = new Book(bookName, bookID, false); //舊的
            // books.put(bookID, book); //舊的
            System.out.println("Book \"" + bookName + "\" added successfully.");
        }
    }

    // 刪除書籍
    public void deleteBook(int bookID) {
        if (books.containsKey(bookID)) {
            books.remove(bookID);
            System.out.println("Book with ID " + bookID + " deleted successfully.");
        } else {
            System.out.println("Book with ID " + bookID + " not found.");
        }
    }

    // 借書
    public void borrowBook(int bookID) {
        Book book = books.get(bookID);
        if (book != null) {
            if (!book.getIsBorrowed()) {
                // book.addBook(book.getBookName(), book.getBookID()); // 保持書籍資訊不變
                book.borrowBook();
                System.out.println("Book \"" + book.getBookName() + "\" borrowed successfully.");
            } else {
                System.out.println("Book \"" + book.getBookName() + "\" is already borrowed.");
            }
        } else {
            System.out.println("Book with ID " + bookID + " not found.");
        }
    }

    // 還書
    public void returnBook(int bookID) {
        Book book = books.get(bookID);
        if (book != null) {
            if (book.getIsBorrowed()) {
                // book.addBook(book.getBookName(), book.getBookID()); // 保持書籍資訊不變
                System.out.println("Book \"" + book.getBookName() + "\" returned successfully.");
            } else {
                System.out.println("Book \"" + book.getBookName() + "\" was not borrowed.");
            }
        } else {
            System.out.println("Book with ID " + bookID + " not found.");
        }
    }

    // 顯示所有書籍
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Books in the library:");
            for (Book book : books.values()) {
                System.out.println("ID: " + book.getBookID() + ", Name: " + book.getBookName() +
                        ", Borrowed: " + (book.getIsBorrowed() ? "Yes" : "No"));
            }
        }
    }
}
