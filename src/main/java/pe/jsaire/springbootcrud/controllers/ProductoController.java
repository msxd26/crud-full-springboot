package pe.jsaire.springbootcrud.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.jsaire.springbootcrud.dto.ApiResponse;
import pe.jsaire.springbootcrud.dto.ProductoRequestDTO;
import pe.jsaire.springbootcrud.dto.ProductoResponseDTO;
import pe.jsaire.springbootcrud.exceptions.ProductoException;
import pe.jsaire.springbootcrud.services.IProductoService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {


    private final IProductoService productoService;

    @GetMapping("/all")
    public ResponseEntity<Page<ProductoResponseDTO>> findAll(@RequestParam(required = false) String busquedad,
                                                             @RequestParam(required = false) String field,
                                                             @RequestParam(required = false)  Boolean desc,
                                                             @RequestParam(required = false)  Integer page) {
        return ResponseEntity.ok(productoService.findAll(busquedad,field, desc, page));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO requestDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        ProductoResponseDTO updatedProduct = productoService.update(id, requestDTO);

        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> guardarProducto(@Valid @RequestBody ProductoRequestDTO requestDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        ProductoResponseDTO savedProduct = productoService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<?> buscarPorSku(@RequestParam String sku) {
        if (productoService.existsBySku(sku)) {
            throw new ProductoException(" No existe este sku :" + sku + " !!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findBySku(sku));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private ResponseEntity<ApiResponse<?>> validation(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, "Errores de validación", errores));
    }


}
