public class Book {

    private String bookName;
    private int bookID;
    private boolean isBorrowed;
    private Integer borrowedByUid; // 借閱者的 UID

    // public void addBook(String bookName, int bookID) {
    //     this.bookName = bookName;
    //     this.bookID = bookID;
    //     this.isBorrowed = false;
    // }
    public Book(String bookName, int bookID, boolean isBorrowed) {
        this.bookName = bookName;
        this.bookID = bookID;
        this.isBorrowed = isBorrowed;
        this.borrowedByUid=null ; // 借閱者的 UID
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
    public void setBorrowed(boolean isBorrowed){
        this.isBorrowed = isBorrowed;
    }
    public void setBorrowedByUid(Integer borrowedByUid){
        this.borrowedByUid = borrowedByUid;
    }
    public void borrowBook(int userid){
        if(!isBorrowed){
            this.isBorrowed = true;
            this.borrowedByUid=userid;
            System.out.println("Book is borrowed successfully");

        }
        else{
            System.out.println("Book is already borrowed");
        }
        // this.isBorrowed = true;
    }
    public void returnBook(){
        if(isBorrowed){
            this.isBorrowed = false;
            this.borrowedByUid = null;
            System.out.println("Book is returned successfully");
        }
        else{
            System.out.println("Book is not borrowed");
        }
        // this.isBorrowed = false;
    }
    //新增資料進檔案(改格式)
    public String toCsvRow(){
        
        return bookName+","+bookID+","+isBorrowed+","+(borrowedByUid==null?"":borrowedByUid);

    }
    //解析 CSV 文件中的行
    public static Book fromCsvRow(String csvRow){
        String[] parts=csvRow.split(",");
        String bookName=parts[0];
        int bookID=Integer.parseInt(parts[1]);
        boolean isBorrowed=Boolean.parseBoolean(parts[2]);
        Integer borrowedByUid=parts.length>3 && !parts[3].isEmpty()?Integer.parseInt(parts[3]):null;
        // return new Book(bookName, bookID, isBorrowed); // 修正後
        Book book = new Book(bookName, bookID,isBorrowed);
        // book.setBorrowed(isBorrowed);
        book.setBorrowedByUid(borrowedByUid);
        return book;

    }
}
