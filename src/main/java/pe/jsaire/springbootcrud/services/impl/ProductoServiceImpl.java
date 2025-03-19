package pe.jsaire.springbootcrud.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.jsaire.springbootcrud.dto.ProductoRequestDTO;
import pe.jsaire.springbootcrud.dto.ProductoResponseDTO;
import pe.jsaire.springbootcrud.entities.Producto;
import pe.jsaire.springbootcrud.exceptions.ProductoException;
import pe.jsaire.springbootcrud.repositories.ProductoRepository;
import pe.jsaire.springbootcrud.services.IProductoService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements IProductoService {

    private final static Integer PAGE_SIZE = 5;
    private final ProductoRepository repository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public ProductoResponseDTO save(ProductoRequestDTO productoRequestDTO) {

        if (repository.existsByNombre(productoRequestDTO.getNombre())) {
            throw new ProductoException("El nombre '" + productoRequestDTO.getNombre() + "' ya está registrado.");
        }
        if (repository.existsBySku(productoRequestDTO.getSku())) {
            throw new ProductoException("El SKU '" + productoRequestDTO.getSku() + "' ya está registrado.");
        }

        Producto producto = new Producto();
        producto.setNombre(productoRequestDTO.getNombre());
        producto.setDescripcion(productoRequestDTO.getDescripcion());
        producto.setSku(productoRequestDTO.getSku());
        producto.setPrecio(productoRequestDTO.getPrecio());
        producto.setStockActual(productoRequestDTO.getStockActual());
        producto.setUnidadMedida(productoRequestDTO.getUnidadMedida());
        producto.setEstado(true);

        repository.save(producto);
        return convertToDTO(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO findById(Long id) {
        Producto producto = repository.findById(id).orElseThrow(
                () -> new ProductoException("No se encontro el id " + id));
        return convertToDTO(producto);
    }

    @Override
    @Transactional
    public ProductoResponseDTO update(Long id, ProductoRequestDTO requestDTO) {

        Producto producto = repository.findById(id).orElseThrow(
                () -> new ProductoException("No se encontró el producto con id " + id));

        if (!producto.getNombre().equals(requestDTO.getNombre()) &&
                repository.existsByNombre(requestDTO.getNombre())) {
            throw new ProductoException("El nombre del producto " + requestDTO.getNombre() + " ya existe");
        }

        if (!producto.getSku().equals(requestDTO.getSku()) &&
                repository.existsBySku(requestDTO.getSku())) {
            throw new ProductoException("El SKU " + requestDTO.getSku() + " ya existe");
        }

        producto.setNombre(requestDTO.getNombre());
        producto.setDescripcion(requestDTO.getDescripcion());
        producto.setPrecio(requestDTO.getPrecio());
        producto.setSku(requestDTO.getSku());
        producto.setStockActual(requestDTO.getStockActual());
        producto.setUnidadMedida(requestDTO.getUnidadMedida());

        Producto productoActualizado = repository.save(producto);

        return convertToDTO(productoActualizado);

    }



    @Override
    @Transactional
    public void deleteById(Long id) {
        Producto producto = repository.findById(id).orElseThrow(() -> new ProductoException("No se encontro el id " + id));
        repository.delete(producto);
    }


    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO findBySku(String sku) {
        Producto producto = repository.findBySku(sku);
        return convertToDTO(producto);
    }

    @Override
    public boolean existsBySku(String sku) {
        return false;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ProductoResponseDTO> findAll(String busquedad, String field, Boolean desc, Integer page) {

        String sortBy = (field != null) ? field : "id";

        List<String> validFields = List.of("precio", "nombre", "id");

        if (!validFields.contains(sortBy)) {
            throw new IllegalArgumentException(" Este campo no exites: " + field + " !!");
        }

        Sort sort = Sort.by(sortBy);
        if (Boolean.TRUE.equals(desc)) {
            sort = sort.descending();
        }

        int pageNumber = (page != null && page >= 0) ? page : 0;

        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy));

        Page<Producto> productos;

        if (busquedad != null && !busquedad.isBlank()) {
            productos = repository.findByNombreContainingIgnoreCase(busquedad, pageable);
        } else {
            productos = repository.findAll(pageable);
        }

        return productos.map(this::convertToDTO);
    }




    private ProductoResponseDTO convertToDTO(Producto producto) {
        return modelMapper.map(producto, ProductoResponseDTO.class);
    }

    private Producto convertToEntity(ProductoRequestDTO productoRequestDTO) {
        return modelMapper.map(productoRequestDTO, Producto.class);
    }


}
