package app.controlador;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import app.modelo.Puerta;

import app.repositorios.RepositorioPuerta;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@Controller    // Clase controlador
@RequestMapping(path="/puertas") // URL del servicio comienza con /agaport
public class PuertaController{

    @Autowired
    private RepositorioPuerta puertaRepo;
	@GetMapping(path="/insertar") // Map SOLO GET 
	public @ResponseBody String agregarPuerta (@RequestParam int Tipo, @RequestParam double distanciaASalida, @RequestParam int flujoPersonas,
												@RequestParam int Estado){
		Puerta p = new Puerta();
		p.setTipo(Tipo);
		p.setDistanciaASalida(distanciaASalida);
		p.setFlujoPersonas(flujoPersonas);
		p.setEstado(Estado);
		puertaRepo.save(p);
		return "Guardado";
	}

	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Puerta> listarPuertas() {
        Iterable<Puerta> listaPuertas = puertaRepo.findAll();
        ArrayList<Puerta> listaExistentes = new ArrayList<Puerta>();
        for(Puerta p: listaPuertas){
            if (p.getBorrado()==0){
                listaExistentes.add(p);
            }
        }
        return listaExistentes;
    }

	@GetMapping(path="/modificar")
	public @ResponseBody String modificarPuerta(@RequestParam int idPuerta,@RequestParam int Tipo, @RequestParam double distanciaASalida, @RequestParam int flujoPersonas,
												@RequestParam int Estado){
		Puerta p = puertaRepo.findById(idPuerta).get();
		p.setTipo(Tipo);
		p.setDistanciaASalida(distanciaASalida);
		p.setFlujoPersonas(flujoPersonas);
		p.setEstado(Estado);
		puertaRepo.save(p);
		return "Modificado";
	}
	@GetMapping(path="/eliminar")
	public @ResponseBody String eliminarPuerta(@RequestParam int idPuerta){
		Puerta p = puertaRepo.findById(idPuerta).get();
		p.setBorrado(1);
		return "Eliminado";
	}
}