package app.controlador;

import app.modelo.VueloLlegada;
import app.modelo.Puerta;
import app.modelo.Avion;
import app.modelo.ClaseVuelo;

import app.repositorios.RepositorioVueloLlegada;
import app.repositorios.RepositorioPuerta;
import app.repositorios.RepositorioAvion;
import app.repositorios.RepositorioClaseVuelo;

public class VueloLlegadaController{

	public String agregarVueloLlegada(RepositorioVueloLlegada vueloLlegadaRepo, RepositorioPuerta puertaRepo, RepositorioAvion avionRepo, 
										RepositorioClaseVuelo claseVueloRepo, DATE horaLlegadaProg, DATE horaLlegadaReal, int nivelCombustible, 
										int nivelRiesgoClima, int nPersonas,int kEquipaje, int Estado, int idPuerta, int idAvion, int idClaseVuelo){

		Puerta p = puertaRepo.findById(idPuerta).get();
		Avion a = avionRepo.findById(idAvion).get();
		ClaseVuelo cv = claseVueloRepo.findById(idClaseVuelo).get();
		VueloLlegada v = new VueloLlegada();
		v.setHoraLlegadaProg(horaLlegadaProg);
		v.setHoraLlegadaReal(horaLlegadaReal);
		v.setNivelCombustible(nivelCombustible);
		v.setNivelRiesgoClima(nivelRiesgoClima);
		v.setNPersonas(nPersonas);
		v.setKEquipaje(kEquipaje);
		v.setEstado(Estado);
		v.setPuertaAsignada(p);
		v.setAvion(a);
		v.setClaseVuelo(cv);
		vueloLlegadaRepo.save(v);
		return "Guardado";
	}

	public String agregarVueloLlegada(RepositorioVueloLlegada vueloLlegadaRepo, RepositorioPuerta puertaRepo, RepositorioAvion avionRepo, 
										RepositorioClaseVuelo claseVueloRepo, DATE horaLlegadaProg, DATE horaLlegadaReal, int nivelCombustible, 
										int nivelRiesgoClima, int nPersonas,int kEquipaje, int Estado, int idPuerta, int idAvion, int idClaseVuelo, int idVueloLlegada){

		VueloLlegada v = vueloLlegadaRepo.findById(idVueloLlegada).get();
		Puerta p = puertaRepo.findById(idPuerta).get();
		Avion a = avionRepo.findById(idAvion).get();
		ClaseVuelo cv = claseVueloRepo.findById(idClaseVuelo).get();
		v.setHoraLlegadaProg(horaLlegadaProg);
		v.setHoraLlegadaReal(horaLlegadaReal);
		v.setNivelCombustible(nivelCombustible);
		v.setNivelRiesgoClima(nivelRiesgoClima);
		v.setNPersonas(nPersonas);
		v.setKEquipaje(kEquipaje);
		v.setEstado(Estado);
		v.setPuertaAsignada(p);
		v.setAvion(a);
		v.setClaseVuelo(cv);
		vueloLlegadaRepo.saveOrUpdate(v);
		return "Modificado";
	}

	public String eliminarVueloLlegada(RepositorioVueloLlegada vueloLlegadaRepo,int idVueloLlegada){
		//implementar
		return "Eliminado"
	}
} 
