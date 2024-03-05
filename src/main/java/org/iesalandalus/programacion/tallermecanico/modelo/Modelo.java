//Adrián Visiedo Rodriguez

package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;
    public Modelo(){ /* empty */ }
    public void comenzar() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }

    public void terminar() {
        System.out.print("El modelo ha finalizado.");
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        revisiones.insertar(new Revision(clientes.buscar(revision.getCliente()), vehiculos.buscar(revision.getVehiculo()), revision.getFechaInicio()));
    }

    public Cliente buscar(Cliente cliente) {
        return clientes.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        return revisiones.buscar(revision);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        revisiones.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        for (Revision revision : revisiones.get(cliente)) {
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Revision revision : revisiones.get(vehiculo)) {
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {
        List<Cliente> clientes1 = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            clientes1.add(new Cliente(cliente));
        }
        return clientes1;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    public List<Revision> getRevisiones() {
        List<Revision> revision1 = new ArrayList<>();
        for (Revision revision : revisiones.get()) {
            revision1.add(new Revision(revision));
        }
        return revision1;
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        List<Revision> revision2 = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)) {
            revision2.add(new Revision(revision));
        }
        return revision2;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        List<Revision> revision3 = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)) {
            revision3.add(new Revision(revision));
        }
        return revision3;
    }
}
