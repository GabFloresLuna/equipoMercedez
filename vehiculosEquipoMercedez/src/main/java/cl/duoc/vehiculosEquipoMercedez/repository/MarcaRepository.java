package cl.duoc.vehiculosEquipoMercedez.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.vehiculosEquipoMercedez.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long>
{
    Optional<Marca> findByCode(String code);
}
