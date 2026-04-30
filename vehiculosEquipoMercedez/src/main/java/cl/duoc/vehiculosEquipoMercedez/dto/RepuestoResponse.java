package cl.duoc.vehiculosEquipoMercedez.dto;
 
import lombok.Data;

@Data
public class RepuestoResponse 
{
    private boolean realizado;
    private String mensaje;
    private String data;
    private String timestamp;
}
