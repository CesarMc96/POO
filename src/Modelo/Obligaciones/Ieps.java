
package Modelo.Obligaciones;

import Enum.TipoRegimen;
import Objetos.Periodo;

public class Ieps extends Impuesto{

    public Ieps(Periodo periodo) {
        super(periodo, TipoRegimen.Ieps);
    }

    @Override
    public float calculoImpuestos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float calculaRecargos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float totalPagar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

//litros de gasolina vendiste, cada litro de gasolina se le multiplica el precio de la gasolina y se calcula despues el 20% del ieos
