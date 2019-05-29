package app.controlador;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import app.modelo.Aerolinea;
import app.modelo.Prioridad;
import app.repositorios.RepositorioAerolinea;
import app.repositorios.RepositorioPrioridad;

@CrossOrigin
@Controller    // Clase controlador
@RequestMapping(path="/aerolineas") // URL del servicio comienza con /agaport
public class AerolineaController {

    @Autowired
    private RepositorioAerolinea aerolineaRepo;	
    
    @Autowired
    private RepositorioPrioridad prioridadRepo;
	//AEROLINEAS
    //------------------------------------------------------------------------------------------------------//
	@PostMapping(path="/insertar") // Map SOLO GET 
	public @ResponseBody String agregarAerolinea (@RequestParam String Nombre, @RequestParam int idPrioridad) {

        Prioridad p = prioridadRepo.findById(idPrioridad).get();		
        Aerolinea a = new Aerolinea();
        a.setNombre(Nombre);
        a.setPrioridad(p);
        a.setBorrado(0);
		aerolineaRepo.save(a);
        return "Guardado";
    }
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Aerolinea> listarAerolineas() {
        Iterable<Aerolinea> listaAerolineas = aerolineaRepo.findAll();
        ArrayList<Aerolinea> listaExistentes = new ArrayList<Aerolinea>();
        for(Aerolinea a: listaAerolineas){
            if (a.getBorrado()==0){
                listaExistentes.add(a);
            }
        }
        return listaExistentes;
    }
	@PostMapping(path="/modificar")
	public @ResponseBody String modificarAerolinea(@RequestParam int idAerolinea, @RequestParam String Nombre, @RequestParam int idPrioridad) {
        Prioridad p = prioridadRepo.findById(idPrioridad).get();		
        Aerolinea a = aerolineaRepo.findById(idAerolinea).get();	
        a.setNombre(Nombre);
        a.setPrioridad(p);
        return "Modificado";
    }    
	@PostMapping(path="/eliminar")
	public @ResponseBody String eliminarAerolinea(@RequestParam int idAerolinea) {
        Aerolinea a = aerolineaRepo.findById(idAerolinea).get();	
        a.setBorrado(1);
        return "Eliminado";
    }    
}
