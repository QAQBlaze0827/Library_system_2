class User {
    private final int uid;
    private final String uname;
    private final String upassword;

    // 建構子
    public User(int uid, String uname, String upassword) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
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