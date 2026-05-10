package com.arrows.backend.service.impl;

import com.arrows.backend.domain.Proveedor;
import com.arrows.backend.dto.Proveedor.ProveedorRequest;
import com.arrows.backend.dto.Proveedor.ProveedorResponse;
import com.arrows.backend.enums.EstadoEnum;
import com.arrows.backend.repository.ProveedorRepository;
import com.arrows.backend.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {
    private final ProveedorRepository proveedorRepository;

    @Override
    public List<ProveedorResponse> findAll() {
        return proveedorRepository.findByEstado(EstadoEnum.ACTIVO)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public ProveedorResponse findById(Long id) {
        return toResponse(findProveedorOrThrow(id));
    }

    @Override
    public ProveedorResponse findByRuc(String ruc) {
        Proveedor proveedor = proveedorRepository.findByRuc(ruc)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con RUC: " + ruc));
        return toResponse(proveedor);
    }

    @Override
    public ProveedorResponse create(ProveedorRequest request) {
        if (proveedorRepository.existsByRuc(request.getRuc())) {
            throw new RuntimeException("Ya existe un proveedor con el RUC: " + request.getRuc());
        }
        Proveedor proveedor = Proveedor.builder()
                .nombre(request.getNombre())
                .ruc(request.getRuc())
                .contacto(request.getContacto())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .direccion(request.getDireccion())
                .estado(EstadoEnum.ACTIVO)
                .build();
        return toResponse(proveedorRepository.save(proveedor));
    }

    @Override
    public ProveedorResponse update(Long id, ProveedorRequest request) {
        Proveedor proveedor = findProveedorOrThrow(id);
        proveedor.setNombre(request.getNombre());
        proveedor.setRuc(request.getRuc());
        proveedor.setContacto(request.getContacto());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setEmail(request.getEmail());
        proveedor.setDireccion(request.getDireccion());
        return toResponse(proveedorRepository.save(proveedor));
    }

    @Override
    public void delete(Long id) {
        Proveedor proveedor = findProveedorOrThrow(id);
        proveedor.setEstado(EstadoEnum.INACTIVO);
        proveedorRepository.save(proveedor);
    }

    private Proveedor findProveedorOrThrow(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + id));
    }

    private ProveedorResponse toResponse(Proveedor p) {
        return ProveedorResponse.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .ruc(p.getRuc())
                .contacto(p.getContacto())
                .telefono(p.getTelefono())
                .email(p.getEmail())
                .direccion(p.getDireccion())
                .estado(p.getEstado())
                .totalProductos(p.getProductos().size())
                .build();
    }
}
