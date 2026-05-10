package com.arrows.backend.service.impl;

import com.arrows.backend.domain.Categoria;
import com.arrows.backend.domain.Producto;
import com.arrows.backend.dto.Producto.ProductoRequest;
import com.arrows.backend.dto.Producto.ProductoResponse;
import com.arrows.backend.enums.EstadoEnum;
import com.arrows.backend.repository.CategoriaRepository;
import com.arrows.backend.repository.ProductoRepository;
import com.arrows.backend.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<ProductoResponse> findAll() {
        return productoRepository.findByEstado(EstadoEnum.ACTIVO)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public List<ProductoResponse> findByCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaIdAndEstado(categoriaId, EstadoEnum.ACTIVO)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public ProductoResponse findById(Long id) {
        return toResponse(findProductoOrThrow(id));
    }

    @Override
    public ProductoResponse create(ProductoRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + request.getCategoriaId()));

        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .categoria(categoria)
                .estado(EstadoEnum.ACTIVO)
                .build();

        return toResponse(productoRepository.save(producto));
    }

    @Override
    public ProductoResponse update(Long id, ProductoRequest request) {
        Producto producto = findProductoOrThrow(id);
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + request.getCategoriaId()));

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoria(categoria);

        return toResponse(productoRepository.save(producto));
    }

    @Override
    public void delete(Long id) {
        Producto producto = findProductoOrThrow(id);
        producto.setEstado(EstadoEnum.INACTIVO);
        productoRepository.save(producto);
    }

    private Producto findProductoOrThrow(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    private ProductoResponse toResponse(Producto p) {
        return ProductoResponse.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .precio(p.getPrecio())
                .stock(p.getStock())
                .categoriaId(p.getCategoria().getId())
                .categoriaNombre(p.getCategoria().getNombre())
                .estado(p.getEstado())
                .build();
    }
}
