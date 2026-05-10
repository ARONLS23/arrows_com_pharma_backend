package com.arrows.backend.dto.Categoria;

import com.arrows.backend.enums.EstadoEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private EstadoEnum estado;
}
