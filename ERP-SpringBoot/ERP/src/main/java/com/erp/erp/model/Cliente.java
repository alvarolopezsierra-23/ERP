package com.erp.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Persona {
    private LocalDate fechaAlta;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venta> ventas = new ArrayList<Venta>();


    public Cliente() {
    }

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
                "fechaAlta=" + getFechaAlta() +
                '}';
    }
}
