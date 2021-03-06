
package Modelo.Obligaciones;

import Enum.TipoRegimen;
import Objetos.Periodo;
import java.io.Serializable;

public class Cedular extends Impuesto implements Serializable{

    private Float totalCasaHabitacion;
    private Float totalLocalComercial;
    
    public Cedular(Periodo periodo) {
        super(periodo, TipoRegimen.Cedular);
    }

    @Override
    public Float totalPagar() {
        Float total = totalCasaHabitacion * (float)0.10 + totalLocalComercial * (float)0.25;
        Float iva = (totalCasaHabitacion + totalLocalComercial) * (float)0.16;
        return total + iva;
    }
}