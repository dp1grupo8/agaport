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
	private RepositorioPrioridad prioridadRepo;
	@Autowired
	private RepositorioPuerta puertaRepo;
	@Autowired
	private RepositorioVueloLlegada vueloLlegadaRepo;
	@Autowired
	private RepositorioAerolinea aerolineaRepo;	
	@Autowired
	private RepositorioAvion avionRepo;
	@Autowired
	private RepositorioClaseVuelo claseVueloRepo;


	private UsuarioController controladorUsuario;
	private PrioridadController controladorPrioridad;
	private PuertaController controladorPuerta;
	private VueloLlegadaController controladorVueloLlegada;

	


	private PermisoController controladorPermisos;
	private AerolineaController controladorAerolineas;
	private AvionController controladorAviones;
	private ClaseVueloController controladorClasesVuelo;
	//USUARIOS
	//------------------------------------------------------------------------------------------------------//

	@GetMapping(path="/usuarios/insertar") // Map SOLO GET 
	public @ResponseBody String agregarUsuario (@RequestParam int DNI, @RequestParam String Password, @RequestParam String Nombres, 
												@RequestParam int idPermiso) {

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
	@GetMapping(path="/permisos/insertar") // Map SOLO GET 
	public @ResponseBody String agregarPermiso (@RequestParam String Descripcion,
												@RequestParam int Numero) {
		return controladorPermisos.agregarPermiso(permisosRepo, Descripcion, Numero);
	}
	@GetMapping(path="/permisos/listar")
	public @ResponseBody Iterable<Permiso> listarPermisos() {
		return controladorPermisos.listarPermisos(permisosRepo);
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
		return controladorAerolineas.listarAerolineas(aerolineaRepo);
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
		return controladorAviones.listarAviones(avionRepo);
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
		return controladorClasesVuelo.listarAerolineas(claseVueloRepo);
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
	
}