package pe.jsaire.springbootcrud.validations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.jsaire.springbootcrud.entities.Producto;
import pe.jsaire.springbootcrud.services.IProductoService;


@RequiredArgsConstructor
@Component
public class ExistBySkuValidation implements ConstraintValidator<isExistBySku, String> {


    private final IProductoService productoService;

    @Override
    public boolean isValid(String sku, ConstraintValidatorContext context) {
        if (sku == null || sku.isBlank()) {
            return false;
        }


        if (productoService.existsBySku(sku)) {
            context.buildConstraintViolationWithTemplate("Este SKU ya existe en la base de datos")
                    .addConstraintViolation();
            return false;
        }

       return !productoService.existsBySku(sku);
    }
}
