package DepositoDM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class Modelo {

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////PRODUCTOS TXT///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    public static void guardarProductos(Producto producto) {
        ArrayList<Producto> merc = new ArrayList();
        //Agrego todos los elementos de la colección especificada al final del Array
        merc.addAll(Modelo.verProductos());
        merc.add(producto);

        //Creo un flujo de salida a disco pasandole el nombre del archivo txt
        try ( ObjectOutputStream salidaDisco = new ObjectOutputStream(new FileOutputStream("Producto.txt"))) {
            //Escribo los objetos al flujo de salida y los guarda en un archivo en disco
            salidaDisco.writeObject(merc);
        } catch (FileNotFoundException e) {
            System.out.println(" El archivo de almacenamiento no encontrado");
        } catch (IOException e) {
            //Imprimo la excepcion
            e.printStackTrace();
            System.out.println(" Se encontro un error de entrada/salida. ");
        }
    }

    public static ArrayList<Producto> verProductos() {
        ArrayList<Producto> viewProductos = new ArrayList();

        Connection conn=null;
        try {
            conn = Singleton.getInstance();
        } catch (SQLException e) {
            new RuntimeException(e);
        }

        if(conn!=null){
            try {
                PreparedStatement consul = conn.prepareStatement("SELECT * FROM producto");
                ResultSet rs = consul.executeQuery();
                while (rs.next()){
                    String UID = rs.getString("idProducto");
                    String name = rs.getString("Nombre");
                    int cant = rs.getInt("Cantidad");
                    String marc = rs.getString("Marca");
                    String UIDP = rs.getString("idProv");

                    Producto p = new Producto(UID,name,cant,marc,UIDP);
                    viewProductos.add(p);
                }
            }catch (SQLException throwable){
                conn = null;
            }
        }

        if(conn==null){
            //Creo un flujo de entrada a disco para poder leer el archivo txt posteriormente
            try ( ObjectInputStream entradaDisco = new ObjectInputStream(new FileInputStream("Producto.txt"));) {
                viewProductos = (ArrayList<Producto>) entradaDisco.readObject();
            } catch (FileNotFoundException e) {
                System.out.println(" El archivo de almacenamiento no existe en el disco. ");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(" Se encontro un error de entrada/salida. ");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return viewProductos;
    }

    public void infinity(int go){
        //Vista
        Vista vista =  new Vista();

        //Conexion
        Connection conn=null;
        try {
            conn = Singleton.getInstance();
        } catch (SQLException e) {
            new RuntimeException(e);
        }

        if(go == 5){

            ArrayList<Producto> productsSQL = new ArrayList();

            try {
                PreparedStatement consul = conn.prepareStatement("SELECT * FROM producto");
                ResultSet rs = consul.executeQuery();
                while (rs.next()){
                    String UID = rs.getString("idProducto");
                    String name = rs.getString("Nombre");
                    int cant = rs.getInt("Cantidad");
                    String marc = rs.getString("Marca");
                    String UIDP = rs.getString("idProv");

                    Producto p = new Producto(UID,name,cant,marc,UIDP);
                    productsSQL.add(p);
                }
            }catch (SQLException throwable){
                throw new RuntimeException(throwable);
            }

            ArrayList<Producto> productsTXT = new ArrayList();

            try ( ObjectInputStream entradaDisco = new ObjectInputStream(new FileInputStream("Producto.txt"));) {
                productsTXT = (ArrayList<Producto>) entradaDisco.readObject();
            } catch (FileNotFoundException e) {
                System.out.println(" El archivo de almacenamiento no existe en el disco. ");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(" Se encontro un error de entrada/salida. ");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            boolean update = true;

            for (int i = 0; i <productsSQL.size() ; i++) {

                for (int j = 0; j <productsTXT.size() ; j++) {

                    if (productsSQL.get(i).getIdProducto().equals(productsTXT.get(j).getIdProducto())) {

                        productsTXT.remove(j);
                        try ( ObjectOutputStream salidaDisco = new ObjectOutputStream(new FileOutputStream("Producto.txt"))) {
                            //Escribo los objetos al flujo de salida y los guarda en un archivo en disco
                            salidaDisco.writeObject(productsTXT);
                        } catch (FileNotFoundException e) {
                            System.out.println(" El archivo de almacenamiento no encontrado");
                        } catch (IOException e) {
                            //Imprimo la excepcion
                            e.printStackTrace();
                            System.out.println(" Se encontro un error de entrada/salida. ");
                        }

                    }else {
                        update = false;
                    }
                }
            }

            if (update == false) {
                for (int k = 0; k<productsTXT.size() ; k++) {
                    try {
                        productsTXT.get(k).insertar();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                try ( ObjectOutputStream salidaDisco = new ObjectOutputStream(new FileOutputStream("Producto.txt"))) {
                    //Escribo los objetos al flujo de salida y los guarda en un archivo en disco
                    salidaDisco.writeObject(productsSQL);
                } catch (FileNotFoundException e) {
                    System.out.println(" El archivo de almacenamiento no encontrado");
                } catch (IOException e) {
                    //Imprimo la excepcion
                    e.printStackTrace();
                    System.out.println(" Se encontro un error de entrada/salida. ");
                }

            }

        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////  PROVEEDORES  ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    public static void guardarProveedores(Proveedor prov) {
        ArrayList<Proveedor> proves = new ArrayList();

        //Conexion txt para almacenar en el nuevo array
        proves.addAll(Modelo.viewProveedor());
        proves.add(prov);

        //Creo un flujo de salida a disco pasandole el nombre del archivo txt
        try ( ObjectOutputStream canalDeSalida = new ObjectOutputStream(new FileOutputStream("Proveedores.txt"));) {
            //Escribo los objetos al flujo de salida y los guarda en un archivo en disco
            canalDeSalida.writeObject(proves);
        } catch (FileNotFoundException e) {
            System.out.println(" El archivo de almacenamiento no encontrado");
        } catch (IOException e) {
            //Imprimo la excepcion
            e.printStackTrace();
            System.out.println(" Se encontro un error de entrada/salida");
        }

    }

    public static ArrayList<Proveedor> viewProveedor(){

        Connection conn=null;
        try {
            conn = Singleton.getInstance();
        } catch (SQLException e) {
             new RuntimeException(e);
        }

        ArrayList<Proveedor> datos = new ArrayList();

        if(conn!=null){
            try {
                PreparedStatement consul = conn.prepareStatement("SELECT * FROM proveedor");
                ResultSet rs = consul.executeQuery();
                while (rs.next()){
                    String UID = rs.getString("idProveedor");
                    String name = rs.getString("Nombre");
                    Proveedor p = new Proveedor(UID,name);
                    datos.add(p);
                }
            }catch (SQLException throwable){
                conn = null;
            }
        }

        if(conn==null){
            try ( ObjectInputStream canalDeEntrada = new ObjectInputStream(new FileInputStream("Proveedores.txt"));) {
                datos = (ArrayList<Proveedor>) canalDeEntrada.readObject();
            } catch (FileNotFoundException e) {
                System.out.println(" El archivo de almacenamiento no existe en el disco. ");
            } catch (ClassNotFoundException e) {
                System.out.println("Error");
                e.printStackTrace();
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return datos;
    }

    public void forever(int init){
        //Vista
        Vista vista =  new Vista();

        //Conexion
        Connection conn=null;
        try {
            conn = Singleton.getInstance();
        } catch (SQLException e) {
            new RuntimeException(e);
        }


        if(init == 5){

            ArrayList<Proveedor> SQL = new ArrayList();

            try {
                PreparedStatement consul = conn.prepareStatement("SELECT * FROM proveedor");
                ResultSet rs = consul.executeQuery();
                while (rs.next()){
                    String UID = rs.getString("idProveedor");
                    String name = rs.getString("Nombre");
                    Proveedor p = new Proveedor(UID,name);
                    SQL.add(p);
                }
            }catch (SQLException throwable){
                throw new RuntimeException(throwable);
            }

            ArrayList<Proveedor> TXT = new ArrayList();

            try ( ObjectInputStream canalDeEntrada = new ObjectInputStream(new FileInputStream("Proveedores.txt"))) {
                TXT = (ArrayList<Proveedor>) canalDeEntrada.readObject();
            } catch (FileNotFoundException e) {
                System.out.println(" El archivo de almacenamiento no existe en el disco. ");
            } catch (IOException e) {
                //Imprimo la excepcion
                e.printStackTrace();
                System.out.println(" Se encontro un error de entrada/salida");
            } catch (ClassNotFoundException e) {
                //Imprimo la excepcion
                e.printStackTrace();
            }

            boolean update = true;

            for (int i = 0; i <SQL.size() ; i++) {

                for (int j = 0; j <TXT.size() ; j++) {

                    if (SQL.get(i).getId().equals(TXT.get(j).getId())) {

                        TXT.remove(j);
                        try ( ObjectOutputStream canalDeSalida = new ObjectOutputStream(new FileOutputStream("Proveedores.txt"));) {
                            //Escribo los objetos al flujo de salida y los guarda en un archivo en disco
                            canalDeSalida.writeObject(TXT);
                        } catch (FileNotFoundException e) {
                            System.out.println(" El archivo de almacenamiento no encontrado");
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println(" Se encontro un error de entrada/salida");
                        }

                    }else {
                        update = false;
                    }
                }
            }

            if (update == false) {
                for (int k = 0; k <TXT.size() ; k++) {
                    try {
                        TXT.get(k).insertar();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                try ( ObjectOutputStream canalDeSalida = new ObjectOutputStream(new FileOutputStream("Proveedores.txt"));) {
                    //Escribo los objetos al flujo de salida y los guarda en un archivo en disco
                    canalDeSalida.writeObject(SQL);
                } catch (FileNotFoundException e) {
                    System.out.println(" El archivo de almacenamiento no encontrado");
                } catch (IOException e) {
                    //Imprimo la excepcion
                    e.printStackTrace();
                    System.out.println(" Se encontro un error de entrada/salida");
                }

            }

        }
    }
}
