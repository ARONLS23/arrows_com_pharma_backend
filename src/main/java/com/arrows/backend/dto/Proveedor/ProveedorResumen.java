package com.arrows.backend.dto.Proveedor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProveedorResumen {
    private Long id;
    private String nombre;
    private String ruc;
}
