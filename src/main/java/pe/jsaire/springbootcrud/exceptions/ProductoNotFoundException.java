package pe.jsaire.springbootcrud.exceptions;


public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(String message) {
        super(message);
    }
}
