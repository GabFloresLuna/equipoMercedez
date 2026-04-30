package cl.duoc.vehiculosEquipoMercedez.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepuestoClient 
{
    private final WebClient webClient;
}
