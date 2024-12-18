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

    // 獲取圖書館中的所有書籍
    public static List<Book> getLibraryBooks() {
        return new ArrayList<>(libraryBooks); // 返回副本，避免直接修改原始數據
    }
}
