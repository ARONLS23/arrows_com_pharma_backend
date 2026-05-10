package com.arrows.backend.repository;

import com.arrows.backend.domain.Proveedor;
import com.arrows.backend.enums.EstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByEstado(EstadoEnum estado);

    Optional<Proveedor> findByRuc(String ruc);

    boolean existsByRuc(String ruc);
}
