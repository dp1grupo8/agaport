package app.controlador;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import app.modelo.Aerolinea;
import app.repositorios.RepositorioAerolinea;

@Controller    // Clase controlador
@RequestMapping(path="/aerolineas") // URL del servicio comienza con /agaport
public class AerolineaController {

    @Autowired
	private RepositorioAerolinea aerolineaRepo;	
	//AEROLINEAS
	//------------------------------------------------------------------------------------------------------//
	@GetMapping(path="/insertar") // Map SOLO GET 
	public @ResponseBody String agregarAerolinea (@RequestParam String Nombre) {
        Aerolinea a = new Aerolinea();
        a.setNombre(Nombre);
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
	@GetMapping(path="/modificar")
	public @ResponseBody String modificarAerolinea(@RequestParam int idAerolinea, @RequestParam String Nombre) {
        Aerolinea a = aerolineaRepo.findById(idAerolinea).get();	
        a.setNombre(Nombre);
        return "Modificado";
    }    
	@GetMapping(path="/eliminar")
	public @ResponseBody String eliminarAerolinea(@RequestParam int idAerolinea) {
        Aerolinea a = aerolineaRepo.findById(idAerolinea).get();	
        a.setBorrado(1);
        return "Eliminado";
    }    
}
