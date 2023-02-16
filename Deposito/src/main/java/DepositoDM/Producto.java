
package DepositoDM;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Producto implements Serializable, Strategy {
    private String idProducto;
    private String nombre;
    private int cantidad;
    private String marca;
    private String idProveedor;
    
    public Producto(String Nombre, int Cantidad, String Marca, String IdProveedor) {
        this.idProducto = UUID.randomUUID().toString().toUpperCase().substring(0 , 4);
        this.nombre = Nombre;
        this.cantidad = Cantidad;
        this.marca = Marca;
        this.idProveedor = IdProveedor;
    }

    public Producto(String UID, String Name, int Cant, String Marc, String UIDP) {
        this.idProducto = UID;
        this.nombre = Name;
        this.cantidad = Cant;
        this.marca = Marc;
        this.idProveedor = UIDP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.cantidad = Cantidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String Marca) {
        this.marca = Marca;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setId(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return " " + idProducto + " - " +  nombre + " - " + cantidad + " - " + marca+ " - " +idProveedor;
    }
    
     @Override
    public boolean equals(Object Obj) {
        Producto NewMercaderia = (Producto) Obj;
        return (NewMercaderia.nombre==nombre) && (NewMercaderia.cantidad==cantidad)&&((NewMercaderia.marca==marca));
    }


    @Override
    public void insertar() throws SQLException {
        Connection conn = Singleton.getInstance();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO producto values (?,?,?,?,?)");
        preparedStatement.setString(1,idProducto);
        preparedStatement.setString(2,nombre);
        preparedStatement.setInt(3,cantidad);
        preparedStatement.setString(4,marca);
        preparedStatement.setString(5,idProveedor);
        preparedStatement.executeUpdate();
    }
}
