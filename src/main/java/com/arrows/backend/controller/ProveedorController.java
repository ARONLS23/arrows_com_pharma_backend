package com.arrows.backend.controller;

import com.arrows.backend.dto.ApiResponse;
import com.arrows.backend.dto.Proveedor.ProveedorRequest;
import com.arrows.backend.dto.Proveedor.ProveedorResponse;
import com.arrows.backend.service.ProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {
    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProveedorResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok("Proveedores obtenidos", proveedorService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProveedorResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Proveedor encontrado", proveedorService.findById(id)));
    }

    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<ApiResponse<ProveedorResponse>> findByRuc(@PathVariable String ruc) {
        return ResponseEntity.ok(ApiResponse.ok("Proveedor encontrado", proveedorService.findByRuc(ruc)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProveedorResponse>> create(@Valid @RequestBody ProveedorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Proveedor creado", proveedorService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProveedorResponse>> update(
            @PathVariable Long id, @Valid @RequestBody ProveedorRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Proveedor actualizado", proveedorService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        proveedorService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Proveedor eliminado", null));
    }
}
