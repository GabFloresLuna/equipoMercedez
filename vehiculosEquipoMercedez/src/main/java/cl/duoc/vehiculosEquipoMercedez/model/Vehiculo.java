package cl.duoc.vehiculosEquipoMercedez.model;

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
@Table(name = "vichulos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehiculo 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_vehiculo", length = 50, nullable = false)
    private String nombre;

    @Column(name = "cod_vehiculo",length = 10, nullable = false, unique = true)
    private String code;

    @Column(name = "stock_vehiculo", length = 3, nullable = false)
    private Integer stock;

    @ManyToOne()
    @JoinColumn(name = "marca_id")
    private Marca marca;
}
