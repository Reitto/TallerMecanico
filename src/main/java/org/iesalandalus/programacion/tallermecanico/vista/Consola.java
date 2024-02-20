package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/M/yyyy";

    private Consola() {

    }

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        System.out.println("_".repeat(mensaje.length()));
    }

    public static void mostrarMenu() {
        mostrarCabecera("La aplicación tiene como cometido realizar las siguientes acciones, elija a su conveniencia.");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        Opcion opcion = null;
        do {
            try {
                opcion = Opcion.get(leerEntero("Introduce una opcion:"));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion==null);
        return opcion;
    }

    private static int leerEntero(String mensaje) {
        System.out.println(mensaje);
        return Entrada.entero();
    }

    private static float leerReal(String mensaje) {
        System.out.println(mensaje);
        return Entrada.real();
    }

    private static String leerCadena(String mensaje) {
        System.out.println(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formateo = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        LocalDate fecha = null;
        do {
            String cadenaFecha = leerCadena(mensaje);
            try {
                fecha = LocalDate.parse(cadenaFecha, formateo);
            } catch (DateTimeParseException e) {
                System.out.println("La fecha introducida no es válida, vuelva a intentarlo");
            }
        }while (fecha==null);
        return fecha;
    }

    public static Cliente leerCliente() {
        String nombre = Consola.leerCadena("Introduce un nombre para el cliente:");
        String dni = Consola.leerCadena("Introduce un dni para el cliente:");
        String telefono = Consola.leerCadena("Introduce un teléfono para el cliente:");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni() {
        String dni = Consola.leerCadena("Introduce un dni para el cliente:");
        return Cliente.get(dni);
    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre para el cliente:");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono para el cliente:");
    }

    public static Vehiculo leerVehiculo() {
        String marca = leerCadena("Introduce una marca para el vehiculo:");
        String modelo = leerCadena("Introduce el modelo para el vehiculo:");
        String matricula = leerCadena("Introduce la matrícula del vehiculo:");
        return new Vehiculo(marca, modelo, matricula );
    }

    public static Vehiculo leerVehiculoMatricula() {
        String matricula = Consola.leerCadena("Introduce una matrícula para el vehiculo:");
        return Vehiculo.get(matricula);
    }

    public static Revision leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio:");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    public static int leerHoras() {
        return leerEntero("Introduce el número de horas:");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Introduce el precio del material:");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre:");
    }
}
