package usuario.proyecto.prueba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usuario.proyecto.prueba.dao.IProductoDao;
import usuario.proyecto.prueba.entity.Producto;

@Service
public class ProductoServiceImpl implements  ProductoService{

	 @Autowired
	    private IProductoDao productoDao;
	 
	@Override
	public Producto createProducto(Producto producto) {
		 // Calcular el valor de compra
        double valorCompra = producto.getPrecio() * producto.getCantidad();
        
        // Aplicar descuento si el valor total de compra es superior a $50
        if (valorCompra > 50) {
            double descuento = valorCompra * 0.1; // 10% de descuento
            valorCompra -= descuento;
            producto.setDescuento(descuento);
        } else {
            producto.setDescuento(0.0);
        }

        // Calcular el IVA
        double iva = valorCompra * 0.12; // 12% de IVA

        // Calcular el TOTAL a pagar
        double total = valorCompra + iva;

        producto.setValorCompra(valorCompra);
        producto.setIva(iva);
        producto.setTotal(total);

        return productoDao.save(producto);
	}

	@Override
	public List<Producto> getAllProductos() {
		 return productoDao.findAll();
	}

	@Override
	public Optional<Producto> getProductoById(Long id) {
		 return productoDao.findById(id);
	}

	@Override
	public Producto updateProducto(Long id, Producto producto) {
        Producto productoExistente = productoDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
        return productoDao.save(productoExistente);
	}

	@Override
	public void deleteProducto(Long id) {
		productoDao.deleteById(id);
		
	}

}
