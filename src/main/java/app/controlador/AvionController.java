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

import app.modelo.Aerolinea;
import app.modelo.Avion;
import app.repositorios.RepositorioAerolinea;
import app.repositorios.RepositorioAvion;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@RestController    // Clase controlador
@RequestMapping(path="/aviones") // URL del servicio comienza con /agaport
public class AvionController {

	@Autowired
    private RepositorioAvion avionRepo;

    @Autowired
    private RepositorioAerolinea aerolineaRepo;
    @CrossOrigin
    @PostMapping(path="/insertar") // Map SOLO GET 
    public @ResponseBody String agregarAvion (@RequestParam String Placa, @RequestParam int CapacidadMax, 
                                                @RequestParam int CargaMax, @RequestParam int CombustibleMax,
                                                @RequestParam int idAerolinea) {        
                                                    
        Aerolinea p = aerolineaRepo.findById(idAerolinea).get();	
        Avion a = new Avion();
        a.setPlaca(Placa);
        a.setCapacidadMax(CapacidadMax);
        a.setCargaMax(CargaMax);
        a.setCombustibleMax(CombustibleMax);
        a.setAerolinea(p);
        a.setBorrado(0);
		avionRepo.save(a);
        return "Guardado";
    }
    @CrossOrigin
    @GetMapping(path="/listar")
    public @ResponseBody Iterable<Avion> listarAviones(){
        Iterable<Avion> listaAviones = avionRepo.findAll();
        ArrayList<Avion> listaExistentes = new ArrayList<Avion>();
        for(Avion a: listaAviones){
            if (a.getBorrado()==0){
                listaExistentes.add(a);
            }
        }
        return listaExistentes;
    }
    @CrossOrigin
    @PostMapping(path="/modificar")
	public @ResponseBody String modificarAvion(@RequestParam int idAvion, @RequestParam String Placa, 
												@RequestParam int CapacidadMax, @RequestParam int CargaMax, 
												@RequestParam int CombustibleMax, @RequestParam int idAerolinea) {
        Avion a = avionRepo.findById(idAvion).get();
        Aerolinea p = aerolineaRepo.findById(idAerolinea).get();
        a.setPlaca(Placa);
        a.setCapacidadMax(CapacidadMax);
        a.setCargaMax(CargaMax);
        a.setCombustibleMax(CombustibleMax);
        a.setAerolinea(p);
        avionRepo.save(a);
        return "Modificado";
    }
    @CrossOrigin    
	@PostMapping(path="/eliminar")
	public @ResponseBody String eliminarAvion(@RequestParam int idAvion) {
        Avion a = avionRepo.findById(idAvion).get();
        a.setBorrado(1);
        avionRepo.save(a);
        return "Eliminado";
    }    
}
