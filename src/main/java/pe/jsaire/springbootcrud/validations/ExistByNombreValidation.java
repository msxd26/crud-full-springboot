package pe.jsaire.springbootcrud.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.jsaire.springbootcrud.services.ProductoService;


@Component
@RequiredArgsConstructor
public class ExistByNombreValidation implements ConstraintValidator<isExistByNombre, String> {

    private final ProductoService productoService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return !productoService.existsByNombre(value);
    }
}
