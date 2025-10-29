package com.erp.erp.repository;

import com.erp.erp.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Venta, Integer> {
}
