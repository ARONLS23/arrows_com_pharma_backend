package com.arrows.backend.service.impl;

import com.arrows.backend.domain.Categoria;
import com.arrows.backend.dto.Categoria.CategoriaRequest;
import com.arrows.backend.dto.Categoria.CategoriaResponse;
import com.arrows.backend.enums.EstadoEnum;
import com.arrows.backend.repository.CategoriaRepository;
import com.arrows.backend.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaResponse> findAll() {
        return categoriaRepository.findByEstado(EstadoEnum.ACTIVO)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CategoriaResponse findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        return toResponse(categoria);
    }

    @Override
    public CategoriaResponse create(CategoriaRequest request) {
        if (categoriaRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new RuntimeException("Ya existe una categoría con el nombre: " + request.getNombre());
        }
        Categoria categoria = Categoria.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .estado(EstadoEnum.ACTIVO)
                .build();
        return toResponse(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaResponse update(Long id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
        return toResponse(categoriaRepository.save(categoria));
    }

    @Override
    public void delete(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        categoria.setEstado(EstadoEnum.INACTIVO); // Soft delete
        categoriaRepository.save(categoria);
    }

    private CategoriaResponse toResponse(Categoria c) {
        return CategoriaResponse.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .descripcion(c.getDescripcion())
                .estado(c.getEstado())
                .build();
    }
}
