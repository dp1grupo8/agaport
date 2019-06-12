package app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.modelo.VueloLlegada;
import app.modelo.Puerta;
import app.modelo.Avion;
import app.modelo.ClaseVuelo;

import app.repositorios.RepositorioVueloLlegada;
import app.repositorios.RepositorioPuerta;
import app.repositorios.RepositorioAvion;
import app.repositorios.RepositorioClaseVuelo;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import org.json.*;


@CrossOrigin
@RestController    // Clase controlador
@RequestMapping(path="/VuelosLlegada") // URL del servicio comienza con /agaport
public class VueloLlegadaController{

    @Autowired
    private RepositorioVueloLlegada vueloLlegadaRepo;
    @Autowired
    private RepositorioPuerta puertaRepo;
    @Autowired
    private RepositorioAvion avionRepo;
    @Autowired
    private RepositorioClaseVuelo claseVueloRepo;
	@CrossOrigin
    @PostMapping(path="/insertar")
	public @ResponseBody String agregarVueloLlegada (@RequestParam Date horaLlegadaProg, @RequestParam Date horaLlegadaReal,
														@RequestParam int nivelCombustible, @RequestParam int nivelRiesgoClima, @RequestParam int nPersonas,
														@RequestParam int kEquipaje, @RequestParam int Estado, @RequestParam int idPuerta, @RequestParam int idAvion,
														@RequestParam int idClaseVuelo){

		Puerta p = puertaRepo.findById(idPuerta).get();
		Avion a = avionRepo.findById(idAvion).get();
		ClaseVuelo cv = claseVueloRepo.findById(idClaseVuelo).get();
		VueloLlegada v = new VueloLlegada();
		v.setHoraLlegadaProg(horaLlegadaProg);
		v.setHoraLlegadaReal(horaLlegadaReal);
		v.setNivelCombustible(nivelCombustible);
		v.setNivelRiesgoClima(nivelRiesgoClima);
		v.setNPersonas(nPersonas);
		v.setKEquipaje(kEquipaje);
		v.setEstado(Estado);
		v.setPuerta(p);
		v.setAvion(a);
		v.setClaseVuelo(cv);
		vueloLlegadaRepo.save(v);
		return "Guardado";
	}
	@CrossOrigin
	@PostMapping(path="/modificar")
	public @ResponseBody String modificarVueloLlegada (@RequestParam int idVueloLlegada,@RequestParam String horaLlegadaProg, @RequestParam String horaLlegadaReal,
														@RequestParam int nivelCombustible, @RequestParam int nivelRiesgoClima, @RequestParam int nPersonas,
														@RequestParam int kEquipaje, @RequestParam int Estado, @RequestParam int idPuerta, @RequestParam int idAvion,
														@RequestParam int idClaseVuelo){
		VueloLlegada v = vueloLlegadaRepo.findById(idVueloLlegada).get();
		Puerta p = puertaRepo.findById(idPuerta).get();
		Avion a = avionRepo.findById(idAvion).get();
		ClaseVuelo cv = claseVueloRepo.findById(idClaseVuelo).get();
		Date horaProg = Date.valueOf(horaLlegadaProg);
		Date horaReal = Date.valueOf(horaLlegadaReal);
		v.setHoraLlegadaProg(horaProg);
		v.setHoraLlegadaReal(horaReal);
		v.setNivelCombustible(nivelCombustible);
		v.setNivelRiesgoClima(nivelRiesgoClima);
		v.setNPersonas(nPersonas);
		v.setKEquipaje(kEquipaje);
		v.setEstado(Estado);
		v.setPuerta(p);
		v.setAvion(a);
		v.setClaseVuelo(cv);
		vueloLlegadaRepo.save(v);
		return "Modificado";
	}
	@CrossOrigin
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<VueloLlegada> listarVuelosLlegada() {
        Iterable<VueloLlegada> listarVuelosLlegada = vueloLlegadaRepo.findAll();
        ArrayList<VueloLlegada> listaExistentes = new ArrayList<VueloLlegada>();
        for(VueloLlegada vl: listarVuelosLlegada){
            if (vl.getBorrado()==0){
                listaExistentes.add(vl);
            }
        }
        return listaExistentes;
	}
	@CrossOrigin
	@PostMapping(path="/eliminar")
	public @ResponseBody String eliminarVueloLlegada (@RequestParam int idVueloLlegada){
		VueloLlegada v = vueloLlegadaRepo.findById(idVueloLlegada).get();
		v.setBorrado(1);
		vueloLlegadaRepo.save(v);
		return "Eliminado";
	}

