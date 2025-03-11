package pe.jsaire.springbootcrud.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy ={ExistBySkuValidation.class})
public @interface isExistBySku {

    String message() default "Este producto ya existe en la base de datos";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
