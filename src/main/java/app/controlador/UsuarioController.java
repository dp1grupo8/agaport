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
import app.modelo.Usuario;
import app.repositorios.RepositorioPermiso;
import app.repositorios.RepositorioUsuario;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@RestController    // Clase controlador
@RequestMapping(path="/usuarios") // URL del servicio comienza con /agaport
public class UsuarioController {
	@Autowired // Bean llamado RepositorioUsuarios
	private RepositorioUsuario usuarioRepo;
	@Autowired // Bean llamado RepositorioUsuarios
	private RepositorioPermiso permisosRepo;
	//USUARIOS
	//------------------------------------------------------------------------------------------------------//
	@CrossOrigin
	@PostMapping(path="/insertar") // Map SOLO GET 
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
	@CrossOrigin
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Usuario> listarUsuarios() {
		// Regresa el JSON
        Iterable<Usuario> listaUsuarios = usuarioRepo.findAll();
        ArrayList<Usuario> listaExistentes = new ArrayList<Usuario>();
        for(Usuario a: listaUsuarios){
            if (a.getBorrado()==0){
            	Usuario n = new Usuario();
        		n.setDNI(a.getDNI());
		        n.setNombres(a.getNombres());
				n.setPassword(a.getPassword());
				n.setPermiso(a.getPermiso())
				n.setBorrado(0);
				listaExistentes.add(n);
                //listaExistentes.add(a);
            }
        }
        return listaExistentes;
	}
	@CrossOrigin
	@PostMapping(path="/modificar") // Map SOLO GET 
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
	@CrossOrigin
	@PostMapping(path="/eliminar")
	public @ResponseBody String eliminarUsuario (@RequestParam int DNI){
		Usuario u = usuarioRepo.findById(DNI).get();
		u.setBorrado(1);
		return "Eliminado";
	}
}