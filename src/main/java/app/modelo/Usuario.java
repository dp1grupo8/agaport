package app.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity // Le dice a Hibernate hacer una tabla de esta clase
@Table(name="usuarios")

public class Usuario implements Serializable{
    @Id
    private int DNI;
    private String Password;
    private String Nombres;
    private int borrado;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_permiso", nullable = false)
    //@JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})     
    private Permiso permiso;

    public Usuario(){
        Permiso permiso = new Permiso();
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return Nombres;
    }

    public int getIdUsuario() {
        return DNI;
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