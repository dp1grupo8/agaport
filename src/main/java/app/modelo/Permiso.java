package app.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="permisos")

public class Permiso {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPermiso;
    private String Descripcion;
    private int Numero;
    private int borrado;

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return Numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.Numero = numero;
    }

    /**
     * @param descripcion the descripcion to set
     */
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}

    public int getIdPermiso(){
        return idPermiso;
    }
}