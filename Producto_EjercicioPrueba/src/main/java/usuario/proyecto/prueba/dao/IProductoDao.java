package usuario.proyecto.prueba.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import usuario.proyecto.prueba.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto,Long>{	
}
