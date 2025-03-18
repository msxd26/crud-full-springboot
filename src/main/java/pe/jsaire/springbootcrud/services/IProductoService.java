package pe.jsaire.springbootcrud.services;

import org.springframework.data.domain.Page;
import pe.jsaire.springbootcrud.dto.ProductoRequestDTO;
import pe.jsaire.springbootcrud.dto.ProductoResponseDTO;


public interface IProductoService {

    ProductoResponseDTO save(ProductoRequestDTO requestDTO);

    ProductoResponseDTO findById(Long id);

    ProductoResponseDTO update(Long id, ProductoRequestDTO requestDTO);

    void deleteById(Long id);

    Boolean existsBySku(String sku);

    ProductoResponseDTO findBySku(String sku);

    Boolean existsByNombre(String nombre);

    //ProductoResponseDTO findByNombre(String nombre);

    Page<ProductoResponseDTO> findAll(String busquedad,String field, Boolean desc, Integer page);

    //Page<ProductoResponseDTO>  findByNombreContainingIgnoreCase(String nombre, Boolean desc, Integer page);
}
