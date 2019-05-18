package app.controlador;

import app.modelo.Puerta;

import app.repositorios.RepositorioPuerta;

public class PuertaController{

	public String agregarPuerta(RepositorioPuerta prioridadPuerta, int Tipo, double distanciaASalida, int flujoPersonas, int Estado){
		Puerta p = new Puerta();
		p.setTipo(Tipo);
		p.setDistanciaASalida(distanciaASalida);
		p.setFlujoPersonas(flujoPersonas);
		p.setEstado(Estado);
		prioridadPuerta.save(p);
		return "Guardado";
	}

	public String modificarPuerta(RepositorioPuerta prioridadPuerta, int Tipo, double distanciaASalida, int flujoPersonas, int Estado, int idPuerta){
		Puerta p = prioridadRepo.findById(idPuerta).get();
		p.setTipo(Tipo);
		p.setDistanciaASalida(distanciaASalida);
		p.setFlujoPersonas(flujoPersonas);
		p.setEstado(Estado);
		prioridadPuerta.saveOrUpdate(p);
		return "Modificado";
	}

	public String eliminarPuerta(RepositorioPuerta prioridadPuerta, int idPrioridad){
		//Implementar
		return "Eliminado";
	}
}