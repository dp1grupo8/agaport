package app.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity // Le dice a Hibernate hacer una tabla de esta clase
@Table(name="usuarios")

public class Usuario {
    @Id
    private int DNI;
    private String Password;
    private String Nombres;
    private int borrado;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_permiso", nullable = false)
    @JsonIgnore
    
    private Permiso permiso;

    /**
     * @return the nombres
     */
    public String getNombres() {
        return Nombres;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    /**
     * @param nombres the nombres to set
     */
	public void setNombres(String nombres) {
		this.Nombres = nombres;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.Password = password;
    }
    
    public int getDNI(){
        return DNI;
    }
    public void setDNI(int dni){
        this.DNI=dni;
    }

    public Permiso getPermiso(){
        return permiso;
    }
    public void setPermiso(Permiso permiso){
        this.permiso=permiso;
    }
}