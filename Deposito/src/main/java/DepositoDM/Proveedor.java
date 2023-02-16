
package DepositoDM;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Proveedor implements Serializable, Strategy {
    
    private String id;
    private String nombre;

    public Proveedor(String Nombre) {
        this.id = UUID.randomUUID().toString().toUpperCase().substring(0 , 4);
        this.nombre = Nombre;
    }

    public Proveedor(String Id,String Nombre) {
        this.id = Id;
        this.nombre = Nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getId() {
        return id;
    }
    

    @Override
    public String toString() {
        return "\n ID: " + id + " NOMBRE: "+ nombre ;
    }


    @Override
    public void insertar() throws SQLException {
        Connection conn = Singleton.getInstance();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO proveedor values (?,?)");
        preparedStatement.setString(1,id);
        preparedStatement.setString(2,nombre);
        preparedStatement.executeUpdate();
    }
}
