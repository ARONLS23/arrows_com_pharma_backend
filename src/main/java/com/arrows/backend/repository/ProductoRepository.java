package com.arrows.backend.repository;

import com.arrows.backend.domain.Producto;
import com.arrows.backend.enums.EstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByEstado(EstadoEnum estado);

    List<Producto> findByCategoriaIdAndEstado(Long categoriaId, EstadoEnum estado);
}
