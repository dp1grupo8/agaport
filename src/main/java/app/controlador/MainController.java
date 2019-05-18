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
	@Autowired
	private RepositorioPrioridad prioridadRepo;
	@Autowired
	private RepositorioPuerta puertaRepo;
	@Autowired
	private RepositorioVueloLlegada vueloLlegadaRepo;


	private UsuarioController controladorUsuario;
	private PrioridadController controladorPrioridad;
	private PuertaController controladorPuerta;
	private VueloLlegadaController controladorVueloLlegada;

	//USUARIOS
	//------------------------------------------------------------------------------------------------------//

	@GetMapping(path="/usuarios/insertar") // Map SOLO GET 
	public @ResponseBody String agregarUsuario (@RequestParam int DNI, @RequestParam String Password, @RequestParam String Nombres, 
												@RequestParam int idPermiso) {
		// @ResponseBody string es la respuesta, no el nombre
		// @RequestParam es un parametro de la request
		//VERIFICACION PERMISO EXISTENTE
		/*
		Permiso p = permisosRepo.findById(idPermiso).get();								
		Usuario n = new Usuario();
        n.setDNI(DNI);
        n.setNombres(Nombres);
		n.setPassword(Password);
		n.setPermiso(p);
		usuarioRepo.save(n);
		*/
		return controladorUsuario.agregarUsuario(usuarioRepo, permisosRepo, DNI, Password, Nombres, idPermiso);
	}

	@GetMapping(path="/usuarios/listar")
	public @ResponseBody Iterable<Usuario> listarUsuarios() {
		// Regresa el JSON
		return usuarioRepo.findAll();
	}

	@GetMapping(path="/usuarios/modificar")
	public @ResponseBody String modificarUsuario (@RequestParam int DNI, @RequestParam String Password, @RequestParam String Nombres, 
													@RequestParam int idPermiso, @RequestParam idUsuario){
		return controladorUsuario.modificarUsuario(usuarioRepo, permisosRepo, idUsuario, DNI, Password, Nombres, idPermiso);
	}

	@GetMapping(path="/usuarios/eliminar")
	public @ResponseBody String eliminarUsuario (@RequestParam int idUsuario){
		return controladorUsuario.eliminarUsuario(usuarioRepo, idUsuario);
	}

	//PRIORIDAD
	//------------------------------------------------------------------------------------------------------//
	
	@GetMapping(path="/prioridades/insertar") // Map SOLO GET 
	public @ResponseBody String agregarPrioridad (@RequestParam int Descripcion, @RequestParam int nPrioridad){
		return controladorPrioridad.agregarPrioridad(prioridadRepo, Descripcion, nPrioridad);
	}

	@GetMapping(path="/prioridades/modificar")
	public @ResponseBody String modificarPrioridad (@RequestParam int Descripcion, @RequestParam int nPrioridad, @RequestParam int idPrioridad){
		return controladorPrioridad.modificarPrioridad(prioridadRepo, Descripcion, nPrioridad,idPrioridad);
	}
	@GetMapping(path="/prioridades/eliminar")
	public @ResponseBody String eliminarPrioridad (@RequestParam int idPrioridad){
		return controladorPrioridad.eliminarUsuario(prioridadRepo, idPrioridad);
	}

	//VUELOS DE LLEGADA
	//------------------------------------------------------------------------------------------------------//
	
	@GetMapping(path="/vuelosLlegada/insertar") // Map SOLO GET 
	public @ResponseBody String agregarVueloLlegada (@RequestParam Date horaLlegadaProg, @RequestParam Date horaLlegadaReal,
														@RequestParam int nivelCombustible, @RequestParam int nivelRiesgoClima, @RequestParam int nPersonas,
														@RequestParam int kEquipaje, @RequestParam int Estado, @RequestParam int idPuerta, @RequestParam int idAvion,
														@RequestParam int idClaseVuelo){
		return controladorPrioridad.agregarVueloLlegada(vueloLlegadaRepo, horaLlegadaProg, horaLlegadaReal, nivelCombustible, nivelRiesgoClima, nPersonas,
														kEquipaje, Estado, idPuerta, idAvion, idClaseVuelo);
	}

	@GetMapping(path="/vuelosLlegada/modificar")
	public @ResponseBody String modificarVueloLlegada (@RequestParam Date horaLlegadaProg, @RequestParam Date horaLlegadaReal,
														@RequestParam int nivelCombustible, @RequestParam int nivelRiesgoClima, @RequestParam int nPersonas,
														@RequestParam int kEquipaje, @RequestParam int Estado, @RequestParam int idPuerta, @RequestParam int idAvion,
														@RequestParam int idClaseVuelo, @RequestParam int idVueloLlegada){
		return controladorPrioridad.modificarVueloLlegada(vueloLlegadaRepo, horaLlegadaProg, horaLlegadaReal, nivelCombustible, nivelRiesgoClima, nPersonas,
														kEquipaje, Estado, idPuerta, idAvion, idClaseVuelo, idVueloLlegada);
	}
	@GetMapping(path="/vuelosLlegada/eliminar")
	public @ResponseBody String eliminarVueloLlegada (@RequestParam int idVueloLlegada){
		return controladorPrioridad.eliminarVueloLlegada(vueloLlegadaRepo, idVueloLlegada);
	}
	//PUERTA
	//------------------------------------------------------------------------------------------------------//
	
	@GetMapping(path="/puertas/insertar") // Map SOLO GET 
	public @ResponseBody String agregarPuerta (@RequestParam int Tipo, @RequestParam double distanciaASalida, @RequestParam int flujoPersonas,
												@RequestParam int Estado){
		return controladorPuerta.agregarPuerta(puertaRepo, Tipo, distanciaASalida, flujoPersonas, Estado);
	}

	@GetMapping(path="/puertas/modificar")
	public @ResponseBody String modificarPuerta(@RequestParam int Tipo, @RequestParam double distanciaASalida, @RequestParam int flujoPersonas,
												@RequestParam int Estado, @RequestParam int idPuerta){
		return controladorPrioridad.modificarPrioridad(puertaRepo, Tipo, distanciaASalida, flujoPersonas, Estado, idPuerta);
	}
	@GetMapping(path="/puertas/eliminar")
	public @ResponseBody String eliminarPuerta(@RequestParam int idPuerta){
		return controladorPrioridad.eliminarUsuario(puertaRepo, idPuerta);
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