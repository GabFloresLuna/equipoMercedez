package cl.duoc.vehiculosEquipoMercedez.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.vehiculosEquipoMercedez.exception.VehiculoNotFoundException;
import cl.duoc.vehiculosEquipoMercedez.exception.PlacaDuplicadaException;
import cl.duoc.vehiculosEquipoMercedez.model.Vehiculo;
import cl.duoc.vehiculosEquipoMercedez.repository.VehiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    //Agregar nuevos vehiculos
    public Vehiculo agregarVehiculo(Vehiculo vehiculo) {
        // Verificar si la placa ya existe
        if (vehiculoRepository.existsByPlaca(vehiculo.getPlaca())) {
            throw new PlacaDuplicadaException("Ya existe un vehículo con la placa: " + vehiculo.getPlaca());
        }
        return vehiculoRepository.save(vehiculo);
    }

    //Ver stock disponible
    public List<Vehiculo> obtenerStock() {
        return vehiculoRepository.findAll();
    }

    //Buscar vehiculo por ID
    public Vehiculo obtenerVehiculoPorId(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new VehiculoNotFoundException("No se encontró el vehículo con ID: " + id));
    }

    //Vender un vehiculo
    public void venderVehiculo(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new VehiculoNotFoundException("No se puede vender. No se encontró el vehículo con ID: " + id);
        }
        vehiculoRepository.deleteById(id);
    }
}
