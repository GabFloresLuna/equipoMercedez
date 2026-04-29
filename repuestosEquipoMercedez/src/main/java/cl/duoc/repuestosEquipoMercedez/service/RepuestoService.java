package cl.duoc.repuestosEquipoMercedez.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.repuestosEquipoMercedez.dto.RepuestoDTO;
import cl.duoc.repuestosEquipoMercedez.model.Marca;
import cl.duoc.repuestosEquipoMercedez.model.Repuesto;
import cl.duoc.repuestosEquipoMercedez.repository.MarcaRepository;
import cl.duoc.repuestosEquipoMercedez.repository.RepuestoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepuestoService 
{
    private final RepuestoRepository repuestoRepository;
    private final MarcaRepository marcaRepository;

    @Transactional
    public Repuesto save(RepuestoDTO dto)
    {
        // Excepción por si ya existe el código/nombre
        if (repuestoRepository.existsByCode(dto.getCodigoRepuesto()))
        {
            throw new RuntimeException("El código del repuesto ya existe");
        }
        if (repuestoRepository.existsByNombre(dto.getNombre()))
        {
            throw new RuntimeException("El nombre del repuesto ya existe");
        }

        // Busca o crea la marca
        Marca marca = marcaRepository.findByCode(dto.getCodigoMarca())
            .orElseGet(()-> {
                                Marca nuevMarca = new Marca();
                                nuevMarca.setCode(dto.getCodigoMarca());
                                nuevMarca.setNombre(dto.getMarca());
                                return marcaRepository.save(nuevMarca);
                            } );
        
        //Guardar repuesto
        Repuesto repuesto = new Repuesto();
        repuesto.setCode(dto.getCodigoRepuesto());
        repuesto.setNombre(dto.getNombre());
        repuesto.setStock(dto.getStock());
        repuesto.setMarca(marca);

        return repuestoRepository.save(repuesto);
    }

    public List<Repuesto> listRepuestos()
    {
        return repuestoRepository.findAll();
    }
}
