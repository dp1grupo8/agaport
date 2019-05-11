package app.modelo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="vuelosLlegada")
public class VueloLlegada {
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
    
    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(
        name = "id_puerta",
        nullable = false
    )
    @JsonIgnore
    private Puerta puertaAsignada;

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(
        name = "id_avion",
        nullable = false
    )
    @JsonIgnore
    private Avion avion;
    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(
        name = "id_clase",
        nullable = false
    )
    @JsonIgnore
    private ClaseVuelo clase;

}