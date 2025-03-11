package pe.jsaire.springbootcrud.validations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.jsaire.springbootcrud.services.ProductoService;


@RequiredArgsConstructor
@Component
public class ExistBySkuValidation implements ConstraintValidator<isExistBySku, String> {


    private final ProductoService productoService;

    @Override
    public boolean isValid(String sku, ConstraintValidatorContext context) {
        if (sku == null || sku.isBlank()) {
            return false;
        }
        return !productoService.existsBySku(sku);
    }
}
