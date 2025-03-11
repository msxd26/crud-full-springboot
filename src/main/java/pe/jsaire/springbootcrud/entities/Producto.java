package pe.jsaire.springbootcrud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String sku;
    private BigDecimal precio;
    private Integer stockActual;
    private String unidadMedida;
    private Boolean estado;
    private LocalDateTime creadoEn;
    private LocalDateTime ActualizadoEn;


    @PrePersist
    void prePersist() {
        creadoEn = LocalDateTime.now();
    }

    @PostUpdate
    void postUpdate() {
        ActualizadoEn = LocalDateTime.now();
    }
}
