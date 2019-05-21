package app.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import app.modelo.Permiso;
import app.modelo.Usuario;
import app.repositorios.RepositorioPermiso;
import app.repositorios.RepositorioUsuario;

@Controller    // Clase controlador
@RequestMapping(path="/usuarios") // URL del servicio comienza con /agaport
public class UsuarioController {
	@Autowired // Bean llamado RepositorioUsuarios
	private RepositorioUsuario usuarioRepo;
	@Autowired // Bean llamado RepositorioUsuarios
	private RepositorioPermiso permisosRepo;
	//USUARIOS
	//------------------------------------------------------------------------------------------------------//
	@GetMapping(path="/insertar") // Map SOLO GET 
	public @ResponseBody String agregarUsuario (@RequestParam int DNI, @RequestParam String Password, @RequestParam String Nombres, 
												@RequestParam int idPermiso) {
		// @ResponseBody string es la respuesta, no el nombre
		// @RequestParam es un parametro de la request
		//VERIFICACION PERMISO EXISTENTE
		Permiso p = permisosRepo.findById(idPermiso).get();								
		Usuario n = new Usuario();
        n.setDNI(DNI);
        n.setNombres(Nombres);
		n.setPassword(Password);
		n.setPermiso(p);
		usuarioRepo.save(n);
		return "Guardado";
	}

	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Usuario> listarUsuarios() {
		// Regresa el JSON
        Iterable<Usuario> listaUsuarios = usuarioRepo.findAll();
        ArrayList<Usuario> listaExistentes = new ArrayList<Usuario>();
        for(Usuario a: listaUsuarios){
            if (a.getBorrado()==0){
                listaExistentes.add(a);
            }
        }
        return listaExistentes;
	}
	@GetMapping(path="/modificar") // Map SOLO GET 
	public @ResponseBody String modificarUsuario (@RequestParam int DNI, @RequestParam String Password, @RequestParam String Nombres, 
												@RequestParam int idPermiso) {
		// @ResponseBody string es la respuesta, no el nombre
		// @RequestParam es un parametro de la request
		//VERIFICACION PERMISO EXISTENTE
		Permiso p = permisosRepo.findById(idPermiso).get();								
		Usuario a = usuarioRepo.findById(DNI).get();
        a.setNombres(Nombres);
		a.setPassword(Password);
		a.setPermiso(p);
		usuarioRepo.save(a);
		return "Guardado";
	}

	@GetMapping(path="/eliminar")
	public @ResponseBody String eliminarUsuario (@RequestParam int DNI){
		Usuario u = usuarioRepo.findById(DNI).get();
		u.setBorrado(1);
		return "Eliminado";
	}
}