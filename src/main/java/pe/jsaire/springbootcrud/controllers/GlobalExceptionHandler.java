package pe.jsaire.springbootcrud.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.jsaire.springbootcrud.exceptions.ProductoNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<String> handleProductoNotFound(ProductoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + ex.getMessage());
    }
}
