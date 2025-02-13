package org.trinity.pruebatecnica.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.trinity.pruebatecnica.model.Cliente;
import org.trinity.pruebatecnica.repository.IClienteRepository;
import org.trinity.pruebatecnica.service.impl.ClienteService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private IClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
    }

    @Test
    void testGuardarCliente() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente nuevoCliente = clienteService.save(cliente);

        assertNotNull(nuevoCliente);
        assertEquals("Juan", nuevoCliente.getNombre());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testBuscarClientePorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> clienteEncontrado = clienteService.findById(1L);

        assertTrue(clienteEncontrado.isPresent());
        assertEquals("Juan", clienteEncontrado.get().getNombre());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testEliminarCliente() {
        when(clienteRepository.existsById(1L)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(1L);

        assertDoesNotThrow(() -> clienteService.delete(1L));
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
