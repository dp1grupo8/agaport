package app.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="vuelosLlegada")
public class VueloLlegada implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idVuelo;
    private Date HoraLlegadaProg;
    private Date HoraLlegadaReal;
    private int NivelCombustible;
    private int NivelRiesgoClima;
    private int NPersonas;
    private int KEquipaje;
    private int Estado;
    private int borrado;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_puerta", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})   
    private Puerta puertaAsignada;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_avion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})   
    private Avion avion;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_clase", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})   
    private ClaseVuelo clase;

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    /**
     * @return the horaLlegadaProg
     */
    public Date getHoraLlegadaProg() {
        return HoraLlegadaProg;
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
     * @return the kEquipaje
     */
    public int getKEquipaje() {
        return KEquipaje;
    }

    /**
     * @param kEquipaje the kEquipaje to set
     */
    public void setKEquipaje(int kEquipaje) {
        this.KEquipaje = kEquipaje;
    }

    /**
     * @return the nPersonas
     */
    public int getNPersonas() {
        return NPersonas;
    }

    /**
     * @param nPersonas the nPersonas to set
     */
    public void setNPersonas(int nPersonas) {
        this.NPersonas = nPersonas;
    }

    /**
     * @return the nivelRiesgoClima
     */
    public int getNivelRiesgoClima() {
        return NivelRiesgoClima;
    }

    /**
     * @param nivelRiesgoClima the nivelRiesgoClima to set
     */
    public void setNivelRiesgoClima(int nivelRiesgoClima) {
        this.NivelRiesgoClima = nivelRiesgoClima;
    }

    /**
     * @return the nivelCombustible
     */
    public int getNivelCombustible() {
        return NivelCombustible;
    }

    /**
     * @param nivelCombustible the nivelCombustible to set
     */
    public void setNivelCombustible(int nivelCombustible) {
        this.NivelCombustible = nivelCombustible;
    }

    /**
     * @return the horaLlegadaReal
     */
    public Date getHoraLlegadaReal() {
        return HoraLlegadaReal;
    }

    /**
     * @param horaLlegadaReal the horaLlegadaReal to set
     */
    public void setHoraLlegadaReal(Date horaLlegadaReal) {
        this.HoraLlegadaReal = horaLlegadaReal;
    }

    /**
     * @param horaLlegadaProg the horaLlegadaProg to set
     */
    public void setHoraLlegadaProg(Date horaLlegadaProg) {
        this.HoraLlegadaProg = horaLlegadaProg;
    }

    public Puerta getPuerta(){
        return puertaAsignada;
    }

    public void setPuerta(Puerta p){
        this.puertaAsignada = p;
    }

    public ClaseVuelo getClaseVuelo(){
        return clase;
    }
    public void setClaseVuelo(ClaseVuelo c){
        this.clase = c;
    }
    public Avion getAvion(){
        return avion;
    }
    public void setAvion(Avion a){
        this.avion = a;
    }

}