package cl.duoc.vehiculosEquipoMercedez.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.vehiculosEquipoMercedez.dto.VehiculoDTO;
import cl.duoc.vehiculosEquipoMercedez.model.Marca;
import cl.duoc.vehiculosEquipoMercedez.model.Vehiculo;
import cl.duoc.vehiculosEquipoMercedez.repository.MarcaRepository;
import cl.duoc.vehiculosEquipoMercedez.repository.VehiculoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoService 
{
    private final VehiculoRepository vehiculoRepository;
    private final MarcaRepository marcaRepository;

    private Vehiculo findByCode(String code)
    {
        return vehiculoRepository.findByCode(code)
            .orElseThrow(()-> new RuntimeException("No existe repuesto con dicho código/*El formato del codigo debe ser XX-000 (Ej., NY-101)"));
    }

    @Transactional
    public Vehiculo save(VehiculoDTO dto)
    {

        // Busca la marca
        Marca marca = marcaRepository.findByCode(dto.getCodeMarca())
            .orElseThrow (()-> new RuntimeException("No existe marca con dicho código"));
        
        //Guardar repuesto
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setCode(dto.getCode());
        vehiculo.setNombre(dto.getNombre());
        vehiculo.setStock(dto.getStock());
        vehiculo.setMarca(marca);

        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> listVehiculo()
    {
        return vehiculoRepository.findAll();
    }

    @Transactional
    public String modificaStock(String code, Integer n)
    {
        Vehiculo vehiculo = findByCode(code);
        Integer stockActual = vehiculo.getStock();
        if (stockActual + n < 0)
        {
            throw new RuntimeException("Stock insuficiente");
        }
        vehiculo.setStock(stockActual + n);
        return "El stock ha sido modificado en %d".formatted(n);
    }
}
