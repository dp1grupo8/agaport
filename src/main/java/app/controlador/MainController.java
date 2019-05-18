package app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.modelo.Usuario;
import app.modelo.Aerolinea;
import app.modelo.Avion;
import app.modelo.ClaseVuelo;
import app.modelo.Permiso;
import app.repositorios.RepositorioAerolinea;
import app.repositorios.RepositorioAvion;
import app.repositorios.RepositorioClaseVuelo;
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
	private RepositorioAerolinea aerolineaRepo;	
	@Autowired
	private RepositorioAvion avionRepo;
	@Autowired
	private RepositorioClaseVuelo claseVueloRepo;

	private PermisoController controladorPermisos;
	private AerolineaController controladorAerolineas;
	private AvionController controladorAviones;
	private ClaseVueloController controladorClasesVuelo;
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
		return "Guardado";
	}

	@GetMapping(path="/usuarios/listar")
	public @ResponseBody Iterable<Usuario> listarUsuarios() {
		// Regresa el JSON
		return usuarioRepo.findAll();
	}

	//PERMISOS
	//------------------------------------------------------------------------------------------------------//
	@GetMapping(path="/permisos/insertar") // Map SOLO GET 
	public @ResponseBody String agregarPermiso (@RequestParam String Descripcion,
												@RequestParam int Numero) {
		return controladorPermisos.agregarPermiso(permisosRepo, Descripcion, Numero);
	}
	@GetMapping(path="/permisos/listar")
	public @ResponseBody Iterable<Permiso> listarPermisos() {
		return permisosRepo.findAll();
	}
	@GetMapping(path="/permisos/modificar")
	public @ResponseBody String modificarPermiso(@RequestParam int idPermiso, @RequestParam String Descripcion,
															@RequestParam int Numero) {
		return controladorPermisos.modificarPermiso(permisosRepo,idPermiso,Descripcion,Numero);
	}
	@GetMapping(path="/permisos/eliminar")
	public @ResponseBody String eliminarPermiso(@RequestParam int idPermiso) {
		return controladorPermisos.eliminarPermiso(permisosRepo,idPermiso);
	}
	//AEROLINEAS
	//------------------------------------------------------------------------------------------------------//
	@GetMapping(path="/aerolineas/insertar") // Map SOLO GET 
	public @ResponseBody String agregarAerolinea (@RequestParam String Nombre) {
		return controladorAerolineas.agregarAerolinea(aerolineaRepo, Nombre);
	}
	@GetMapping(path="/aerolineas/listar")
	public @ResponseBody Iterable<Aerolinea> listarAerolineas() {
		return aerolineaRepo.findAll();
	}
	@GetMapping(path="/aerolineas/modificar")
	public @ResponseBody String modificarAerolinea(@RequestParam int idAerolinea, @RequestParam String Nombre) {
		return controladorAerolineas.modificarAerolinea(aerolineaRepo,idAerolinea,Nombre);
	}
	@GetMapping(path="/aerolineas/eliminar")
	public @ResponseBody String eliminarAerolinea(@RequestParam int idAerolinea) {
		return controladorAerolineas.eliminarAerolinea(aerolineaRepo,idAerolinea);
	}
	//AVIONES
	//------------------------------------------------------------------------------------------------------//
	@GetMapping(path="/aviones/insertar") // Map SOLO GET 
	public @ResponseBody String agregarAvion (@RequestParam String Placa, 
												@RequestParam int CapacidadMax, @RequestParam int CargaMax, 
												@RequestParam int CombustibleMax) {
		return controladorAviones.agregarAvion(avionRepo,Placa, CapacidadMax, CargaMax, CombustibleMax);
	}
	@GetMapping(path="/aviones/listar")
	public @ResponseBody Iterable<Avion> listarAviones() {
		return avionRepo.findAll();
	}
	@GetMapping(path="/aviones/modificar")
	public @ResponseBody String modificarAvion(@RequestParam int idAvion, @RequestParam String Placa, 
												@RequestParam int CapacidadMax, @RequestParam int CargaMax, 
												@RequestParam int CombustibleMax) {
		return controladorAviones.modificarAvion(avionRepo,idAvion,Placa,CapacidadMax,CargaMax,CombustibleMax);
	}
	@GetMapping(path="/aviones/eliminar")
	public @ResponseBody String eliminarAvion(@RequestParam int idAvion) {
		return controladorAviones.eliminarAvion(avionRepo,idAvion);
	}
	//CLASES VUELOS
	//------------------------------------------------------------------------------------------------------//
	@GetMapping(path="/clasesVuelo/insertar") // Map SOLO GET 
	public @ResponseBody String agregarClaseVuelo (@RequestParam String Descripcion, @RequestParam int NPrioridad) {
		return controladorClasesVuelo.agregarClaseVuelo(claseVueloRepo,Descripcion,NPrioridad);
	}
	@GetMapping(path="/clasesVuelo/listar")
	public @ResponseBody Iterable<ClaseVuelo> listarClasesVuelo() {
		return claseVueloRepo.findAll();
	}
	@GetMapping(path="/clasesVuelo/modificar")
	public @ResponseBody String modificarClaseVuelo(@RequestParam int idClaseVuelo, @RequestParam String Descripcion, 
													@RequestParam int NPrioridad) {
		return controladorClasesVuelo.modificarClaseVuelo(claseVueloRepo,idClaseVuelo,Descripcion,NPrioridad);
	}
	@GetMapping(path="/clasesVuelo/eliminar")
	public @ResponseBody String eliminarClaseVuelo(@RequestParam int idClaseVuelo) {
		return controladorClasesVuelo.eliminarClaseVuelo(claseVueloRepo,idClaseVuelo);
	}
	//PRIORIDADES
	//------------------------------------------------------------------------------------------------------//

	//PUERTAS
	//------------------------------------------------------------------------------------------------------//

	//VUELOS DE LLEGADA
	//------------------------------------------------------------------------------------------------------//

}