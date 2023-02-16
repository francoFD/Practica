package DepositoDM;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Vista {

    public int login() {
        //Scanner leer
        Scanner Leer = new Scanner(System.in);
        boolean repetir = true;
        int log = 0;

        do{
            //Menu
            System.out.println("                      ");
            System.out.println("----------------------");
            System.out.println("    Deposito Login    ");
            System.out.println("----------------------");
            System.out.println("1= Registrar Proveedor");
            System.out.println("2= Ingresar productos");
            System.out.println("3= Ver Deposito");
            System.out.println("4= Ver Proveedores");
            System.out.println("5= Actualizar");
            System.out.println("0= Salir");
            System.out.println("----------------------");
            try {
                log = Leer.nextInt();
                repetir = false;
            } catch (InputMismatchException e) {
                Leer.nextLine();
                System.out.println("Error, has introducido mal el dato");
                System.out.println("Vuelve a intentarlo");
            }
        } while(repetir);

        return log;
    }

    public ArrayList añadirProducto() {
        //Scanners String e Int
        Scanner leer = new Scanner(System.in);
        Scanner leerNumero = new Scanner(System.in);

        boolean bucle = true;
        String nomb;

        do {
            System.out.println("Ingrese el nombre del producto");
            nomb = leer.nextLine().trim();

            if (nomb.isEmpty()) {
                System.out.println("Error vuelva a intentarlo");
            }else {
                bucle = false;
            }
        }while (bucle);

        boolean repetir = true;
        int num = 0;
        do{
            System.out.println("Ingrese las unidades");
            try {
                num = leerNumero.nextInt();
                repetir = false;
            } catch (InputMismatchException e) {
                leerNumero.nextLine();
                System.out.println("Error, has introducido mal el dato");
            }
        } while(repetir);

        boolean bandera = true;
        String marc;

        do {
            System.out.println("Ingrese la marca");
            marc = leer.nextLine().trim();

            if (marc.isEmpty()) {
                System.out.println("Error vuelva a intentarlo");
            }else {
                bandera = false;
            }
        }while (bandera);

        //Conversion de int a string
        String numero = Integer.toString(num);

        //ArrayList de tipo String 
        ArrayList<String> atributos = new ArrayList();
        atributos.add(nomb);
        atributos.add(numero);
        atributos.add(marc);

        return (atributos);
    }

    public void verProducto(ArrayList mostrarProductos) {
        //Recorro el array y lo imprimo por pantalla
        System.out.println("---------------------------------");
        System.out.println("            Productos            ");
        System.out.println("---------------------------------");
        for (int i = 0; i < mostrarProductos.size(); i++) {
            System.out.println(" " + i + "_" + mostrarProductos.get(i));
            System.out.println("                        ");
        }
        System.out.println("---------------------------------");
    }

    public String añadirProveedor() {
        //Scanners String e Int
        Scanner lector = new Scanner(System.in);

        String provee;
        boolean bandera = true;

        do {
            System.out.println("Ingrese el nombre");
            provee = lector.nextLine().trim();

            if (provee.isEmpty()) {
                System.out.println("Error vuelva a intentarlo");
            }else {
                bandera = false;
            }
        }while (bandera);

        return provee;
    }

    public int verArrayProveedores(ArrayList<Proveedor> mostrarProveedores) {
        //Scanner leer
        Scanner Leer = new Scanner(System.in);

        //Recorro el array y lo imprimo por pantalla
        System.out.println("-------------------------");
        System.out.println("       Proveedores       ");
        System.out.println("-------------------------");

        int i;
        for (i = 0; i < mostrarProveedores.size(); i++) {
            System.out.println(" " + i + "_" + mostrarProveedores.get(i));
            System.out.println("                                       ");
        }
        System.out.println("-------------------------");
        System.out.println("                         ");
        System.out.println("-------------------------");

        boolean repetir = true;
        int us=0;

        do{
            System.out.println("Indique su usuario");
            try {
                us = Leer.nextInt();
                repetir = false;
            } catch (InputMismatchException e) {
                Leer.nextLine();
                System.out.println("Error, has introducido mal el dato");
            }
        } while(repetir);
        
        return us;
    }

    public void verCuentas(ArrayList<Proveedor>mostrarProveedores){
        System.out.println("-------------------------------------------------");
        System.out.println("              Proveedores Registrados            ");
        System.out.println("-------------------------------------------------");
        for(int i = 0; i<mostrarProveedores.size(); i++){
            System.out.println(mostrarProveedores.get(i));
        }
        System.out.println("                                                 ");
        System.out.println("-------------------------------------------------");
    }


}
