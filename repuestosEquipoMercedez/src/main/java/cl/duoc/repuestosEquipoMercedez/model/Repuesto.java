package cl.duoc.repuestosEquipoMercedez.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; 
import jakarta.persistence.Table; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repuestos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repuesto 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repuesto_id")
    private Long id;

    @Column(nullable = false, unique = true, name = "codigo_repuesto", length = 10)
    private String code;

    @Column(nullable = false, name = "nombre_repuesto", length = 50)
    private String nombre;

    @Column(nullable = false, length = 3)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
}
