package app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.modelo.VueloLlegada;
import app.modelo.Puerta;
import app.modelo.Avion;
import app.modelo.ClaseVuelo;

import app.repositorios.RepositorioVueloLlegada;
import app.repositorios.RepositorioPuerta;
import app.repositorios.RepositorioAvion;
import app.repositorios.RepositorioClaseVuelo;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@RestController    // Clase controlador
@RequestMapping(path="/VuelosLlegada") // URL del servicio comienza con /agaport
public class VueloLlegadaController{

    @Autowired
    private RepositorioVueloLlegada vueloLlegadaRepo;
    @Autowired
    private RepositorioPuerta puertaRepo;
    @Autowired
    private RepositorioAvion avionRepo;
    @Autowired
    private RepositorioClaseVuelo claseVueloRepo;
	@CrossOrigin
    @PostMapping(path="/insertar")
	public @ResponseBody String agregarVueloLlegada (@RequestParam Date horaLlegadaProg, @RequestParam Date horaLlegadaReal,
														@RequestParam int nivelCombustible, @RequestParam int nivelRiesgoClima, @RequestParam int nPersonas,
														@RequestParam int kEquipaje, @RequestParam int Estado, @RequestParam int idPuerta, @RequestParam int idAvion,
														@RequestParam int idClaseVuelo){

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
		v.setPuerta(p);
		v.setAvion(a);
		v.setClaseVuelo(cv);
		vueloLlegadaRepo.save(v);
		return "Guardado";
	}
	@CrossOrigin
	@PostMapping(path="/modificar")
	public @ResponseBody String modificarVueloLlegada (@RequestParam int idVueloLlegada,@RequestParam String horaLlegadaProg, @RequestParam String horaLlegadaReal,
														@RequestParam int nivelCombustible, @RequestParam int nivelRiesgoClima, @RequestParam int nPersonas,
														@RequestParam int kEquipaje, @RequestParam int Estado, @RequestParam int idPuerta, @RequestParam int idAvion,
														@RequestParam int idClaseVuelo){
		VueloLlegada v = vueloLlegadaRepo.findById(idVueloLlegada).get();
		Puerta p = puertaRepo.findById(idPuerta).get();
		Avion a = avionRepo.findById(idAvion).get();
		ClaseVuelo cv = claseVueloRepo.findById(idClaseVuelo).get();
		Date horaProg = Date.valueOf(horaLlegadaProg);
		Date horaReal = Date.valueOf(horaLlegadaReal);
		v.setHoraLlegadaProg(horaProg);
		v.setHoraLlegadaReal(horaReal);
		v.setNivelCombustible(nivelCombustible);
		v.setNivelRiesgoClima(nivelRiesgoClima);
		v.setNPersonas(nPersonas);
		v.setKEquipaje(kEquipaje);
		v.setEstado(Estado);
		v.setPuerta(p);
		v.setAvion(a);
		v.setClaseVuelo(cv);
		vueloLlegadaRepo.save(v);
		return "Modificado";
	}
	@CrossOrigin
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<VueloLlegada> listarVuelosLlegada() {
        Iterable<VueloLlegada> listarVuelosLlegada = vueloLlegadaRepo.findAll();
        ArrayList<VueloLlegada> listaExistentes = new ArrayList<VueloLlegada>();
        for(VueloLlegada vl: listarVuelosLlegada){
            if (vl.getBorrado()==0){
                listaExistentes.add(vl);
            }
        }
        return listaExistentes;
	}
	@CrossOrigin
	@PostMapping(path="/eliminar")
	public @ResponseBody String eliminarVueloLlegada (@RequestParam int idVueloLlegada){
		VueloLlegada v = vueloLlegadaRepo.findById(idVueloLlegada).get();
		v.setBorrado(1);
		return "Eliminado";
	}
} 