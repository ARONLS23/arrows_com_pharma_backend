package com.arrows.backend.service;

import com.arrows.backend.dto.Categoria.CategoriaRequest;
import com.arrows.backend.dto.Categoria.CategoriaResponse;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponse> findAll();

    CategoriaResponse findById(Long id);

    CategoriaResponse create(CategoriaRequest request);

    CategoriaResponse update(Long id, CategoriaRequest request);

    void delete(Long id);
}
