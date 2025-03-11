package pe.jsaire.springbootcrud.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.jsaire.springbootcrud.dto.ProductoRequestDTO;
import pe.jsaire.springbootcrud.dto.ProductoResponseDTO;
import pe.jsaire.springbootcrud.entities.Producto;
import pe.jsaire.springbootcrud.exceptions.ProductoNotFoundException;
import pe.jsaire.springbootcrud.repositories.ProductoRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

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
        Producto producto = repository.findById(id).orElseThrow(() -> new ProductoNotFoundException("No se encontro el id " + id));
        return convertToDTO(producto);
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
    public ProductoResponseDTO findByNombre(String nombre) {
        return convertToDTO(repository.findByNombre(nombre));
    }

    @Override
    public Page<ProductoResponseDTO> findAll(String field, Boolean desc, Integer page) {

        Sort sorting = Sort.by("nombre");

        if (Objects.nonNull(field)) {
            switch (field) {
                case "precio" -> sorting = Sort.by("precio");
                default -> throw new IllegalArgumentException("Invalid field" + field);
            }
        }

        return (desc) ? this.repository.findAll(PageRequest.of(page, PAGE_SIZE, sorting.descending())).map(this::convertToDTO) :
                this.repository.findAll(PageRequest.of(page, PAGE_SIZE, sorting.ascending())).map(this::convertToDTO);
    }


    private ProductoResponseDTO convertToDTO(Producto producto) {
        return modelMapper.map(producto, ProductoResponseDTO.class);
    }

    private Producto convertToEntity(ProductoRequestDTO productoRequestDTO) {
        return modelMapper.map(productoRequestDTO, Producto.class);
    }


}
