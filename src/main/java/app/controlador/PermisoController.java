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

import app.modelo.Permiso;
import app.repositorios.RepositorioPermiso;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@RestController    // Clase controlador
@RequestMapping(path="/permisos") // URL del servicio comienza con /agaport
public class PermisoController {

    @Autowired
	private RepositorioPermiso permisosRepo;
    @CrossOrigin
    @PostMapping(path="/insertar") // Map SOLO GET 
	public @ResponseBody String agregarPermiso (@RequestParam String Descripcion,
												@RequestParam int Numero) {
        Permiso p = new Permiso();
		p.setDescripcion(Descripcion);
        p.setNumero(Numero);
        p.setBorrado(0);
		permisosRepo.save(p);
        return "Guardado";
    }
    @CrossOrigin
    @GetMapping(path="/listar")
	public @ResponseBody Iterable<Permiso> listarPermisos() {
        Iterable<Permiso> listaPermisos = permisosRepo.findAll();
        ArrayList<Permiso> listaExistentes = new ArrayList<Permiso>();
        for(Permiso a: listaPermisos){
            if (a.getBorrado()==0){
                listaExistentes.add(a);
            }
        }
        return listaExistentes;
    }
    @CrossOrigin
	@PostMapping(path="/modificar")
	public @ResponseBody String modificarPermiso(@RequestParam int idPermiso, @RequestParam String Descripcion,
															@RequestParam int Numero) {
        Permiso p = permisosRepo.findById(idPermiso).get();	
        p.setDescripcion(Descripcion);
        p.setNumero(Numero);
        permisosRepo.save(p);
        return "Modificado";
    }   
    @CrossOrigin 
	@PostMapping(path="/eliminar")
	public @ResponseBody String eliminarPermiso(@RequestParam int idPermiso) {
        Permiso p = permisosRepo.findById(idPermiso).get();	
        p.setBorrado(1);
        permisosRepo.save(p);
        return "Eliminado";
    }    
}
