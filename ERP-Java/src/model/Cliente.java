package model;

import java.time.LocalDate;

public class Cliente extends Persona {
    private LocalDate fechaAlta;

    public Cliente(String nombre, String email) {
        super(nombre, email);
        fechaAlta = LocalDate.now();
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "fechaAlta=" + fechaAlta +
                ", id=" + getId() +
                ", nombre='" +getNombre() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
