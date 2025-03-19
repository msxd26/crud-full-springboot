package pe.jsaire.springbootcrud.services;

import org.springframework.data.domain.Page;
import pe.jsaire.springbootcrud.dto.ProductoRequestDTO;
import pe.jsaire.springbootcrud.dto.ProductoResponseDTO;


public interface IProductoService {

    ProductoResponseDTO save(ProductoRequestDTO requestDTO);

    ProductoResponseDTO findById(Long id);

    ProductoResponseDTO update(Long id, ProductoRequestDTO requestDTO);

    void deleteById(Long id);

    boolean existsBySku(String sku);

    ProductoResponseDTO findBySku(String sku);

    boolean existsByNombre(String nombre);

    Page<ProductoResponseDTO> findAll(String busquedad,String field, Boolean desc, Integer page);
    
    
}