	@CrossOrigin
	@GetMapping(path="/enviarVuelo")
	public @ResponseBody String envioVuelos (){
		Iterable<VueloLlegada> listarVuelosLlegada = vueloLlegadaRepo.findAll();
        ArrayList<VueloLlegada> listaVuelosNoAsignados = new ArrayList<VueloLlegada>();
        System.out.println("sadfadsf");
        for(VueloLlegada vl: listarVuelosLlegada){
        	System.out.println("entro1");
        	int estates = vl.getEstado();
        	System.out.println(estates);
            if (vl.getEstado()==0){
            	RestTemplate restTemplate = new RestTemplate();
            	String url = "http://200.16.7.178/AGAPYTHON/agapython/encolarVuelo";
            	System.out.println("entrada2");
            	/*
				JSONObject myObj = {"idVuelo":null, "TiempoLlegada":null, "NPersonas":null, "NPrioridad":null};
				myObj.idVuelo = vl.getIdVuelo();
				myObj.TiempoLlegada = vl.getHoraLlegadaReal();
				myObj.nPersonas = vl.getNPersonas();
				myObj.NPrioridad = 1;

            	String respuesta = restTemplate.postForObject(url,vl,String.class);
            	System.out.println("entrada3");

                listaVuelosNoAsignados.add(vl);
                */
            }
        }

		
		return "hola";
	}
	

	@CrossOrigin
	@GetMapping(path="/listarAsignaciones")
	public @ResponseBody Iterable<VueloLlegada> prueba (){
		//con esto se lee el json que manda python
		RestTemplate restTemplate = new RestTemplate();
		//String fooResourceUrl = "http://200.16.7.178/AGAPYTHON/agapython/listarAsignaciones";
		String fooResourceUrl = "http://200.16.7.178/AGAPYTHON/agapython/listarAsignaciones";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		String jsonInput = response.getBody();
		
		ArrayList<Integer> prueba = new ArrayList<Integer>();
		ArrayList<VueloLlegada> vuelosAsignados = new ArrayList<VueloLlegada>();
		
		JSONObject outerObject = new JSONObject(jsonInput);
		JSONArray jsonArray = outerObject.getJSONArray("asignaciones");

		for (int i = 0, size = jsonArray.length(); i < size; i++){
      		JSONObject objectInArray = jsonArray.getJSONObject(i);	      
	    	String[] elementNames = JSONObject.getNames(objectInArray);
	    	prueba.add(elementNames.length);

	    	Integer idPuerta;
	    	Integer idVueloAsignado;
	    	Puerta p = new Puerta();
	    	VueloLlegada v = new VueloLlegada();

	      	for (String elementName : elementNames){
	      		
	      		Integer value = objectInArray.getInt(elementName);
	      		System.out.println(elementName);
	      		System.out.println(value);
	      		
	      		/*
	      		//Integer value = Integer.parseInt(valueString);
	      		if(elementName.equals("idPuerta")){
	      			idPuerta = value;
	      			System.out.println("puerta");
	      			System.out.println(idPuerta);
	      			p = puertaRepo.findById(idPuerta).get();

	      		}
	      		if(elementName.equals("idVueloAsignado")){
	      			idVueloAsignado = value;
	      			System.out.println("vuelo");
	      			System.out.println(idVueloAsignado);
	      			v = vueloLlegadaRepo.findById(idVueloAsignado).get();
	      			v.setPuerta(p);
	      			vueloLlegadaRepo.save(v);
	      			vuelosAsignados.add(v);
	      		}
	      		*/
	      	
	      		if(elementName.equals("idPuerta")){
	      			idPuerta = value;
	      			//System.out.println("puerta");
	      			//System.out.println(idPuerta);
	      			p = puertaRepo.findById(idPuerta).get();
	      			v.setEstado(1);
	      			v.setPuerta(p);
	      			vueloLlegadaRepo.save(v);
	      			vuelosAsignados.add(v);

	      		}

	      		if(elementName.equals("idVueloAsignado")){
	      			idVueloAsignado = value;
	      			System.out.println("vuelo");
	      			System.out.println(idVueloAsignado);
	      			v = vueloLlegadaRepo.findById(idVueloAsignado).get();
	      			System.out.println("vueloEncontrado");
	      			
	      		}

	      		
	        //System.out.printf("name=%s, value=%s\n", elementName, value);
	      	}
	      //System.out.println();
    	}

		return vuelosAsignados;
	}

} 