package org.trinity.pruebatecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trinity.pruebatecnica.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
