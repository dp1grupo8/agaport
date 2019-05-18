package app.controlador;

import app.modelo.Permiso;
import app.repositorios.RepositorioPermiso;

public class PermisoController {
    public String agregarPermiso(RepositorioPermiso permisosRepo, String desc, int numero){
        Permiso p = new Permiso();
		p.setDescripcion(desc);
        p.setNumero(numero);
        p.setBorrado(0);
		permisosRepo.save(p);
        return "Guardado";
    }
    public Iterable<Permiso> listarPermisos(RepositorioPermiso permisosRepo){
        Iterable<Permiso> listaPermisos = permisosRepo.findAll();
        //filtrado
        return listaPermisos;
    }

    public String modificarPermiso(RepositorioPermiso permisosRepo,int idPermiso, String desc, int numero){
        Permiso p = permisosRepo.findById(idPermiso).get();	
        p.setDescripcion(desc);
        p.setNumero(numero);
        return "Modificado";
    }    
    public String eliminarPermiso(RepositorioPermiso permisosRepo,int idPermiso){
        Permiso p = permisosRepo.findById(idPermiso).get();	
        p.setBorrado(1);
        return "Eliminado";
    }    
}
