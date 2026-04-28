package cl.duoc.repuestosEquipoMercedez.service;

import org.springframework.stereotype.Service;

import cl.duoc.repuestosEquipoMercedez.dto.RepuestoDTO;
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
    public RepuestoDTO guardarRepuesto(RepuestoDTO dto)
    {
    }
}
