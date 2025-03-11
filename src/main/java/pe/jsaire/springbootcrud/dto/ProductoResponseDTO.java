package pe.jsaire.springbootcrud.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String sku;
    private BigDecimal precio;
    private Integer stockActual;
    private String unidadMedida;
    private Boolean estado;

}
