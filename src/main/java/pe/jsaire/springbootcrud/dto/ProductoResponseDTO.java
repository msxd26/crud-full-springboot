package pe.jsaire.springbootcrud.dto;


import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
