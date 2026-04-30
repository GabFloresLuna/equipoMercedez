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

    private Repuesto findByCode(String code)
    {
        return repuestoRepository.findByCode(code)
            .orElseThrow(()-> new RuntimeException("No existe repuesto con dicho código/*El formato del codigo debe ser XX-000 (Ej., NY-101)"));
    }

    @Transactional
    public Repuesto save(RepuestoDTO dto)
    {

        // Busca o crea la marca
        Marca marca = marcaRepository.findByCode(dto.getCodigoMarca())
            .orElseThrow (()-> new RuntimeException("No existe marca con dicho código"));
        
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

    @Transactional
    public String modificarStock(String code, Integer n)
    {
        Repuesto repuesto = findByCode(code);
        Integer stockActual = repuesto.getStock();
        if (stockActual + n < 0)
        {
            throw new RuntimeException("Stock insuficiente");
        }
        repuesto.setStock(stockActual+n);
        return "El stock ha sido modificado en %d".formatted(n);
    }
}
