package app.controlador;

import app.modelo.ClaseVuelo;
import app.repositorios.RepositorioClaseVuelo;;

public class ClaseVueloController {
    public String agregarClaseVuelo(RepositorioClaseVuelo clasesRepo,String desc, int num){
        ClaseVuelo c = new ClaseVuelo();
        c.setDescripcion(desc);
        c.setNPrioridad(num);
		clasesRepo.save(c);
        return "Guardado";
    }
    public String modificarClaseVuelo(RepositorioClaseVuelo clasesRepo,int id, String desc, int num){
        ClaseVuelo c = clasesRepo.findById(id).get();
        c.setDescripcion(desc);
        c.setNPrioridad(num);
        return "Modificado";
    }    
    public String eliminarClaseVuelo(RepositorioClaseVuelo avionRepo,int idAvion){
        //Permiso p = permisosRepo.findById(idPermiso).get();	
        //permisosRepo.delete(p);
        return "Eliminado";
    }    
}
