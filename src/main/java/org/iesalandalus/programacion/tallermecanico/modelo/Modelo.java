//Adrián Visiedo Rodriguez

package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;

public class Modelo {

    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public void comenzar(){
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }
    public void terminar(){
        System.out.print("El modelo ha finalizado.");
    }
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(cliente);
    }
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }
    public void insertar(Revision revision) throws OperationNotSupportedException {
        revisiones.insertar(revision);
    }
    public Cliente buscar(Cliente cliente){
        return  clientes.buscar(cliente);
    }
    public Vehiculo buscar(Vehiculo vehiculo){
        return  vehiculos.buscar(vehiculo);
    }
    public Revision buscar(Revision revision){
        return  revisiones.buscar(revision);
    }
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }
    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        revisiones.buscar(revision).anadirHoras(horas);
    }
    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        revisiones.buscar(revision).anadirPrecioMaterial(precioMaterial);
    }
    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        revisiones.buscar(revision).cerrar(fechaFin);
    }
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        clientes.borrar(cliente);
    }
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.borrar(vehiculo);
    }
    public void borrar(Revision revision) throws OperationNotSupportedException {
        revisiones.borrar(revision);
    }
    public List<Cliente> getClientes(){
        return clientes.get();
    }
    public List<Vehiculo> getVehiculos(){
        return vehiculos.get();
    }
    public List<Revision> getRevisiones(){
        return revisiones.get();
    }
    public List<Revision> getRevisiones(Cliente cliente){
        return revisiones.get(cliente);
    }
    public List<Revision> getRevisiones(Vehiculo vehiculo){
        return revisiones.get(vehiculo);
    }
}
