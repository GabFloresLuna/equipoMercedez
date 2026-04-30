package cl.duoc.repuestosEquipoMercedez.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.repuestosEquipoMercedez.dto.ApiResponse;
import cl.duoc.repuestosEquipoMercedez.dto.RepuestoDTO;
import cl.duoc.repuestosEquipoMercedez.model.Repuesto;
import cl.duoc.repuestosEquipoMercedez.service.RepuestoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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


@RestController
@RequestMapping("/api/v1/repuestos")
@RequiredArgsConstructor
public class RepuestoController 
{
    private final RepuestoService repuestoService;

    //Método auxiliar
    private RepuestoDTO mapToDTO(Repuesto repuesto)
    {
        RepuestoDTO dto = new RepuestoDTO();
        dto.setNombre(repuesto.getNombre());
        dto.setCodigoRepuesto(repuesto.getCode());
        dto.setStock(repuesto.getStock());
        

        if (repuesto.getMarca() != null)
        {
            dto.setMarca(repuesto.getMarca().getNombre());
            dto.setCodigoMarca(repuesto.getMarca().getCode());
        }

        return dto;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RepuestoDTO>>> listRepuestos()
    {
        //obtiene la lista de repuesto
        List<Repuesto> listaRepuesto = repuestoService.listRepuestos();

        //genera la lista de repuestoDTO
        List<RepuestoDTO> dtos = listaRepuesto.stream()
            .map(this::mapToDTO).collect(Collectors.toList());

        //Genera la respuesta
        ApiResponse<List<RepuestoDTO>> response = new ApiResponse<>
        (
            true,
            "Lista de repuestos obtenida exitosamente",
            dtos,
            LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<ApiResponse<RepuestoDTO>> create(@Valid @RequestBody RepuestoDTO dto) 
    {
        //Asigna un nuevo repuesto que ya tiene ID
        Repuesto nuevRepuesto = repuestoService.save(dto);
        
        //Reconstruye la respuesta
        RepuestoDTO data = mapToDTO(nuevRepuesto);

        //Construye respuesta
        ApiResponse<RepuestoDTO> response = new ApiResponse<>
        (
            true,
            "Repuesto creado exitosamente",
            data,
            LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{code}", params = "n")
    public ResponseEntity<ApiResponse<String>> modificarStock(@PathVariable String code, @RequestParam Integer n)
    {
        String data = repuestoService.modificarStock(code, n);

        ApiResponse<String> response = new ApiResponse<>
        (
            true,
            "Repuesto modificado correctamente",
            data,
            LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
