package org.trinity.pruebatecnica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.pruebatecnica.model.EstadoCuenta;
import org.trinity.pruebatecnica.model.Producto;
import org.trinity.pruebatecnica.repository.IProductoRepository;
import org.trinity.pruebatecnica.service.IProductoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        if (producto.getCliente() == null || producto.getCliente().getId() == null) {
            throw new RuntimeException("El producto debe estar vinculado a un cliente existente.");
        }
        return productoRepository.save(producto);
    }


    @Override
    public Producto update(Long id, Producto producto) {
        return productoRepository.findById(id).map(existingProducto -> {
            existingProducto.setTipoCuenta(producto.getTipoCuenta());
            existingProducto.setNumeroCuenta(producto.getNumeroCuenta());
            existingProducto.setEstado(producto.getEstado());
            existingProducto.setSaldo(producto.getSaldo());
            existingProducto.setExentoGMF(producto.getExentoGMF());
            existingProducto.setCliente(producto.getCliente());
            return productoRepository.save(existingProducto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Producto cambiarEstado(Long id, String estado) {
        return productoRepository.findById(id).map(existingProducto -> {
            try {
                EstadoCuenta nuevoEstado = EstadoCuenta.valueOf(estado.toUpperCase());
                existingProducto.setEstado(nuevoEstado);
                return productoRepository.save(existingProducto);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Estado inválido: " + estado);
            }
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }


    @Override
    public void delete(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }
}
