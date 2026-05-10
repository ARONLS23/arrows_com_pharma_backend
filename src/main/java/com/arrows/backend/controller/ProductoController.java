package com.arrows.backend.controller;

import com.arrows.backend.dto.ApiResponse;
import com.arrows.backend.dto.Producto.ProductoRequest;
import com.arrows.backend.dto.Producto.ProductoResponse;
import com.arrows.backend.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductoResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok("Productos obtenidos", productoService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Producto encontrado", productoService.findById(id)));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<ApiResponse<List<ProductoResponse>>> findByCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(ApiResponse.ok("Productos por categoría", productoService.findByCategoria(categoriaId)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductoResponse>> create(@Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Producto creado", productoService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoResponse>> update(
            @PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Producto actualizado", productoService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Producto eliminado (soft delete)", null));
    }
}
