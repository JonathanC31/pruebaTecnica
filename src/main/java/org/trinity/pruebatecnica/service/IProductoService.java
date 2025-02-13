package org.trinity.pruebatecnica.service;

import org.trinity.pruebatecnica.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    Producto update(Long id, Producto producto);
    Producto cambiarEstado(Long id, String estado);
    void delete(Long id);
}
