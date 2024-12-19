import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class User {
    private static final AtomicInteger nextUid = new AtomicInteger(0); // 靜態變數，負責自動生成 UID
    private final int uid; // 用戶的 UID
    private final String uname;
    private final String upassword;
    private final List<Book> borrowedBooks = new ArrayList<>(); // 用戶借閱的書籍

    // 建構子: 自動生成 UID
    public User(String uname, String upassword) {
        this.uid = nextUid.getAndIncrement(); // 自動生成 UID
        this.uname = uname;
        this.upassword = upassword;
    }

    // 建構子: 從 CSV 加載用戶時使用
    public User(int uid, String uname, String upassword, List<Book> borrowedBooks) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.borrowedBooks.addAll(borrowedBooks);
        updateNextUid(uid); // 確保 nextUid 不小於當前的最大 UID
    }

    // 更新靜態變數 nextUid
    private static void updateNextUid(int uid) {
        nextUid.set(Math.max(uid + 1, nextUid.get()));
    }

    public int getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // 將 User 轉為 CSV 行
    public String toCsvRow() {
        return uid + "," + uname + "," + upassword + "," +
               borrowedBooks.stream()
                            .map(Book::getBookID)
                            .map(String::valueOf)
                            .collect(Collectors.joining(";"));
    }

    // 解析 CSV 文件中的行
    public static User fromCsvRow(String csvRow) {
        if (csvRow == null || csvRow.isEmpty()) {
            return null;
        }
        String[] parts = csvRow.split(",");
        if (parts.length < 3) {
            return null;
        }

        int uid = Integer.parseInt(parts[0]); // 確保使用 CSV 中的 UID
        String uname = parts[1];
        String upassword = parts[2];

        // 如果有借閱書籍，則解析書籍 ID
        List<Book> borrowedBooks = new ArrayList<>();
        if (parts.length > 3 && !parts[3].isEmpty()) {
            borrowedBooks = Arrays.stream(parts[3].split(";"))
                                   .map(Integer::parseInt)
                                   .map(Library::getBookById)
                                   .filter(book -> book != null)
                                   .collect(Collectors.toList());
        }
        return new User(uid, uname, upassword, borrowedBooks); // 使用正確的建構子
    }
}
