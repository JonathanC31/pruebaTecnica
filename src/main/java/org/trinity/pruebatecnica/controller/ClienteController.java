package org.trinity.pruebatecnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.trinity.pruebatecnica.model.Cliente;
import org.trinity.pruebatecnica.service.impl.ClienteService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente")
    public List<Cliente> findALL(){
        return clienteService.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Optional<Cliente> findById(@PathVariable Long id){
        return clienteService.findById(id);
    }

    @PostMapping("/cliente")
    public Cliente save(@RequestBody Cliente cliente){
        return clienteService.save(cliente);}


    @PutMapping("/cliente/{id}")
    public void update(@RequestBody Cliente cliente, @PathVariable Long id){
        clienteService.update(cliente, id);
    }


    @DeleteMapping("/cliente/{id}")
    public void delete(@PathVariable Long id){
        clienteService.delete(id);
    }
}
