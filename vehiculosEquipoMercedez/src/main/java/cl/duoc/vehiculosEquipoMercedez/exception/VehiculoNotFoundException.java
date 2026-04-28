package cl.duoc.vehiculosEquipoMercedez.exception;

public class VehiculoNotFoundException extends RuntimeException {

    public VehiculoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
