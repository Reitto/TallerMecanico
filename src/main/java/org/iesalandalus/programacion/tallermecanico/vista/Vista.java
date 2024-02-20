package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo");
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("El programa ha finalizado.");
    }

    private void ejecutar(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_CLIENTE -> insertarCliente();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_CLIENTE -> borrarCliente();
            case LISTAR_CLIENTES -> listarClientes();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case LISTAR_VEHICULOS -> listarVehiculos();
            case INSERTAR_REVISION -> insertarRevision();
            case BUSCAR_REVISION -> buscarRevision();
            case BORRAR_REVISION -> borrarRevision();
            case LISTAR_REVISIONES -> listarRevisiones();
            case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
            case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
            case CERRAR_REVISION -> cerrar();
            case SALIR -> salir();
            default -> throw new IllegalArgumentException("La opción introducida no es válida");
        }
    }

    private void insertarCliente() {
        Consola.mostrarCabecera("Inserta cliente");
        try {
            controlador.insertar(Consola.leerCliente());
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.print(e.getMessage());
        }
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera("Inserta vehículo");
        try {
            controlador.insertar(Consola.leerVehiculo());
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarRevision() {
        Consola.mostrarCabecera("Inserta revisión");
        try {
            controlador.insertar(Consola.leerRevision());
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("Buscar cliente");
        try {
            System.out.println(controlador.buscar(Consola.leerClienteDni()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar vehículo");
        try {
            System.out.println(controlador.buscar(Consola.leerVehiculoMatricula()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("Buscar revisión");
        try {
            System.out.println(controlador.buscar(Consola.leerRevision()));
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void modificarCliente() {
        Consola.mostrarCabecera("Modifique el cliente");
        boolean modificador = false;
        try {
            modificador = controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        if (modificador) {
            System.out.println("El cliente se ha modificado correctamente.");
        }
    }

    private void anadirHoras() {
        Consola.mostrarCabecera("Añadir horas");
        try {
            controlador.anadirHoras(Consola.leerRevision(),Consola.leerHoras());
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void anadirPrecioMaterial() {
        Consola.mostrarCabecera("Añade precio material");
        try {
            controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cerrar() {
        Consola.mostrarCabecera("Cerrar la revisión");
        try {
            controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCliente() {
        Consola.mostrarCabecera("Borrar cliente");
        try {
            controlador.borrar(Consola.leerClienteDni());
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera("Borrar vehículo");
        try {
            controlador.borrar(Consola.leerVehiculo());
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarRevision() {
        Consola.mostrarCabecera("Borrar revisión");
        try {
            controlador.borrar(Consola.leerRevision());
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarClientes() {
        Consola.mostrarCabecera("Listar clientes");
        List<Cliente> listaClientes = controlador.getClientes();
        if (listaClientes.isEmpty()) {
            System.out.println("No se han encontrado clientes.");
        } else {
            System.out.println(listaClientes);
        }
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("Listar vehículo");
        List<Vehiculo> listaVehiculos = controlador.getVehiculos();
        if (listaVehiculos.isEmpty()) {
            System.out.println("No se han encontrado vehículos.");
        } else {
            System.out.println(listaVehiculos);
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("Listar Revisiones");
        List<Revision> listaRevisiones = controlador.getRevisiones();
        if (listaRevisiones.isEmpty()) {
            System.out.println("No se ha encontrado ninguna revisión.");
        } else {
            System.out.println(listaRevisiones);
        }
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("Listar revisiones cliente");
        List<Revision> listaRevisionesCliente = controlador.getRevisiones(Consola.leerClienteDni());
        if (listaRevisionesCliente.isEmpty()) {
            System.out.println("No hay ninguna revisión para dicho cliente.");
        } else {
            System.out.println(listaRevisionesCliente);
        }
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera("Listar revisiones vehiculo");
        List<Revision> listaRevisionesVehiculo = controlador.getRevisiones(Consola.leerVehiculoMatricula());
        if (listaRevisionesVehiculo.isEmpty()) {
            System.out.println("No hay ninguna revisión para dicho vehiculo.");
        } else {
            System.out.println(listaRevisionesVehiculo);
        }
    }

    private void salir() {
        Consola.mostrarCabecera("Salir");
        controlador.terminar();
    }
}
