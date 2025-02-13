package org.trinity.pruebatecnica.service;

import org.trinity.pruebatecnica.model.Transaccion;

public interface ITransaccionService {
    Transaccion consignar(Long cuentaId, Double monto);
    Transaccion retirar(Long cuentaId, Double monto);
    Transaccion transferir(Long cuentaOrigenId, Long cuentaDestinoId, Double monto);
}
