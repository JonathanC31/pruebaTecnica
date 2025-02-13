package org.trinity.pruebatecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trinity.pruebatecnica.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
