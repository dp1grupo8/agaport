package app.controlador;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import app.modelo.ClaseVuelo;
import app.repositorios.RepositorioClaseVuelo;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@Controller    // Clase controlador
@RequestMapping(path="/ClasesVuelos") // URL del servicio comienza con /agaport
public class ClaseVueloController {

	@Autowired
	private RepositorioClaseVuelo claseVueloRepo;

	@GetMapping(path="/insertar") // Map SOLO GET 
	public @ResponseBody String agregarClaseVuelo (@RequestParam String Descripcion, @RequestParam int NPrioridad) {
        ClaseVuelo c = new ClaseVuelo();
        c.setDescripcion(Descripcion);
        c.setNPrioridad(NPrioridad);
        c.setBorrado(0);
		claseVueloRepo.save(c);
        return "Guardado";
    }
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<ClaseVuelo> listarClasesVuelo() {
        Iterable<ClaseVuelo> listaClaseVuelo = claseVueloRepo.findAll();
        ArrayList<ClaseVuelo> listaExistentes = new ArrayList<ClaseVuelo>();
        for(ClaseVuelo a: listaClaseVuelo){
            if (a.getBorrado()==0){
                listaExistentes.add(a);
            }
        }
        return listaExistentes;
    }
	@GetMapping(path="/modificar")
	public @ResponseBody String modificarClaseVuelo(@RequestParam int idClaseVuelo, @RequestParam String Descripcion, 
													@RequestParam int NPrioridad) {
        ClaseVuelo c = claseVueloRepo.findById(idClaseVuelo).get();
        c.setDescripcion(Descripcion);
        c.setNPrioridad(NPrioridad);
        return "Modificado";
    }    
	@GetMapping(path="/eliminar")
	public @ResponseBody String eliminarClaseVuelo(@RequestParam int idClaseVuelo) {
        ClaseVuelo c = claseVueloRepo.findById(idClaseVuelo).get();	
        c.setBorrado(1);
        return "Eliminado";
    }    
}
