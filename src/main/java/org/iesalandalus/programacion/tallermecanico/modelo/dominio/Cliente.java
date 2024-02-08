package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {
    private static final String ER_NOMBRE = "^[A-Z][a-z]*(?: [A-Z][a-z]*)*[a-z]$";
    private static final String ER_DNI = "\\d{8}[A-HJ-NP-TV-Z]$";
    private static final String ER_TELEFONO = "^\\d{9}$";

    private String nombre;
    private String dni;
    private String telefono;
    Cliente(String nombre, String dni, String telefono){
        setDni(dni);
        setNombre(nombre);
        setTelefono(telefono);
    }
    public Cliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "No es posible copiar un cliente nulo.");
        this.dni = cliente.dni;
        this.nombre = cliente.nombre;
        this.telefono= cliente.telefono;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        Objects.requireNonNull("El nombre no puede ser nulo.");
        if (ER_NOMBRE.matches(nombre)){
            this.nombre = nombre;
        }else{
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }

    }
    public String getDni() {
        return dni;
    }
    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        if (ER_DNI.matches(dni)){
            if (comprobarLetraDni(dni)){
                this.dni = dni;
            }
        }else {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
    }
    private boolean comprobarLetraDni(String dni) {
        String numerosDNI = dni.substring(0, 8);
        String letraDNI = dni.substring(8).toUpperCase();

        int numDNI;
        numDNI = Integer.parseInt(numerosDNI);
        char letraCalculada = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(numDNI % 23);

        return letraCalculada == letraDNI.charAt(0);
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        if (ER_TELEFONO.matches(telefono)){
            this.telefono = telefono;
        }else {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
    }
    public static Cliente get(String dni){
        Cliente cliente = new Cliente(null,dni,null);
        if (cliente.comprobarLetraDni(dni)) {
            return new Cliente("Francisco", dni, "655348725");
        } else {
            throw new IllegalArgumentException("El DNI proporcionado no es válido");
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(getNombre(), cliente.getNombre()) && Objects.equals(getDni(), cliente.getDni()) && Objects.equals(getTelefono(), cliente.getTelefono());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getDni(), getTelefono());
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }
}