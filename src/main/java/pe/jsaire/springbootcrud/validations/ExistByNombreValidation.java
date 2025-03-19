package pe.jsaire.springbootcrud.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.jsaire.springbootcrud.services.IProductoService;


@Component
@RequiredArgsConstructor
public class ExistByNombreValidation implements ConstraintValidator<isExistByNombre, String> {

    private final IProductoService productoService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        return !productoService.existsByNombre(value);
    }
}
