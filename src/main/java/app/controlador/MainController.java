package app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.modelo.Usuario;
import app.modelo.Permiso;
import app.repositorios.RepositorioPermiso;
import app.repositorios.RepositorioUsuario;


@Controller    // Clase controlador
@RequestMapping(path="/agaport") // URL del servicio comienza con /agaport
public class MainController {
	@Autowired // Bean llamado RepositorioUsuarios
	private RepositorioUsuario usuarioRepo;
	@Autowired
	private RepositorioPermiso permisosRepo;

	//USUARIOS
	//------------------------------------------------------------------------------------------------------//

	@GetMapping(path="/usuarios/add") // Map SOLO GET 
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
		return "Saved";
	}

	@GetMapping(path="/usuarios/listar")
	public @ResponseBody Iterable<Usuario> listarUsuarios() {
		// Regresa el JSON
		return usuarioRepo.findAll();
	}

	//PERMISOS
	//------------------------------------------------------------------------------------------------------//
	@GetMapping(path="/permisos/add") // Map SOLO GET 
	public @ResponseBody String agregarPermiso (@RequestParam String Descripcion,
												@RequestParam int Numero) {
		// @ResponseBody string es la respuesta, no el nombre
		// @RequestParam es un parametro de la request
		//VERIFICACION PERMISO EXISTENTE
		Permiso p = new Permiso();
		p.setDescripcion(Descripcion);
		p.setNumero(Numero);
		permisosRepo.save(p);
		return "Saved";
	}

	@GetMapping(path="/permisos/listar")
	public @ResponseBody Iterable<Permiso> listarPermisos() {
		// Regresa el JSON
		return permisosRepo.findAll();
	}
}