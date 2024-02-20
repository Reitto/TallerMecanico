package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.util.SortedMap;

public class Consola {
    private  static final String CADENA_FORMATO_FECHA = "dd/M/yyyy";
    private Consola(){

    }
    public static void mostrarCabecera(String mensaje){
        String subrayado = "_".repeat(mensaje.length());
        System.out.println(mensaje);
        System.out.println(subrayado);
    }
    public static void mostrarMenu(){
        mostrarCabecera("La aplicación tiene como cometido realizar las siguientes acciones, elija a su conveniencia.");
        for (Opcion opcion: Opcion.values()){
            System.out.println(opcion);
        }
    }
    public static Opcion elegirOpcion(){
        return Opcion.BORRAR_CLIENTE;
    }
    private static int leerEntero(String mensaje){
        System.out.println(mensaje);
        return Entrada.entero();
    }
    private static float leerReal(String mensaje){
        System.out.println(mensaje);
        return Entrada.real();    }
    private static String leerCadena(String mensaje){
        System.out.println(mensaje);
        return Entrada.cadena();
    }
    private static LocalDate leerFecha(String mensaje){
        return;
    }
    public static Cliente leerCliente(){

    }
    public static Cliente leerClienteDni(){

    }
    public static String leerNuevoNombre(){

    }
    public static String leerNuevoTelefono(){

    }
    public static Vehiculo leerVehiculo(){

    }
    public static Vehiculo leerVehiculoMatricula(){

    }
    public static Revision leerRevision(){

    }
    public static int leerHoras(){

    }
    public static float leerPrecioMaterial(){

    }
    public static LocalDate leerFechaCierre(){

    }
}
