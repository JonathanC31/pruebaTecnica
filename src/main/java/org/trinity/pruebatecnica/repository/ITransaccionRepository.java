package org.trinity.pruebatecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trinity.pruebatecnica.model.Transaccion;

public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {
}
