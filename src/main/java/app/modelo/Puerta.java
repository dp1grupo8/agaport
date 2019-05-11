package app.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="puertas")
public class Puerta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPuerta;
    private int Tipo;
    private double DistanciaASalida;
    private int FlujoPersonas;
    private int Estado;

    /**
     * @return the tipo
     */
    public int getTipo() {
        return Tipo;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return Estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.Estado = estado;
    }

    /**
     * @return the flujoPersonas
     */
    public int getFlujoPersonas() {
        return FlujoPersonas;
    }

    /**
     * @param flujoPersonas the flujoPersonas to set
     */
    public void setFlujoPersonas(int flujoPersonas) {
        this.FlujoPersonas = flujoPersonas;
    }

    /**
     * @return the distanciaASalida
     */
    public double getDistanciaASalida() {
        return DistanciaASalida;
    }

    /**
     * @param distanciaASalida the distanciaASalida to set
     */
    public void setDistanciaASalida(double distanciaASalida) {
        this.DistanciaASalida = distanciaASalida;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.Tipo = tipo;
    }


}