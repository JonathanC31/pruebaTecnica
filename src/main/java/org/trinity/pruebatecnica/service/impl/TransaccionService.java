package org.trinity.pruebatecnica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trinity.pruebatecnica.model.*;
import org.trinity.pruebatecnica.repository.IProductoRepository;
import org.trinity.pruebatecnica.repository.ITransaccionRepository;
import org.trinity.pruebatecnica.service.ITransaccionService;

@Service
public class TransaccionService implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional
    public Transaccion consignar(Long cuentaId, Double monto) {
        Producto cuenta = productoRepository.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cuenta.setSaldo(cuenta.getSaldo() + monto);
        productoRepository.save(cuenta);

        Transaccion transaccion = new Transaccion(null, TipoTransaccion.CONSIGNACION, monto, null, cuenta, null);
        return transaccionRepository.save(transaccion);
    }

    @Override
    @Transactional
    public Transaccion retirar(Long cuentaId, Double monto) {
        Producto cuenta = productoRepository.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        if (cuenta.getSaldo() < monto) {
            throw new RuntimeException("Fondos insuficientes");
        }

        cuenta.setSaldo(cuenta.getSaldo() - monto);
        productoRepository.save(cuenta);

        Transaccion transaccion = new Transaccion(null, TipoTransaccion.RETIRO, monto, cuenta, null, null);
        return transaccionRepository.save(transaccion);
    }

    @Override
    @Transactional
    public Transaccion transferir(Long cuentaOrigenId, Long cuentaDestinoId, Double monto) {
        Producto cuentaOrigen = productoRepository.findById(cuentaOrigenId)
                .orElseThrow(() -> new RuntimeException("Cuenta de origen no encontrada"));

        Producto cuentaDestino = productoRepository.findById(cuentaDestinoId)
                .orElseThrow(() -> new RuntimeException("Cuenta de destino no encontrada"));

        if (cuentaOrigen.getSaldo() < monto) {
            throw new RuntimeException("Fondos insuficientes en la cuenta de origen");
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        productoRepository.save(cuentaOrigen);
        productoRepository.save(cuentaDestino);

        Transaccion transaccion = new Transaccion(null, TipoTransaccion.TRANSFERENCIA, monto, cuentaOrigen, cuentaDestino, null);
        return transaccionRepository.save(transaccion);
    }
}
