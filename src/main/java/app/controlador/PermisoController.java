package app.controlador;

import app.modelo.Permiso;
import app.repositorios.RepositorioPermiso;

public class PermisoController {
    public String agregarPermiso(RepositorioPermiso permisosRepo, String desc, int numero){
        Permiso p = new Permiso();
		p.setDescripcion(desc);
		p.setNumero(numero);
		permisosRepo.save(p);
        return "Guardado";
    }
    public String modificarPermiso(RepositorioPermiso permisosRepo,int idPermiso, String desc, int numero){
        Permiso p = permisosRepo.findById(idPermiso).get();	
        p.setDescripcion(desc);
        p.setNumero(numero);
        return "Modificado";
    }    
    public String eliminarPermiso(RepositorioPermiso permisosRepo,int idPermiso){
        //Permiso p = permisosRepo.findById(idPermiso).get();	
        //permisosRepo.delete(p);
        return "Eliminado";
    }    
}
