package org.trinity.pruebatecnica.service;

import org.trinity.pruebatecnica.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    void update(Cliente cliente, Long id);
    void delete(Long id);
}
