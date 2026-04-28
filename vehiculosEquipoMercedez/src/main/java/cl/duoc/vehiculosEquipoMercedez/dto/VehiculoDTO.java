package cl.duoc.vehiculosEquipoMercedez.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(min = 2, max = 50, message = "La marca debe tener entre 2 y 50 caracteres")
    private String marca;

    @NotBlank(message = "El modelo no puede estar vacío")
    @Size(min = 1, max = 50, message = "El modelo debe tener entre 1 y 50 caracteres")
    private String modelo;

    @NotNull(message = "El año no puede ser nulo")
    @Max(value = 2026, message = "El año debe ser menor o igual a 2026")
    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
    private Integer annio;

    @NotBlank(message = "La placa no puede estar vacía")
    @Size(min = 6, max = 10, message = "La placa debe tener entre 6 y 10 caracteres")
    private String placa;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser un número positivo")
    private Integer precio;
}