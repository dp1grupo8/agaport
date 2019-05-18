package app.controlador;

import app.modelo.Prioridad;

import app.repositorios.RepositorioPrioridad;

import java.util.ArrayList;

public class PrioridadController{

	public String agregarPrioridad(RepositorioPrioridad prioridadRepo, String Descripcion, int nPrioridad){
		Prioridad p = new Prioridad();
		p.setDescripcion(Descripcion);
		p.setNPrioridad(nPrioridad);
		p.setBorrado(0);
		prioridadRepo.save(p);
		return "Guardado";
	}

	public Iterable<Prioridad> listarPrioridades(RepositorioPrioridad prioridadRepo){
        Iterable<Prioridad> listaPrioridades = prioridadRepo.findAll();
        ArrayList<Prioridad> listaExistentes = new ArrayList<Prioridad>();
        for(Prioridad p: listaPrioridades){
            if (p.getBorrado()==0){
                listaExistentes.add(p);
            }
        }
        return listaExistentes;
    }

	public String modificarPrioridad(RepositorioPrioridad prioridadRepo, String Descripcion, int nPrioridad, int idPrioridad){
		Prioridad p = prioridadRepo.findById(idPrioridad).get();
		p.setDescripcion(Descripcion);
		p.setNPrioridad(nPrioridad);
		prioridadRepo.saveOrUpdate(p);
		return "Modificado";
	}

	public String eliminarPrioridad(RepositorioPrioridad prioridadRepo, int idPrioridad){
		Prioridad p = prioridadRepo.findById(idPrioridad).get(); 
		p.setBorrado(1);
		return "Eliminado";
	}
} 