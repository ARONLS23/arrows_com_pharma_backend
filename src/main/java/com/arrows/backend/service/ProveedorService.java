package com.arrows.backend.service;

import com.arrows.backend.dto.Proveedor.ProveedorRequest;
import com.arrows.backend.dto.Proveedor.ProveedorResponse;

import java.util.List;

public interface ProveedorService {
    List<ProveedorResponse> findAll();

    ProveedorResponse findById(Long id);

    ProveedorResponse findByRuc(String ruc);

    ProveedorResponse create(ProveedorRequest request);

    ProveedorResponse update(Long id, ProveedorRequest request);

    void delete(Long id);
}
