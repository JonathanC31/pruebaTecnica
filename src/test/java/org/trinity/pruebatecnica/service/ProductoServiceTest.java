package org.trinity.pruebatecnica.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.trinity.pruebatecnica.model.Producto;
import org.trinity.pruebatecnica.repository.IProductoRepository;
import org.trinity.pruebatecnica.service.impl.ProductoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private IProductoRepository productoRepository;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto();
        producto.setId(1L);
        producto.setSaldo(1000.0);
    }

    @Test
    void testFindById() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> foundProducto = productoService.findById(1L);

        assertTrue(foundProducto.isPresent());
        assertEquals(1000.0, foundProducto.get().getSaldo());
    }

    @Test
    void testSaveProducto() {
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto savedProducto = productoService.save(new Producto());

        assertNotNull(savedProducto);
        assertEquals(1L, savedProducto.getId());
    }

    @Test
    void testDeleteProducto() {
        when(productoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productoRepository).deleteById(1L);

        assertDoesNotThrow(() -> productoService.delete(1L));
        verify(productoRepository, times(1)).deleteById(1L);
    }
}
