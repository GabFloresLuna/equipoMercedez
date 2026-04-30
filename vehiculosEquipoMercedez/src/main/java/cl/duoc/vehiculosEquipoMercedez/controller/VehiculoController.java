package cl.duoc.vehiculosEquipoMercedez.controller;

import java.time.LocalDateTime;
import java.util.List; 
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.vehiculosEquipoMercedez.dto.ApiResponse;
import cl.duoc.vehiculosEquipoMercedez.dto.VehiculoDTO;
import cl.duoc.vehiculosEquipoMercedez.model.Vehiculo;
import cl.duoc.vehiculosEquipoMercedez.service.VehiculoService; 
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/vehiculos")
@RequiredArgsConstructor
public class VehiculoController 
{
    private final VehiculoService vehiculoService;

    private VehiculoDTO mapToDTO(Vehiculo vehiculo)
    {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setCode(vehiculo.getCode());
        dto.setNombre(vehiculo.getNombre());
        dto.setStock(vehiculo.getStock());

        if (vehiculo.getMarca()!= null)
        {
            dto.setNombreMarca(vehiculo.getMarca().getName());
            dto.setCodeMarca(vehiculo.getMarca().getCode());
        }

        return dto;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VehiculoDTO>>> listVechiculo()
    {
        List<Vehiculo> listaVehiuclo = vehiculoService.listVehiculo();

        List<VehiculoDTO> dtos = listaVehiuclo.stream()
            .map(this::mapToDTO).collect(Collectors.toList());

        ApiResponse<List<VehiculoDTO>> response = new ApiResponse<>
        (
            true,
            "Lista de vehiculos obtenida",
            dtos,
            LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VehiculoDTO>> create(@Valid @RequestBody VehiculoDTO dto)
    {
        Vehiculo vehiculo = vehiculoService.save(dto);

        VehiculoDTO data = mapToDTO(vehiculo);

        ApiResponse<VehiculoDTO> response = new ApiResponse<>
        (
            true,
            "Vehiculo creado",
            data,
            LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{code}", params = "n")
    public ResponseEntity<ApiResponse<String>> modificarStock (@PathVariable String code, @PathVariable Integer n)
    {
        String data = vehiculoService.modificaStock(code, n);

        ApiResponse<String> response = new ApiResponse<>
        (
            true,
            "Stock modificado",
            data,
            LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
