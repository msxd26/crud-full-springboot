package pe.jsaire.springbootcrud.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pe.jsaire.springbootcrud.validations.isExistByNombre;
import pe.jsaire.springbootcrud.validations.isExistBySku;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
public class ProductoRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @isExistByNombre(message = "Este nombre ya existe en la base de datos")
    private String nombre;

    @NotBlank(message = "Se requiere una descripcion")
    private String descripcion;

    @NotBlank( message = "El SKU no puede estar vacío")
    @Size(max = 50, message = "El SKU no puede superar los 50 caracteres")
    @isExistBySku(message = "Este SKU ya existe en la base de datos")
    private String sku;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @NotNull(message = "El stock no puede ser negativo")
    @Min(value = 1)
    private Integer stockActual;

    @NotBlank(message = "El campo es requerido")
    private String unidadMedida;

}
