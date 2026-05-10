package com.arrows.backend.dto.Proveedor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProveedorRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150)
    private String nombre;

    @NotBlank(message = "El RUC es obligatorio")
    @Size(min = 11, max = 11, message = "El RUC debe tener 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "El RUC debe contener solo números")
    private String ruc;

    @Size(max = 100)
    private String contacto;

    @Size(max = 20)
    private String telefono;

    @Email(message = "Email inválido")
    @Size(max = 150)
    private String email;

    @Size(max = 255)
    private String direccion;
}
