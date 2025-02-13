package org.trinity.pruebatecnica.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.trinity.pruebatecnica.model.Producto;
import org.trinity.pruebatecnica.service.IProductoService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Test
    void testGetAllProductos() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
        when(productoService.findAll()).thenReturn(Collections.singletonList(new Producto()));

        mockMvc.perform(get("/api/productos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
