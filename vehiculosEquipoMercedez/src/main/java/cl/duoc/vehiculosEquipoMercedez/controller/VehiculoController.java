package cl.duoc.vehiculosEquipoMercedez.controller;

import java.util.List;

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
    public ResponseEntity<Vehiculo> crearVehiculo(@Valid @RequestBody VehiculoDTO dto) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnnio(dto.getAnnio());
        vehiculo.setPlaca(dto.getPlaca());
        vehiculo.setPrecio(dto.getPrecio());

        Vehiculo creado = vehiculoService.agregarVehiculo(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Ver todo el stock de vehículos
    @GetMapping
    public ResponseEntity<List<Vehiculo>> obtenerStock() {
        List<Vehiculo> vehiculos = vehiculoService.obtenerStock();
        return ResponseEntity.ok(vehiculos);
    }

    // Buscar vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorId(@PathVariable Long id) {
        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id);
        return ResponseEntity.ok(vehiculo);
    }

    // Vender (eliminar) un vehículo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> venderVehiculo(@PathVariable Long id) {
        vehiculoService.venderVehiculo(id);
        return ResponseEntity.ok("Vehículo con ID " + id + " vendido exitosamente");
    }
}
