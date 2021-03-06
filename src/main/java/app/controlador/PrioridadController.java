package app.controlador;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.modelo.Prioridad;

import app.repositorios.RepositorioPrioridad;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@RestController    // Clase controlador
@RequestMapping(path="/prioridades") // URL del servicio comienza con /agaport
public class PrioridadController{

    @Autowired
    private RepositorioPrioridad prioridadRepo;
	@CrossOrigin
    @PostMapping(path="/insertar") // Map SOLO GET 
	public @ResponseBody String agregarPrioridad (@RequestParam String Descripcion, @RequestParam int nPrioridad){
		Prioridad p = new Prioridad();
		p.setDescripcion(Descripcion);
		p.setNPrioridad(nPrioridad);
		p.setBorrado(0);
		prioridadRepo.save(p);
		return "Guardado";
	}
	@CrossOrigin
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Prioridad> listarPrioridades() {
        Iterable<Prioridad> listaPrioridades = prioridadRepo.findAll();
        ArrayList<Prioridad> listaExistentes = new ArrayList<Prioridad>();
        for(Prioridad p: listaPrioridades){
            if (p.getBorrado()==0){
                listaExistentes.add(p);
            }
        }
        return listaExistentes;
    }
	@CrossOrigin
    @PostMapping(path="/modificar")
	public @ResponseBody String modificarPrioridad (@RequestParam String Descripcion, @RequestParam int nPrioridad, @RequestParam int idPrioridad){
		Prioridad p = prioridadRepo.findById(idPrioridad).get();
		p.setDescripcion(Descripcion);
		p.setNPrioridad(nPrioridad);
		prioridadRepo.save(p);
		return "Modificado";
	}
	@CrossOrigin
	@PostMapping(path="/eliminar")
	public @ResponseBody String eliminarPrioridad (@RequestParam int idPrioridad){
		Prioridad p = prioridadRepo.findById(idPrioridad).get(); 
		p.setBorrado(1);
		prioridadRepo.save(p);
		return "Eliminado";
	}
} 