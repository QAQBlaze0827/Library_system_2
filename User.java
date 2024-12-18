import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
class User {
    private static final AtomicInteger nextUid = new AtomicInteger(1); // 靜態變數，負責自動生成 UID
    private final int uid; //final 修飾符，表示該變數只能在建構子中賦值，之後不能修改
    private final String uname;
    private final String upassword;
    private final List<Book> borrowedBooks = new ArrayList<>(); // 用戶借閱的書籍


    // 建構子:自動生成 UID
    public User( String uname, String upassword) {
        this.uid = nextUid.getAndIncrement(); // 自動生成 UID 並遞增
        this.uname = uname;
        this.upassword = upassword;
    }
    // 建構子:從CSV加載用戶時使用
    public User(int uid, String uname, String upassword, List<Book> borrowedBooks) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.borrowedBooks.addAll(borrowedBooks);
        updateNextUid(uid); // 更新 靜態變數 nextUid
    }
    private static void updateNextUid(int uid) {
        nextUid.set(Math.max(uid + 1, nextUid.get()));
    }
    public int getUid(){
        return uid;
    }
    public String getUname(){
        return uname;
    }
    public String getUpassword(){
        return upassword;
    }
    public List<Book> getBorrowedBooks(){
        return borrowedBooks;
    }
    // 借閱書籍
    // public void borrowBook(Book book) {
    //     if(!book.getIsBorrowed()) {
    //         borrowedBooks.add(book);
    //         book.setBorrowed(true);
    //         book.setBorrowedByUid(this.uid);
    //         System.out.println("書籍 " + book.getBookName() + " 已成功借閱。");
    //     }else{
    //         System.out.println("書籍 " + book.getBookName() + " 已被借閱。");
    //     }
    // }
    // 歸還書籍
    // public void returnBook(Book book) {
    //     if(borrowedBooks.contains(book)) {
    //         borrowedBooks.remove(book);
    //         book.setBorrowed(false);
    //         book.setBorrowedByUid(null);
    //         System.out.println("書籍 " + book.getBookName() + " 已歸還。");
    //     }else{
    //         System.out.println("書籍 " + book.getBookName() + " 未被借閱。");
    //     }
    // }
    public static Book getBookById(int bookid){
        return  Library.getBookById(bookid);
    }
    // 將 User 轉為 CSV 行
    public String toCsvRow() {
        return uid + "," + uname + "," + upassword+ "," + 
               borrowedBooks.stream()
               .map(Book::getBookID)
               .map(String::valueOf)
               .collect(Collectors.joining(";"));
    }
    //解析 CSV 文件中的行
    public static User fromCsvRow(String csvRow){
        String[] parts=csvRow.split(",");
        int uid=Integer.parseInt(parts[0]);
        String uname=parts[1];
        String upassword=parts[2];
        // 如果有借閱書籍，則解析書籍 ID
        List<Book> borrowedBooks = new ArrayList<>();
        if(parts.length > 3 && parts[3].length() > 0){
            borrowedBooks = Arrays.stream(parts[3].split(";"))
                            .map(Integer::parseInt)
                            .map(Library::getBookById)
                            .filter(book -> book != null)
                            .collect(Collectors.toList());
            return new User(uid, uname, upassword, borrowedBooks);
        }
        // 如果沒有借閱書籍，則直接建立 User 物件
        return new User(uname, upassword);

    }
}