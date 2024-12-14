import java.util.concurrent.atomic.AtomicInteger;
class User {
    private static final AtomicInteger nextUid = new AtomicInteger(1); // 靜態變數，負責自動生成 UID
    private final int uid;
    private final String uname;
    private final String upassword;

    // 建構子:自動生成 UID
    public User( String uname, String upassword) {
        this.uid = nextUid.getAndIncrement(); // 自動生成 UID 並遞增
        this.uname = uname;
        this.upassword = upassword;
    }
    // 建構子:從CSV加載用戶時使用
    public User(int uid, String uname, String upassword) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
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
    // 將 User 轉為 CSV 行
    public String toCsvRow() {
        return uid + "," + uname + "," + upassword;
    }
    //解析 CSV 文件中的行
    public static User fromCsvRow(String csvRow){
        String[] parts=csvRow.split(",");
        int uid=Integer.parseInt(parts[0]);
        String uname=parts[1];
        String upassword=parts[2];
        return new User(uid, uname, upassword); // 修正後

    }
}