package com.arrows.backend.controller;

import com.arrows.backend.dto.ApiResponse;
import com.arrows.backend.dto.Categoria.CategoriaRequest;
import com.arrows.backend.dto.Categoria.CategoriaResponse;
import com.arrows.backend.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok("Categorías obtenidas", categoriaService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Categoría encontrada", categoriaService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoriaResponse>> create(@Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Categoría creada", categoriaService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaResponse>> update(
            @PathVariable Long id, @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Categoría actualizada", categoriaService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Categoría eliminada (soft delete)", null));
    }
}
