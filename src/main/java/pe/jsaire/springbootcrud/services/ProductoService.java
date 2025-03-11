package pe.jsaire.springbootcrud.services;

import org.springframework.data.domain.Page;
import pe.jsaire.springbootcrud.dto.ProductoRequestDTO;
import pe.jsaire.springbootcrud.dto.ProductoResponseDTO;


public interface ProductoService {

    ProductoResponseDTO save(ProductoRequestDTO requestDTO);

    ProductoResponseDTO findById(Long id);

    Boolean existsBySku(String sku);

    ProductoResponseDTO findBySku(String sku);

    Boolean existsByNombre(String nombre);

    ProductoResponseDTO findByNombre(String nombre);

    Page<ProductoResponseDTO> findAll(String field, Boolean desc, Integer page);

}
