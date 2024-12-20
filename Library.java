import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    // 全局的圖書集合
    private static final List<Book> libraryBooks = new ArrayList<>();

    // 添加書籍到圖書館
    public static void addBook(Book book) {
        libraryBooks.add(book);
    }

    // 根據書籍 ID 查找書籍
    public static Book getBookById(int bookId) {
        return libraryBooks.stream()
                .filter(book -> book.getBookID() == bookId)
                .findFirst()
                .orElse(null);
    }
    //將userid借的書籍加入到借書者的借書清單
    public void borrowBookByUser(int userid, int bookID) {
        String path = "allBook.csv";
        List<Book> allBooks = Book.loadBooksFromFile(path);

        // 查找目標書籍
        for (Book book : allBooks) {
            if (book.getBookID() == bookID) {
                book.borrowBook(userid); // 借書
                String dueDate = calculateDueDate(14);
                book.setBorrowDate(dueDate);
                book.updateBookInCsv(path); // 更新到 CSV 文件
                return;
            }
        }
        System.out.println("Book not found!");
    }
    //將userid還的書籍從借書者的借書清單中刪除
    public void returnBookByUser(int userid, int bookID) {
        String path = "allBook.csv";
        List<Book> allBooks = Book.loadBooksFromFile(path);

        // 查找目標書籍
        for (Book book : allBooks) {
            if (book.getBookID() == bookID) {
                book.returnBook(userid); // 還書
                book.updateBookInCsv(path); // 更新到 CSV 文件
                return;
            }
        }
        System.out.println("Book not found!");
    }
    private String calculateDueDate(int daysFromNow) {
        LocalDate dueDate = LocalDate.now().plusDays(daysFromNow);
        return dueDate.toString(); // 使用 yyyy-MM-dd 格式
    }
    // 獲取圖書館中的所有書籍
    public static List<Book> getLibraryBooks() {
        return new ArrayList<>(libraryBooks); // 返回副本，避免直接修改原始數據
    }
}
