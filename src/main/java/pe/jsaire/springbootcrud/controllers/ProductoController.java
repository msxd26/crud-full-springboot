package pe.jsaire.springbootcrud.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.jsaire.springbootcrud.dto.ProductoRequestDTO;
import pe.jsaire.springbootcrud.dto.ProductoResponseDTO;
import pe.jsaire.springbootcrud.exceptions.ProductoNotFoundException;
import pe.jsaire.springbootcrud.services.ProductoService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {


    private final ProductoService productoService;



    @GetMapping("/all")
    public ResponseEntity<Page<ProductoResponseDTO>> findAll(@RequestParam String field,
                                                             @RequestParam Boolean desc,
                                                             @RequestParam Integer page) {
        return ResponseEntity.ok(productoService.findAll(field, desc, page));
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
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(requestDTO));
    }

    @GetMapping
    public ResponseEntity<?> buscarPorSku(@RequestParam String sku) {
        if (!productoService.existsBySku(sku)) {
            throw new ProductoNotFoundException(" No existe este sku :" + sku + " !!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findBySku(sku));
    }

    @GetMapping("/nombre")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        if (!productoService.existsByNombre(nombre)) {
            throw new ProductoNotFoundException(" No existe este nombre :" + nombre + " !!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findByNombre(nombre));
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }


}
