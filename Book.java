public class Book {

    private String bookName;
    private int bookID;
    private boolean isBorrowed;

    // public void addBook(String bookName, int bookID) {
    //     this.bookName = bookName;
    //     this.bookID = bookID;
    //     this.isBorrowed = false;
    // }
    public Book(String bookName, int bookID, boolean isBorrowed) {
        this.bookName = bookName;
        this.bookID = bookID;
        this.isBorrowed = isBorrowed;
    }
    public void deleteBook(int bookid){
        if (bookid == this.bookID){
            this.bookName = null;
            this.bookID = 0;
            this.isBorrowed = false;
        }
        else{
            System.out.println("Book ID not found");
        }
    }
    public String getBookName(){
        return bookName;
    }
    public int getBookID(){
        return bookID;
    }
    public boolean getIsBorrowed(){
        return isBorrowed;
    }
    public void borrowBook(){
        this.isBorrowed = true;
    }
    //新增資料進檔案(改格式)
    public String toCsvRow(){
        
        return bookName+","+bookID+","+isBorrowed;

    }
    //解析 CSV 文件中的行
    public static Book fromCsvRow(String csvRow){
        String[] parts=csvRow.split(",");
        String bookName=parts[0];
        int bookID=Integer.parseInt(parts[1]);
        boolean isBorrowed=Boolean.parseBoolean(parts[2]);
        return new Book(bookName, bookID, isBorrowed); // 修正後

    }
}
