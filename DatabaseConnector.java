import java.sql.*;  
public class DatabaseConnector {  
    private static final String JDBC_URL = "jdbc:mysql://localhost/Java_project_login";  
    private static final String USERNAME = "username";  
    private static final String PASSWORD = "password";  
    public static Connection connect() throws SQLException {  
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);  
        } catch (ClassNotFoundException e) {  
            throw new SQLException("Database driver not found", e);  
        }  
    }  
}  