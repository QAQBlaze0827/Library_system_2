// Admin.java
public class Admin {
    private final String adminUser;
    private final String adminPassword;
    private final String adminName;

    public Admin() {
        this.adminUser = "admin";
        this.adminPassword = "password";
        this.adminName = "QAQBlaze";
    }
    //這邊是準備拿來新增管理員的方式
    // public void Addadmin(String adminUser, String adminPassword, String adminName) {
    //     this.adminUser = adminUser;
    //     this.adminPassword = adminPassword;
    //     this.adminName = adminName;
    // }
    public String getAdminUser() {
        return adminUser;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }
}
