package pe.jsaire.springbootcrud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
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
    private LocalDateTime actualizadoEn;


    @PrePersist
    void prePersist() {
        this.creadoEn = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.actualizadoEn = LocalDateTime.now();
    }
}
