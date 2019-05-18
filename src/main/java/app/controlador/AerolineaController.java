package app.controlador;

import app.modelo.Aerolinea;
import app.repositorios.RepositorioAerolinea;

public class AerolineaController {
    public String agregarAerolinea(RepositorioAerolinea aerolineaRepo, String nombre){
        Aerolinea a = new Aerolinea();
		a.setNombre(nombre);
		aerolineaRepo.save(a);
        return "Guardado";
    }
    public String modificarAerolinea(RepositorioAerolinea aerolineaRepo,int idAerolinea, String nombre){
        Aerolinea a = aerolineaRepo.findById(idAerolinea).get();	
        a.setNombre(nombre);
        return "Modificado";
    }    
    public String eliminarAerolinea(RepositorioAerolinea aerolineaRepo,int idAerolinea){
        //Permiso p = permisosRepo.findById(idPermiso).get();	
        //permisosRepo.delete(p);
        return "Eliminado";
    }    
}
