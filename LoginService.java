import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
public class LoginService {  
    public boolean authenticateUser(String username, String password) {  
        String query = "SELECT * FROM users WHERE username =? AND password = ?";  
        try (Connection connection = DatabaseConnector.connect();  
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {  
            preparedStatement.setString(1, username);  
            preparedStatement.setString(2, password);  
            try (ResultSet resultSet = preparedStatement.executeQuery()) {  
                return resultSet.next();  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();   
            return false;  
        }  
    }  
}  