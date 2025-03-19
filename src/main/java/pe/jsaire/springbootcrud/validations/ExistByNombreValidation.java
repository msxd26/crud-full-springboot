package pe.jsaire.springbootcrud.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.jsaire.springbootcrud.repositories.ProductoRepository;


@Component
@RequiredArgsConstructor
public class ExistByNombreValidation implements ConstraintValidator<isExistByNombre, String> {

    private final ProductoRepository productoRepository;


    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context) {
        if (nombre == null || nombre.isBlank()) {
            return false;
        }
        return !productoRepository.existsByNombre(nombre);
    }
}
