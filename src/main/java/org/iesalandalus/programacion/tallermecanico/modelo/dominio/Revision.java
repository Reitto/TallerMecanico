//Adrian visiedo rodriguez

package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5F;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }

    public Revision(Revision revision) {
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        cliente = new Cliente(revision.cliente);
        vehiculo = revision.vehiculo;
        fechaInicio = revision.fechaInicio;
        fechaFin = revision.fechaFin;
        horas = revision.horas;
        precioMaterial = revision.precioMaterial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) throws OperationNotSupportedException {
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        this.horas = this.horas + horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial = this.precioMaterial + precioMaterial;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (estaCerrada()) {
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        }
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
        float precio = 0;
        if (estaCerrada()) {
            precio = PRECIO_MATERIAL * precioMaterial + ((horas * PRECIO_HORA) + (getDias() * PRECIO_DIA));
        }
        return precio;
    }

    private float getDias() {
        return Period.between(fechaInicio, fechaFin).getDays();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Revision revision)) return false;
        return Objects.equals(getCliente(), revision.getCliente()) && Objects.equals(getVehiculo(), revision.getVehiculo()) && Objects.equals(getFechaInicio(), revision.getFechaInicio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCliente(), getVehiculo(), getFechaInicio());
    }

    @Override
    public String toString() {
        String cadena = null;
        if (!estaCerrada()){
            cadena = String.format("%s - %s: (%s - ), %d horas, %.2f € en material", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), horas, getPrecioMaterial());
        }
        if (estaCerrada()){
            cadena = String.format("%s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), fechaFin.format(FORMATO_FECHA), horas, getPrecioMaterial(), getPrecio());
        }
        return cadena;
    }
}
