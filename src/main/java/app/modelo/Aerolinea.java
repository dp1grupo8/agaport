package app.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity // Le dice a Hibernate hacer una tabla de esta clase
@Table(name="aerolineas")
public class Aerolinea implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idAerolinea;
    private String Nombre;
    private int borrado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_prioridad", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})   
    private Prioridad prioridad;

    public int getIdAerolinea() {
        return idAerolinea;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre){
        this.Nombre=Nombre;
    }
    public Prioridad getPrioridad(){
        return prioridad;
    }
    public void setPrioridad(Prioridad p){
        this.prioridad = p;
    }
}