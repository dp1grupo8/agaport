package app.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="aviones")
public class Avion implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idAvion;
    private String Placa;
    private int CapacidadMax;
    private int CargaMax;
    private int CombustibleMax;
    private int borrado;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_aerolinea", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Aerolinea aerolinea;

    /**
     * @return the idAvion
     */
    public int getIdAvion() {
        return idAvion;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return Placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        Placa = placa;
    }

    /**
     * @return the capacidadMax
     */
    public int getCapacidadMax() {
        return CapacidadMax;
    }

    /**
     * @param capacidadMax the capacidadMax to set
     */
    public void setCapacidadMax(int capacidadMax) {
        CapacidadMax = capacidadMax;
    }

    /**
     * @return the cargaMax
     */
    public int getCargaMax() {
        return CargaMax;
    }

    /**
     * @param cargaMax the cargaMax to set
     */
    public void setCargaMax(int cargaMax) {
        CargaMax = cargaMax;
    }

    /**
     * @return the combustibleMax
     */
    public int getCombustibleMax() {
        return CombustibleMax;
    }

    /**
     * @param combustibleMax the combustibleMax to set
     */
    public void setCombustibleMax(int combustibleMax) {
        CombustibleMax = combustibleMax;
    }

    /**
     * @return the aerolinea
     */
    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    /**
     * @param aerolinea the aerolinea to set
     */
    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

}