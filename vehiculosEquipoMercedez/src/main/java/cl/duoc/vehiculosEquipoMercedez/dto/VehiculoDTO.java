package cl.duoc.vehiculosEquipoMercedez.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDTO 
{   
    @NotBlank(message = "El nombre del repuesto es necesario")
    @Size(min = 3, max = 50, message = "El nombre del repuesto debe ser entre 3 y 50 carácteres")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "El nombre del repuesto solo puede contener letras, números y espacios")
    private String nombre;

    @NotBlank(message = "El código de la marca es necesario")
    @Pattern(regexp = "^[A-Z]{2}-\\d{3}$", message = "El formato del codigo debe ser XX-000 (Ej., NY-101)")
    private String code;

    @NotNull
    @Min(value = 1, message = "Stock debe ser almenos 1")
    @Max(value = 999, message = "Stock no puede ser mayor a 999 unidades")
    private Integer stock;

    @NotBlank(message = "El nombre de la marca es necesario")
    @Pattern(regexp = "^[a-zA-Z0-9\\s-]+$", message = "El nombre de la marca solo puede contener letras, números, '-' y $")
    @Size(min = 5, max = 50, message = "El nombre de la marca debe ser entre 5 y 50 carácteres")
    private String nombreMarca;

    @NotBlank(message = "El código de la marca es necesario")
    @Pattern(regexp = "^[A-Z]{2}-\\d{3}$", message = "El formato del codigo debe ser XX-000 (Ej., NY-101)")
    private String codeMarca;
}
