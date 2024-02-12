//Adrián Visiedo Rodríguez
package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {
    private static final String ER_NOMBRE = "^[A-ZÁÉÍÓÚ][a-záéíóú]+(?: [A-ZÁÉÍÓÚ][a-záéíóú]*){0,3}";
    private static final String ER_DNI = "\\d{8}[TRWAGMYFPDXBNJZSQVHLCKE]";
    private static final String ER_TELEFONO = "^\\d{9}$";
    private String nombre;
    private String dni;
    private String telefono;

    Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "No es posible copiar un cliente nulo.");
        setDni(cliente.dni);
        setNombre(cliente.nombre);
        setTelefono(cliente.telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        this.dni = dni;
    }

    private boolean comprobarLetraDni(String dni) {
        String numerosDNI = dni.substring(0, 8);
        char letraDNI = dni.charAt(8);
        int numDNI;
        numDNI = Integer.parseInt(numerosDNI);
        char letraCalculada = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(numDNI % 23);
        return letraCalculada == letraDNI;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        } else {
            this.telefono = telefono;
        }
    }

    public static Cliente get(String dni) {
        return new Cliente("Adrian", dni, "123456789");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }
}

