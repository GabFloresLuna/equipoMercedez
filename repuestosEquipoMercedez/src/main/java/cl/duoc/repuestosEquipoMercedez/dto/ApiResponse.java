package cl.duoc.repuestosEquipoMercedez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> 
{
    private boolean realizado;
    private String mensaje;
    private T data;
    private String timestamp;
}
