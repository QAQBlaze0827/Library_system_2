import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Book {

    private String bookName;
    private int bookID;
    private boolean isBorrowed;
    private Integer borrowedByUid; // 借閱者的 UID
    private String borrowdate;


    // public void addBook(String bookName, int bookID) {
    //     this.bookName = bookName;
    //     this.bookID = bookID;
    //     this.isBorrowed = false;
    // }
    public Book(String bookName, int bookID, boolean isBorrowed) {
        this.bookName = bookName;
        this.bookID = bookID;
        this.isBorrowed = isBorrowed;
        this.borrowedByUid=-1 ; // 借閱者的 UID
        this.borrowdate = "";
    }
    // public void deleteBook(int bookid){
    //     if (bookid == this.bookID){
    //         this.bookName = null;
    //         this.bookID = 0;
    //         this.isBorrowed = false;
    //         this.borrowedByUid = null;
    //     }
    //     else{
    //         System.out.println("Book ID not found");
    //     }
    // }
    public String getBookName(){
        return bookName;
    }
    public int getBookID(){
        return bookID;
    }
    public boolean getIsBorrowed(){
        return isBorrowed;
    }
    //這邊我加的
    public int getBorrowedByUid(){
        return borrowedByUid;
    }
    public String getBorrowdate(){
        return borrowdate;
    }
    public void setBorrowed(boolean isBorrowed){
        this.isBorrowed = isBorrowed;
    }
    public void setBorrowedByUid(Integer borrowedByUid){
        this.borrowedByUid = borrowedByUid;
    }
    public void setBorrowDate(String borrowdate){
        this.borrowdate = borrowdate;
    }
    public static List<Book> loadBooksFromFile(String path) {
        List<Book> allBooks = new ArrayList<>();
        File file = new File(path);
    
        if (!file.exists()) {
            return allBooks; // 如果檔案不存在，返回空列表
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 跳過標題行和無效行
                if (!line.startsWith("BookName")) {
                    Book book = Book.fromCsvRow(line);
                    if (book != null) {
                        allBooks.add(book);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return allBooks;
    }
    
    public void borrowBook(int userid){
        if(!isBorrowed){
            this.isBorrowed = true;
            this.borrowedByUid=userid;
            this.borrowdate = LocalDate.now().toString(); // 设置借书日期
            System.out.println(LocalDate.now().toString());
            System.out.println("Book is borrowed successfully");

            // 更新 CSV 檔案
            updateBookInCsv("allBook.csv");

           
        }
        else{
            System.out.println("Book is already borrowed");
            
        }
        // this.isBorrowed = true;
    }
    public void returnBook(int userid){
        if(isBorrowed == true){
            
            this.borrowedByUid = -1;
            this.isBorrowed = false;
            this.borrowdate = ""; // 清空借书日期
            System.out.println("Book is returned successfully");
            updateBookInCsv("allBook.csv");
        }
        else{
            System.out.println("Book is not borrowed");
        }
        // this.isBorrowed = false;
    }
    public  void updateBookInCsv(String path){
        // String path = "allBook.csv";
        List<Book> allBooks = loadBooksFromFile(path);
    
        // 更新內存中的書籍資料
        for (Book book : allBooks) {
            if (book.getBookID() == this.bookID) {
                if(this.isBorrowed == false){
                    book.setBorrowed(false)  ;
                    book.setBorrowedByUid(this.borrowedByUid);
                    
                    break;
                }
                else{
                    book.setBorrowed(true)  ;
                    book.setBorrowedByUid(this.borrowedByUid);
                    book.setBorrowDate(this.borrowdate);
                    break;
                }
                
            }
        }
    
        // 將更新後的資料寫回到 CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            // 寫入標題行
            bw.write("BookName,BookId,IsBorrowed,BorrowedByUid");
            bw.newLine();
    
            // 寫入更新後的書籍資料
            for (Book book : allBooks) {
                bw.write(book.toCsvRow());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //新增資料進檔案(改格式)
    public String toCsvRow() {
        return bookName + "," + bookID + "," + isBorrowed + "," + (borrowedByUid == null ? "" : borrowedByUid + "," + borrowdate);
    }
    
    //解析 CSV 文件中的行
    public static Book fromCsvRow(String csvRow) {
        if (csvRow == null || csvRow.trim().isEmpty()) {
            System.err.println("跳過空行");
            return null; // 空行直接跳過
        }
    
        String[] parts = csvRow.split(",");
        if (parts.length < 4) { // 檢查是否有至少 3 個欄位
            System.err.println("行格式錯誤，跳過：" + csvRow);
            return null;
        }
    
        try {
            String bookName = parts[0];
            int bookID = Integer.parseInt(parts[1]);
            boolean isBorrowed = Boolean.parseBoolean(parts[2]);
            Integer borrowedByUid = (parts.length > 3 && !parts[3].isEmpty()) ? Integer.parseInt(parts[3]) : null;
            String borrowdate = parts.length > 4 ? parts[4] : ""; // 默认空字符串
    
            Book book = new Book(bookName, bookID, isBorrowed);
            book.setBorrowedByUid(borrowedByUid);
            book.setBorrowDate(borrowdate);
            return book;
        } catch (NumberFormatException e) {
            System.err.println("行資料解析錯誤，跳過：" + csvRow);
            return null; // 資料解析失敗時跳過該行
        }
    }
    
}
