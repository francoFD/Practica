package DepositoDM;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controlador {

    public static void main(String[] args) {
        //Valor login
        int a;

        //Objeto Vista
        Vista vista = new Vista();

        //Objeto conexion
        Connection conn=null;
        try {
            conn = Singleton.getInstance();
        } catch (SQLException e) {
            new RuntimeException(e);
        }

        do {
            a = vista.login();

            ////////////////////////////////////////////////////////////////
            ///////////////////////NUEVO PROVEEDOR//////////////////////////
            ////////////////////////////////////////////////////////////////
            if (a == 1) {
                //Resivo los atributos
                String nameprovee = vista.añadirProveedor();
                Proveedor prov = new Proveedor(nameprovee);

                if (conn != null) {
                    //Envio el objeto para ser almacenado con SQL
                    try {
                        prov.insertar();
                    } catch (SQLException e) {
                        new RuntimeException(e);
                        conn = null;
                    }
                }

                if(conn==null){
                    //Envio el objeto para ser almacenado en txt
                    Modelo.guardarProveedores(prov);
                }
            }

            ////////////////////////////////////////////////////////////////
            /////////////////////// CUENTAS REGISTRADAS ////////////////////
            ////////////////////////////////////////////////////////////////

            if (a == 2) {
                ArrayList<Proveedor> MostrarProveedores = new ArrayList();
                //Añado los objetos que tiene el array retornando al array de mostrarProveedores
                MostrarProveedores.addAll(Modelo.viewProveedor());
                //Recorro el array y Resivo el id de la cuenta elegida
                int cuentaVip = vista.verArrayProveedores(MostrarProveedores);

                Proveedor cuenta = MostrarProveedores.get(cuentaVip);
                String id = cuenta.getId();

                ////////////////////////////////////////////////////////////////
                //////////////////////////NUEVO PRODUCTO////////////////////////
                ////////////////////////////////////////////////////////////////
                ArrayList<String> atributos = new ArrayList();
                //Resivo los atributos
                atributos = vista.añadirProducto();
                //Instancio el producto
                String nbre = atributos.get(0);
                int cantidad = Integer.parseInt(atributos.get(1));
                String marca = atributos.get(2);
                Producto producto = new Producto(nbre, cantidad, marca, id);

                if(conn==null){
                    //Envio el producto para ser almacenado en txt
                    Modelo.guardarProductos(producto);
                }else{
                    //SQL Producto
                    try {
                        producto.insertar();
                    } catch (SQLException e) {
                        new RuntimeException(e);
                    }
                }
            }

            ////////////////////////////////////////////////////////////////
            ///////////////////////VISTASO AL DEPOSITO//////////////////////
            ////////////////////////////////////////////////////////////////
            if (a == 3) {
                ArrayList<Producto> mostrarProductos = new ArrayList();
                //Añado los objetos que tiene el array retorndo al array de mostrarProductos
                mostrarProductos.addAll(Modelo.verProductos());
                //Envio el array a la vista para imprimirlo por consola
                vista.verProducto(mostrarProductos);
            }

            ////////////////////////////////////////////////////////////////
            /////////////////////////VER PROVEEDORES////////////////////////
            ////////////////////////////////////////////////////////////////
            if (a == 4) {
                ArrayList proveedores = new ArrayList();
                //Añado los objetos que tiene el array retorndo al array de proveedores
                proveedores.addAll(Modelo.viewProveedor());
                //Recorro el array y luego lo imprimo desde la vista
                vista.verCuentas(proveedores);
            }

            if (a == 5) {
                Modelo modelo = new Modelo();
                modelo.forever(a);
                modelo.infinity(a);
            }

        } while (a != 0);
    }
}
