package cl.duoc.vehiculosEquipoMercedez.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.vehiculosEquipoMercedez.dto.VehiculoDTO;
import cl.duoc.vehiculosEquipoMercedez.model.Vehiculo;
import cl.duoc.vehiculosEquipoMercedez.service.VehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    // Crear un nuevo vehículo
    @PostMapping
    public ResponseEntity<?> crearVehiculo(@Valid @RequestBody VehiculoDTO dto) {
        try {
            // Verificar placa duplicada
            if (vehiculoService.existePorPlaca(dto.getPlaca())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ya existe un vehículo con la placa: " + dto.getPlaca());
            }

            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setMarca(dto.getMarca());
            vehiculo.setModelo(dto.getModelo());
            vehiculo.setAnnio(dto.getAnnio());
            vehiculo.setPlaca(dto.getPlaca());
            vehiculo.setPrecio(dto.getPrecio());

            Vehiculo creado = vehiculoService.agregarVehiculo(vehiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear vehículo: " + e.getMessage());
        }
    }

    // Ver todo el stock de vehículos
    @GetMapping
    public ResponseEntity<?> obtenerStock() {
        try {
            List<Vehiculo> vehiculos = vehiculoService.obtenerStock();
            return ResponseEntity.ok(vehiculos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener stock: " + e.getMessage());
        }
    }

    // Buscar vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVehiculoPorId(@PathVariable Long id) {
        try {
            Optional<Vehiculo> vehiculo = vehiculoService.obtenerVehiculoPorId(id);
            if (vehiculo.isPresent()) {
                return ResponseEntity.ok(vehiculo.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el vehículo con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar vehículo: " + e.getMessage());
        }
    }

    // Vender (eliminar) un vehículo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> venderVehiculo(@PathVariable Long id) {
        try {
            boolean vendido = vehiculoService.venderVehiculo(id);
            if (vendido) {
                return ResponseEntity.ok("Vehículo con ID " + id + " vendido exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el vehículo con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al vender vehículo: " + e.getMessage());
        }
    }
}
