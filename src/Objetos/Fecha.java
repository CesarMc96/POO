package Objetos;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;

public class Fecha implements Serializable {

    private Integer dia;
    private Integer mes;
    private Integer anio;
    private static HashSet<Fecha> diasInhabiles = new HashSet<>();

    public static void agregarDiaInhabil(Fecha f) {
        diasInhabiles.add(f);
    }

    public static boolean esDiaInhabil(Fecha f) {
        return diasInhabiles.contains(f);
    }

    public Fecha(Integer dia, Integer mes, Integer anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public Fecha() {
        Calendar c1 = Calendar.getInstance();

        dia = c1.get(Calendar.DAY_OF_MONTH);
        mes = c1.get(Calendar.MONTH) + 1;
        anio = c1.get(Calendar.YEAR);
    }

    public int diasDelMes() {
        int diaAuxiliar = 0;

        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                diaAuxiliar = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                diaAuxiliar = 30;
                break;
            case 2:
                diaAuxiliar = anio % 4 == 0 ? 29 : 28;
                break;
        }

        return diaAuxiliar;
    }

    public boolean fechaValida() {

        if ((anio >= 1900) && (anio <= 2100)) {
            if ((mes > 0) && (mes < 13)) {

                if ((dia >= 1) && (dia < diasDelMes())) {
                    return true;
                }
            }
        }

        return false;
    }

    public void aumentar() {
        dia++;

        if (dia > diasDelMes()) {
            mes++;
            dia = 1;
        }
        if (mes > 12) {
            anio++;
            mes = 1;
        }
    }

    public void aumentar(Integer opcional) {
        dia = dia + opcional;

        if (dia > diasDelMes()) {
            dia = dia - diasDelMes();
            mes++;
        }
        if (mes > 12) {
            anio++;
        }
    }

    public void aumentarDiaInhabil() {
        do {
            aumentar();
        } while (esDiaInhabil(this));
    }

    public void aumentarDiaInhabil(DayOfWeek... dias) {
        boolean bandera = false;

        do {
            bandera = false;
            aumentar();
            for (int i = 0; i < dias.length; i++) {
                if (dias[i] == this.diaSemana()) {
                    bandera = true;
                }
            }
        } while (esDiaInhabil(this) || bandera);

    }

    public DayOfWeek diaSemana() {
        LocalDate dateTemporal = LocalDate.of(anio, mes, dia);
        return dateTemporal.getDayOfWeek();
    }

    public void disminuir() {
        dia--;

        if (dia <= 0) {
            mes--;
            dia = diasDelMes();
        }
        if (mes <= 0) {
            anio--;
            mes = 12;
        }
    }

    public void disminuir(Integer opcional) {
        dia = dia - opcional;

        if (dia <= 0) {
            mes--;
            dia = diasDelMes() + dia;
        }
        if (mes <= 0) {
            anio--;
            mes = 12;
        }
    }

    public int compareTo(Fecha f) {
        int resultado = this.anio - f.anio;
        if (resultado == 0) {
            resultado = this.mes - f.mes;
            if (resultado == 0) {
                resultado = this.dia - f.dia;
            }
        }
        return resultado;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fecha) {
            Fecha f = (Fecha) obj;
            return this.compareTo(f) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.dia);
        hash = 97 * hash + Objects.hashCode(this.mes);
        hash = 97 * hash + Objects.hashCode(this.anio);
        return hash;
    }

    public String diaSemana1() {
        int a = 0, b = 0, c = 0, d = 0, e, na = 0;

        if (anio >= 1900 && anio <= 1999) {
            a = 1;
            na = anio - 1900;
        }
        if (anio >= 2000 && anio <= 2099) {
            a = 0;
            na = anio - 2000;
        }
        if (anio >= 2100 && anio <= 2199) {
            a = -2;
            na = anio - 2100;
        }
        if (anio >= 2200 && anio <= 2299) {
            a = -4;
            na = anio - 2200;
        }

        b = (na / 4) + na;

        if (anio % 4 == 0 && mes == 1 || mes == 2) {
            c = -1;
        } else {
            c = 0;
        }

        switch (mes) {
            case 1:
            case 10:
                d = 6;
                break;
            case 2:
            case 3:
            case 11:
                d = 2;
                break;
            case 4:
            case 7:
                d = 5;
                break;
            case 5:
                d = 0;
                break;
            case 6:
                d = 3;
                break;
            case 8:
                d = 1;
                break;
            case 9:
            case 12:
                d = 4;
                break;
        }

        e = dia;

        int total = a + b + c + d + e;
        do {
            total = total - 7;
        } while (total > 7);

        String semana = null;

        switch (total) {
            case 1:
                semana = "lunes";
                break;
            case 2:
                semana = "martes";
                break;
            case 3:
                semana = "miercoles";
                break;
            case 4:
                semana = "jueves";
                break;
            case 5:
                semana = "viernes";
                break;
            case 6:
                semana = "sabado";
                break;
            case 7:
                semana = "domingo";
                break;
        }

        return semana;
    }

    @Override
    public String toString() {

        return String.format("%02d/%02d/%d", dia, mes, anio);
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer edad() {
        Fecha fa = new Fecha();

        int edad = fa.getAnio() - this.getAnio();
        if (this.getMes() > fa.getMes()) {
            edad = edad - 1;
        } else if (this.getMes() == fa.getMes() && this.getDia() > fa.getDia()) {
            edad = edad - 1;
        }

        return edad;
    }

}
