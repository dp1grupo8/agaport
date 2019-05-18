package app.controlador;

import app.modelo.Prioridad;

import app.repositorios.RepositorioPrioridad;

public class PrioridadController{

	public String agregarPrioridad(RepositorioPrioridad prioridadRepo, String Descripcion, int nPrioridad){
		Prioridad p = new Prioridad();
		p.setDescripcion(Descripcion);
		p.setNPrioridad(nPrioridad);
		prioridadRepo.save(p);
		return "Guardado";
	}

	public String modificarPrioridad(RepositorioPrioridad prioridadRepo, String Descripcion, int nPrioridad, int idPrioridad){
		Prioridad p = prioridadRepo.findById(idPrioridad).get();
		p.setDescripcion(Descripcion);
		p.setNPrioridad(nPrioridad);
		prioridadRepo.saveOrUpdate(p);
		return "Modificado";
	}

	public String eliminarPrioridad(RepositorioPrioridad prioridadRepo, int idPrioridad){
		//Implementar
		return "Eliminado";
	}
} 