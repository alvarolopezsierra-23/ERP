package com.erp.erp.controller;

import com.erp.erp.model.Producto;
import com.erp.erp.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        return productoService.agregarProducto(producto);
    }

    @GetMapping("/{id}")
    public Producto buscarProductoPorId(@PathVariable int id) {
        return productoService.buscarProducto(id);
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable int id) {
        productoService.eliminarProducto(id);
    }

    @PutMapping("/{id}")
    public Producto actualizarStock(@PathVariable int id, @RequestParam int cantidad) {
        return productoService.actualizarStock(id, cantidad);
    }
}
