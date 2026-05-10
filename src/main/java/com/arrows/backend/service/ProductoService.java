package com.arrows.backend.service;

import com.arrows.backend.dto.Producto.ProductoRequest;
import com.arrows.backend.dto.Producto.ProductoResponse;

import java.util.List;

public interface ProductoService {
    List<ProductoResponse> findAll();

    List<ProductoResponse> findByCategoria(Long categoriaId);

    ProductoResponse findById(Long id);

    ProductoResponse create(ProductoRequest request);

    ProductoResponse update(Long id, ProductoRequest request);

    void delete(Long id);
}
