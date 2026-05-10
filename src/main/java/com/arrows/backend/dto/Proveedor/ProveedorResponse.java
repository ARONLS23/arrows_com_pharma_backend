package com.arrows.backend.dto.Proveedor;

import com.arrows.backend.enums.EstadoEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProveedorResponse {
    private Long id;
    private String nombre;
    private String ruc;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;
    private EstadoEnum estado;
    private int totalProductos;
}
