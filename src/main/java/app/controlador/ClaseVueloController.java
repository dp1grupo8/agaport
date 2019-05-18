package app.controlador;

import java.util.ArrayList;

import app.modelo.ClaseVuelo;
import app.repositorios.RepositorioClaseVuelo;;

public class ClaseVueloController {
    public String agregarClaseVuelo(RepositorioClaseVuelo clasesRepo,String desc, int num){
        ClaseVuelo c = new ClaseVuelo();
        c.setDescripcion(desc);
        c.setNPrioridad(num);
        c.setBorrado(0);
		clasesRepo.save(c);
        return "Guardado";
    }
    public Iterable<ClaseVuelo> listarAerolineas(RepositorioClaseVuelo clasesRepo){
        Iterable<ClaseVuelo> listaClaseVuelo = clasesRepo.findAll();
        ArrayList<ClaseVuelo> listaExistentes = new ArrayList<ClaseVuelo>();
        for(ClaseVuelo a: listaClaseVuelo){
            if (a.getBorrado()==0){
                listaExistentes.add(a);
            }
        }
        return listaExistentes;
    }
    public String modificarClaseVuelo(RepositorioClaseVuelo clasesRepo,int id, String desc, int num){
        ClaseVuelo c = clasesRepo.findById(id).get();
        c.setDescripcion(desc);
        c.setNPrioridad(num);
        return "Modificado";
    }    
    public String eliminarClaseVuelo(RepositorioClaseVuelo clasesRepo,int idAvion){
        ClaseVuelo c = clasesRepo.findById(idAvion).get();	
        c.setBorrado(1);
        return "Eliminado";
    }    
}
