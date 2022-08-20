
package practicaclass;

import java.sql.Connection;
import java.sql.*;

public class PracticaClass {
    
    public static void main(String[] args) {
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root",""); 
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM alumno");
            ResultSet rs = prepare.executeQuery();
            
            while(rs.next()){
                System.out.println(rs.getString("Apellido"));
                System.out.println(rs.getInt("id"));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
