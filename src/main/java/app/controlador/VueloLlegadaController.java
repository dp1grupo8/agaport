package app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.Scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.modelo.VueloLlegada;
import app.modelo.Puerta;
import app.modelo.Avion;
import app.modelo.ClaseVuelo;
import app.modelo.Aerolinea;
import app.modelo.Prioridad;

import app.repositorios.RepositorioVueloLlegada;
import app.repositorios.RepositorioPuerta;
import app.repositorios.RepositorioAvion;
import app.repositorios.RepositorioClaseVuelo;

import java.util.Date;
import java.util.ArrayList;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;
import java.lang.Math;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.json.*;


@CrossOrigin
@RestController
@Configuration
@EnableScheduling    // Clase controlador
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

    private static final Logger logger = LoggerFactory.getLogger(VueloLlegadaController.class);
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
	public @ResponseBody String modificarVueloLlegada (@RequestParam int idVueloLlegada,@RequestParam Date horaLlegadaProg, @RequestParam Date horaLlegadaReal,
														@RequestParam int nivelCombustible, @RequestParam int nivelRiesgoClima, @RequestParam int nPersonas,
														@RequestParam int kEquipaje, @RequestParam int Estado, @RequestParam int idPuerta, @RequestParam int idAvion,
														@RequestParam int idClaseVuelo){
		VueloLlegada v = vueloLlegadaRepo.findById(idVueloLlegada).get();
		Puerta p = puertaRepo.findById(idPuerta).get();
		Avion a = avionRepo.findById(idAvion).get();
		ClaseVuelo cv = claseVueloRepo.findById(idClaseVuelo).get();
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
		return "Modificado";
	}
	@CrossOrigin
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<VueloLlegada> listarVuelosLlegada() {
        Iterable<VueloLlegada> listarVuelosLlegada = vueloLlegadaRepo.findAll();
        ArrayList<VueloLlegada> listaExistentes = new ArrayList<VueloLlegada>();
        for(VueloLlegada vl: listarVuelosLlegada){
            if (vl.getBorrado()==0 && (vl.getPuerta() != null)){
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
	@GetMapping(path="/registrarVuelos")
	public @ResponseBody Integer registrarVuelos (){
		RestTemplate restTemplate = new RestTemplate();
		String urlString = "http://demo4498234.mockable.io/vuelosAPI1";
		ResponseEntity<String> response = restTemplate.getForEntity(urlString, String.class);
		String jsonInput = response.getBody();

		ArrayList<Date> horasLlegada = new ArrayList<Date>();
		ArrayList<String> codLlegada = new ArrayList<String>();
		ArrayList<String> avionesLlegada = new ArrayList<String>();
		ArrayList<Integer> pasajerosLlegada = new ArrayList<Integer>();
		int contador =0;
		JSONArray outerObject = new JSONArray(jsonInput);

		for (int i = 0, size = outerObject.length(); i < size; i++){
			//System.out.println("ls");
			JSONObject objectInArray = outerObject.getJSONObject(i);
			//System.out.println("ds");
			String[] elementNames = JSONObject.getNames(objectInArray);
			//System.out.println("rs");
			//obtener fecha actual del sistema
			SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy");
			Date fechaActual = new Date(System.currentTimeMillis());
			//convertir fecha actual a string
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			String fechaActualString = dateFormat.format(fechaActual);

			for (String elementName : elementNames){
				//corroboracion de que no existe el vuel

				if(elementName.equals("arrivalTime")){
					String value = objectInArray.getString(elementName);
					//concatenerlo con la hora de llegada			
					String horaLlegadaString = fechaActualString.concat(" ").concat(value);
					//convertir a formato Date
					SimpleDateFormat formatter2=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
					try{
						Date horaLlegada = formatter2.parse(horaLlegadaString);
						//System.out.println(horaLlegadaString);
						horasLlegada.add(horaLlegada);
					}catch (Exception e){
						System.out.println(e);
					}
				}

				if(elementName.equals("flightNumber")){
					String codVuelo = objectInArray.getString(elementName);
					//System.out.println(codVuelo);
					codLlegada.add(codVuelo);
				}
				if(elementName.equals("airplaneCode")){
					String codAvion = objectInArray.getString(elementName);
					//System.out.println(codAvion);
					avionesLlegada.add(codAvion);
				}
				if(elementName.equals("passengerNumber")){
					Integer cantidad = objectInArray.getInt(elementName);
					//System.out.println(cantidad);
					pasajerosLlegada.add(cantidad);
				}
			}		
			
		}

		//corroboracion de que no existen los vuelos
		Iterable<VueloLlegada> listarVuelosLlegada = vueloLlegadaRepo.findAll();
        ArrayList<VueloLlegada> listaExistentes = new ArrayList<VueloLlegada>();
        ArrayList<Integer> idsEliminar = new ArrayList<Integer>();

        for(VueloLlegada vl: listarVuelosLlegada){
            if (vl.getBorrado()==0 && vl.getEstado()!=4){
            	Avion a = vl.getAvion();
            	Integer codAvion = a.getIdAvion();
            	//System.out.println(codAvion);

            	for(int i= 0; i<avionesLlegada.size();i++){
            		if((Integer.parseInt(avionesLlegada.get(i))) == codAvion){
            			idsEliminar.add(i);
            			pasajerosLlegada.set(i,0);
            		}
            	}

                listaExistentes.add(vl);
            }
        }

        ArrayList<Date> hLlegada = new ArrayList<Date>();
		ArrayList<String> cLlegada = new ArrayList<String>();
		ArrayList<String> aLlegada = new ArrayList<String>();
		ArrayList<Integer> pLlegada = new ArrayList<Integer>();

        for (int i = 0; i<pasajerosLlegada.size(); i++){
        	if(pasajerosLlegada.get(i) !=0){
        		hLlegada.add(horasLlegada.get(i));
        		cLlegada.add(codLlegada.get(i));
        		aLlegada.add(avionesLlegada.get(i));
        		pLlegada.add(pasajerosLlegada.get(i));
        	}
        }

        System.out.println(hLlegada.size());

       	//mandarlos a encolar
        
		for (int i = 0; i<cLlegada.size(); i++){

			Avion a = avionRepo.findById(Integer.parseInt(aLlegada.get(i))).get();
			Aerolinea aero = a.getAerolinea();
			Prioridad pri = aero.getPrioridad();
			double randomDouble = Math.random();
			randomDouble = randomDouble * 4 + 1;
			int randomInt = (int) randomDouble;
			System.out.println(randomInt);
			ClaseVuelo cv = claseVueloRepo.findById(randomInt).get();
			VueloLlegada v = new VueloLlegada();
			v.setHoraLlegadaReal(hLlegada.get(i));
			v.setEstado(0);
			v.setAvion(a);
			v.setClaseVuelo(cv);
			v.setNPersonas(pLlegada.get(i));
			vueloLlegadaRepo.save(v);

			DateFormat dateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fechaString = dateFormat1.format(hLlegada.get(i));

			String respuesta = encolarVuelo (v.getIdVuelo(),fechaString,Integer.toString(pLlegada.get(i))
												,Integer.toString(pri.getIdPrioridad()));

		}
		



		return contador;		
	}




	@CrossOrigin
	@GetMapping(path="/encolarVuelo")
	public @ResponseBody String encolarVuelo (@RequestParam int idVuelo, @RequestParam String TiempoLlegada, @RequestParam String nPersonas,
												@RequestParam String nPrioridad){
		JSONObject request = new JSONObject();
		//JSONObject postRequest = new JSONObject();

		
		request.put("idVuelo", idVuelo);
		request.put("TiempoLlegada", TiempoLlegada);
		request.put("NPersonas", nPersonas);
		request.put("NPrioridad", nPrioridad);
		System.out.println(request.toString());
		String requestString = request.toString();

		/*
		request.put("idVuelo", 5);
		request.put("TiempoLlegada", "2019-06-05 13:22:00");
		request.put("NPersonas", 100);
		request.put("NPrioridad", 2);
		*/
		RestTemplate restTemplate = new RestTemplate();

		//String requestString = request.toString();

		System.out.println(1);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(requestString, headers);
		System.out.println(2);
		//
		String urlString = "http://200.16.7.178/AGAPYTHON/agapython/encolarVuelo";
		System.out.println(3);
		String postRequest = restTemplate.postForObject(urlString, entity, String.class);
		System.out.println(4);
		return postRequest;
		
	}

	@CrossOrigin
	@GetMapping(path="/asignarPuertas")
	public @ResponseBody void asignarPuertas(){

		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://200.16.7.178/AGAPYTHON/agapython/asignarVuelos";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		String jsonInput = response.getBody();
	}

	@CrossOrigin
	@GetMapping(path="/asignarAterrizaje")
	public @ResponseBody String asignarAterrizaje(){
		//obtener fecha actual del sistema
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss HH:mm:ss");
		Date fechaActual = new Date(System.currentTimeMillis());
		//convertir fecha actual a string
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss HH:mm:ss");
		String fechaActualString = dateFormat.format(fechaActual);

		Iterable<VueloLlegada> listarVuelosLlegada = vueloLlegadaRepo.findAll();

        for(VueloLlegada vl: listarVuelosLlegada){
            if (vl.getBorrado()==0 && vl.getEstado()==2){
            	Date horaLlegada = vl.getHoraLlegadaReal();
            	if(fechaActual.compareTo(horaLlegada) >= 0){
            		vl.setEstado(3);
            		vueloLlegadaRepo.save(vl);
            		Puerta p = vl.getPuerta();
            		int idPuerta = p.getIdPuerta();
            		Puerta p1 = new Puerta();
            		p1 = puertaRepo.findById(idPuerta).get();
	      			p1.setEstado(2);
	      			puertaRepo.save(p1);     			

            	}
            }
        }

        return "OK";
	}

	/*	
	@CrossOrigin
	@GetMapping(path="/rVuelos")
	@Scheduled(fixedRate = 45000)
	public @ResponseBody void rVuelos(){
		int rVuelo = registrarVuelos();
		//System.out.println("aiuda");
	}

	@CrossOrigin
	@GetMapping(path="/aVuelos")
	@Scheduled(fixedRate = 60000)
	public @ResponseBody void aVuelos(){
		asignarPuertas();
		//System.out.println("aiuda");
	}

	@CrossOrigin
	@GetMapping(path="/flujo")
	public @ResponseBody String flujoVuelo(){
		rVuelos();
		aVuelos();
		return "oks";
	}
	*/

	@CrossOrigin
	@GetMapping(path="/eliminarVuelos")
	public @ResponseBody void eliminarVuelos(){
		//obtener fecha actual del sistema
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss HH:mm:ss");
		Date fechaActual = new Date(System.currentTimeMillis());

		Iterable<VueloLlegada> listarVuelosLlegada = vueloLlegadaRepo.findAll();

        for(VueloLlegada vl: listarVuelosLlegada){
            if (vl.getBorrado()==0 && vl.getEstado()==3){
            	Date horaLlegada = vl.getHoraLlegadaReal();
            	long diff = fechaActual.getTime() - horaLlegada.getTime();
            	long diffSeconds = diff / 1000;

            	if(diffSeconds >= 2700.00){
            		vl.setEstado(4);
            		vueloLlegadaRepo.save(vl);
            		Puerta p = vl.getPuerta();
            		int idPuerta = p.getIdPuerta();
            		Puerta p1 = new Puerta();
            		p1 = puertaRepo.findById(idPuerta).get();
	      			p1.setEstado(1);
	      			puertaRepo.save(p1);  

            	}
            }
        }
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
	      			p.setEstado(3);
	      			puertaRepo.save(p);
	      			v.setEstado(2);
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