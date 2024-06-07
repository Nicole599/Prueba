package usuario.proyecto.prueba.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import usuario.proyecto.prueba.entity.Producto;
import usuario.proyecto.prueba.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
    private ProductoService productoService;
	 @PostMapping
	    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
	        Producto createdProducto = productoService.createProducto(producto);
	        return ResponseEntity.status(201).body(createdProducto);
	    }

	    @GetMapping
	        public List<Producto> getAllProducto() {
	        return productoService.getAllProductos();
	    }
	    
	  
	    @GetMapping("/{id}")
	    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
	    	return productoService.getProductoById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.status(404).build());
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Void> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
	        Producto productoActualizado = productoService.updateProducto(id, producto);
	        if (productoActualizado != null) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.status(404).build();
	        }
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
	        productoService.deleteProducto(id);
	        return ResponseEntity.noContent().build();
	    }
	   
}
