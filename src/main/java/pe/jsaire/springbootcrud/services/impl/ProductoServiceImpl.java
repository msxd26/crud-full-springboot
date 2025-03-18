package pe.jsaire.springbootcrud.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.jsaire.springbootcrud.dto.ProductoRequestDTO;
import pe.jsaire.springbootcrud.dto.ProductoResponseDTO;
import pe.jsaire.springbootcrud.entities.Producto;
import pe.jsaire.springbootcrud.exceptions.ProductoNotFoundException;
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
    public ProductoResponseDTO save(ProductoRequestDTO productoRequestDTO) {

        Producto producto = convertToEntity(productoRequestDTO);

        repository.save(producto);

        return convertToDTO(producto);
    }

    @Override
    public ProductoResponseDTO findById(Long id) {
        Producto producto = repository.findById(id).orElseThrow(
                () -> new ProductoNotFoundException("No se encontro el id " + id));
        return convertToDTO(producto);
    }

    @Override
    public ProductoResponseDTO update(Long id, ProductoRequestDTO requestDTO) {

        Producto producto = repository.findById(id).orElseThrow(
                () -> new ProductoNotFoundException("No se encontro el id " + id));

        var producotoModificado = producto.toBuilder().nombre(requestDTO.getNombre())
                .descripcion(requestDTO.getDescripcion())
                .precio(requestDTO.getPrecio())
                .sku(requestDTO.getSku())
                .stockActual(requestDTO.getStockActual())
                .unidadMedida(requestDTO.getUnidadMedida())
                .build();
        return convertToDTO(this.repository.save(producotoModificado));
    }

    @Override
    public void deleteById(Long id) {
        Producto producto = repository.findById(id).orElseThrow(() -> new ProductoNotFoundException("No se encontro el id " + id));
        repository.delete(producto);
    }

    @Override
    public Boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }


    @Override
    public ProductoResponseDTO findBySku(String sku) {
        Producto producto = repository.findBySku(sku);
        return convertToDTO(producto);
    }

    @Override
    public Boolean existsByNombre(String nombre) {
        return repository.existsByNombre(nombre);
    }


    @Override
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
