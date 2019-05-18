package app.controlador;

import java.util.ArrayList;

import app.modelo.Aerolinea;
import app.repositorios.RepositorioAerolinea;

public class AerolineaController {
    public String agregarAerolinea(RepositorioAerolinea aerolineaRepo, String nombre){
        Aerolinea a = new Aerolinea();
        a.setNombre(nombre);
        a.setBorrado(0);
		aerolineaRepo.save(a);
        return "Guardado";
    }
    public Iterable<Aerolinea> listarAerolineas(RepositorioAerolinea aerolineaRepo){
        Iterable<Aerolinea> listaAerolineas = aerolineaRepo.findAll();
        ArrayList<Aerolinea> listaExistentes = new ArrayList<Aerolinea>();
        for(Aerolinea a: listaAerolineas){
            if (a.getBorrado()==0){
                listaExistentes.add(a);
            }
        }
        //filtrado
        return listaExistentes;
    }
    public String modificarAerolinea(RepositorioAerolinea aerolineaRepo,int idAerolinea, String nombre){
        Aerolinea a = aerolineaRepo.findById(idAerolinea).get();	
        a.setNombre(nombre);
        return "Modificado";
    }    
    public String eliminarAerolinea(RepositorioAerolinea aerolineaRepo,int idAerolinea){
        Aerolinea a = aerolineaRepo.findById(idAerolinea).get();	
        a.setBorrado(1);
        return "Eliminado";
    }    
}
