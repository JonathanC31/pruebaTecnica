package org.trinity.pruebatecnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.trinity.pruebatecnica.model.Transaccion;
import org.trinity.pruebatecnica.service.ITransaccionService;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private ITransaccionService transaccionService;

    @PostMapping("/consignar")
    public Transaccion consignar(@RequestParam Long cuentaId, @RequestParam Double monto) {
        return transaccionService.consignar(cuentaId, monto);
    }

    @PostMapping("/retirar")
    public Transaccion retirar(@RequestParam Long cuentaId, @RequestParam Double monto) {
        return transaccionService.retirar(cuentaId, monto);
    }

    @PostMapping("/transferir")
    public Transaccion transferir(@RequestParam Long cuentaOrigenId, @RequestParam Long cuentaDestinoId, @RequestParam Double monto) {
        return transaccionService.transferir(cuentaOrigenId, cuentaDestinoId, monto);
    }
}
