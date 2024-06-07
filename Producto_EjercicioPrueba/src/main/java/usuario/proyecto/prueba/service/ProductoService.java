package usuario.proyecto.prueba.service;

import java.util.List;
import java.util.Optional;

import usuario.proyecto.prueba.entity.Producto;

public interface ProductoService {

	Producto createProducto(Producto producto);
    List<Producto> getAllProductos();
    Optional<Producto> getProductoById(Long id);
    Producto updateProducto(Long id,Producto producto);
    void deleteProducto(Long id);
}
