package cl.duoc.vehiculosEquipoMercedez.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.duoc.vehiculosEquipoMercedez.model.Vehiculo;
import cl.duoc.vehiculosEquipoMercedez.repository.VehiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    //Agregar nuevos vehiculos
    public Vehiculo agregarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    //Ver stock disponible
    public List<Vehiculo> obtenerStock() {
        return vehiculoRepository.findAll();
    }

    //Buscar vehiculo por ID
    public Optional<Vehiculo> obtenerVehiculoPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

    //Verificar si existe por placa
    public boolean existePorPlaca(String placa) {
        return vehiculoRepository.existsByPlaca(placa);
    }

    //Vender un vehiculo
    public boolean venderVehiculo(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            return false;
        }
        vehiculoRepository.deleteById(id);
        return true;
    }
}
