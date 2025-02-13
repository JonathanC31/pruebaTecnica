package org.trinity.pruebatecnica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.pruebatecnica.model.Cliente;
import org.trinity.pruebatecnica.repository.IClienteRepository;
import org.trinity.pruebatecnica.service.IClienteService;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return(List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) { return clienteRepository.findById(id); }


    @Override
    public Cliente save(Cliente cliente) { return clienteRepository.save(cliente); }

    @Override
    public void update(Cliente cliente, Long id) {
        Optional<Cliente>us=clienteRepository.findById(id);
        if(us.isPresent()){
            Cliente clienteUpdate=us.get();
            clienteUpdate.setId(cliente.getId());
            clienteUpdate.setTipoid(cliente.getTipoid());
            clienteUpdate.setNumerodoc(cliente.getNumerodoc());
            clienteUpdate.setNombre(cliente.getNombre());
            clienteUpdate.setApellido(cliente.getApellido());
            clienteUpdate.setTelefono(cliente.getTelefono());
            clienteUpdate.setEmail(cliente.getEmail());
            clienteUpdate.setFechaDeNacimiento(cliente.getFechaDeNacimiento());
            clienteRepository.save(clienteUpdate);
        }else {
            System.out.println("No existe el usuario");
        }
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}

