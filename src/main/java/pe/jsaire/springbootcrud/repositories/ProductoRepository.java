package pe.jsaire.springbootcrud.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.jsaire.springbootcrud.entities.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    boolean existsBySku(String sku);

    Producto findBySku(String sku);

    boolean existsByNombre(String nombre);

    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

}
