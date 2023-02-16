package DepositoDM;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {
    private static Connection conexion;
    public static Connection getInstance() throws SQLException {
        if(conexion==null){
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/deposito","root","2003");
        }
        return conexion;
    }

}
