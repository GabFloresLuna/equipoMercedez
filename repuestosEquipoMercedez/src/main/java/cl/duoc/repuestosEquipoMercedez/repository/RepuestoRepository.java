package cl.duoc.repuestosEquipoMercedez.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import cl.duoc.repuestosEquipoMercedez.model.Repuesto;
 

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Long> 
{
    Optional<Repuesto> findByNombre(String nombre);

    Optional<Repuesto> findByCode(String code);

    boolean existsByCode(String code);

    boolean existsByNombre(String nombre);
}
