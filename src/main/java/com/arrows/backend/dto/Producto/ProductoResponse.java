package com.arrows.backend.dto.Producto;

import com.arrows.backend.dto.Proveedor.ProveedorResumen;
import com.arrows.backend.enums.EstadoEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private Long categoriaId;
    private String categoriaNombre;
    private EstadoEnum estado;
    private List<ProveedorResumen> proveedores;
}
