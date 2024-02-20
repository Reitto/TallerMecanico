package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {
    INSERTAR_CLIENTE(1, "Insertar cliente"),
    BUSCAR_CLIENTE(2, "Buscar cliente"),
    BORRAR_CLIENTE(3, "Borrar cliente"),
    LISTAR_CLIENTES(4, "Listar clientes"),
    MODIFICAR_CLIENTE(5, "Modificar clientes"),
    INSERTAR_VEHICULO(6, "Insertar vehiculo"),
    BUSCAR_VEHICULO(7, "Buscar vehiculo"),
    BORRAR_VEHICULO(8, "Borrar vehiculo"),
    LISTAR_VEHICULOS(9, "Listar vehículos"),
    INSERTAR_REVISION(10, "Insertar revision"),
    BUSCAR_REVISION(11, "Buscar revision"),
    BORRAR_REVISION(12, "Borrar revision"),
    LISTAR_REVISIONES(13, "Listar revisiones"),
    LISTAR_REVISIONES_CLIENTE(14, "Listar revisiones cliente"),
    LISTAR_REVISIONES_VEHICULO(15, "Listar revisiones vehiculo"),
    ANADIR_HORAS_REVISION(16, "Añadir horas revision"),
    ANADIR_PRECIO_MATERIAL_REVISION(17, "Añadir precio material revision"),
    CERRAR_REVISION(18, "cerrar revision"),
    SALIR(19, "Salir");
    private final int numeroOpcion;
    private final String mensaje;
    private static final Map<Integer, Opcion> Opciones = new HashMap<>() {
        static {
            for (Opcion opcion : Opciones.values()) {
                Opciones.put(opcion.numeroOpcion, opcion);
            }
        }
    };

    Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return numeroOpcion > 0 && numeroOpcion < 20;
    }

    public static Opcion get(int numeroOpcion) {
        Opcion[] opciones = Opcion.values();
        if (!esValida(numeroOpcion)) {
            throw new IllegalArgumentException("La opción pasada por parámetro no es válida");
        }
        return opciones[numeroOpcion - 1];
    }

    @Override
    public String toString() {
        return String.format("Opcion: numeroOpcion=%s, mensaje=%s", this.numeroOpcion, this.mensaje);
    }
}
