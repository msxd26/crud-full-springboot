package pe.jsaire.springbootcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.jsaire.springbootcrud.entities.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Boolean existsBySku(String sku);

    Producto findBySku(String sku);

    Boolean existsByNombre(String nombre);

    Producto findByNombre(String nombre);

}
