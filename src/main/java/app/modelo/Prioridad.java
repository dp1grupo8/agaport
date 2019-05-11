package app.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="prioridades")
public class Prioridad {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPrioridad;
    private String Descripcion;
    private int NPrioridad;

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @return the nPrioridad
     */
    public int getNPrioridad() {
        return NPrioridad;
    }

    /**
     * @param nPrioridad the nPrioridad to set
     */
    public void setNPrioridad(int nPrioridad) {
        this.NPrioridad = nPrioridad;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

}