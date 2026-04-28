package cl.duoc.repuestosEquipoMercedez.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "marcas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marca 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marca_id")
    private Long id;

    @Column(nullable = false, unique = true, name = "codigo_marca", length = 10)
    private String code;

    @Column(nullable = false, unique = true, name = "nombre_marca", length = 50)
    private String nombre;

    @OneToMany(mappedBy = "marca")
    private List<Repuesto> repuesto;
}
