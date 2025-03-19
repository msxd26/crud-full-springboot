package pe.jsaire.springbootcrud.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import pe.jsaire.springbootcrud.validations.PutOnlyValidated;
import pe.jsaire.springbootcrud.validations.isExistByNombre;
import pe.jsaire.springbootcrud.validations.isExistBySku;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {

    @Size(min = 1, max = 100)
    @NotBlank(message = "Este campo es requerido !!")
    @isExistByNombre(groups = PutOnlyValidated.class)
    private String nombre;

    @NotBlank(message = "Se requiere una descripcion")
    private String descripcion;

    @NotBlank(message = "Este campo es requerido !!")
    @Size(min = 1, max = 50, message = "El SKU no puede superar los 50 caracteres")
    @isExistBySku(groups = PutOnlyValidated.class)
    private String sku;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @NotNull(message = "El stock no puede ser negativo")
    @Min(value = 1)
    private Integer stockActual;

    @NotBlank(message = "El campo es requerido")
    private String unidadMedida;

    private Boolean estado;

}
