package app.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity // Le dice a Hibernate hacer una tabla de esta clase
@Table(name="aerolineas")
public class Aerolinea {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idAerolinea;
    private String Nombre;

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(
        name = "id_prioridad",
        nullable = false
    )
    @JsonIgnore
    private Prioridad prioridad;

	public int getIdAerolinea(){
        return idAerolinea;
    }
    public String getNombre(){
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