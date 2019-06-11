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

import app.modelo.Puerta;

import app.repositorios.RepositorioPuerta;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;

import java.io.IOException;

@CrossOrigin
@RestController    // Clase controlador
@RequestMapping(path="/puertas") // URL del servicio comienza con /agaport
public class PuertaController{

    @Autowired
	private RepositorioPuerta puertaRepo;
	@CrossOrigin
	@PostMapping(path="/insertar") // Map SOLO GET 
	public @ResponseBody String agregarPuerta (@RequestParam int Tipo, @RequestParam double distanciaASalida, @RequestParam int flujoPersonas,
												@RequestParam int Estado){
		Puerta p = new Puerta();
		p.setTipo(Tipo);
		p.setDistanciaASalida(distanciaASalida);
		p.setFlujoPersonas(flujoPersonas);
		p.setEstado(Estado);
		puertaRepo.save(p);
		return "Guardado";
	}
	@CrossOrigin
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Puerta> listarPuertas() {
        Iterable<Puerta> listaPuertas = puertaRepo.findAll();
        ArrayList<Puerta> listaExistentes = new ArrayList<Puerta>();
        for(Puerta p: listaPuertas){
            if (p.getBorrado()==0){
                listaExistentes.add(p);
            }
        }
        return listaExistentes;
    }
	@CrossOrigin
	@PostMapping(path="/modificar")
	public @ResponseBody String modificarPuerta(@RequestParam int idPuerta,@RequestParam int Tipo, @RequestParam double distanciaASalida, @RequestParam int flujoPersonas,
												@RequestParam int Estado){
		Puerta p = puertaRepo.findById(idPuerta).get();
		p.setTipo(Tipo);
		p.setDistanciaASalida(distanciaASalida);
		p.setFlujoPersonas(flujoPersonas);
		p.setEstado(Estado);
		puertaRepo.save(p);
		return "Modificado";
	}
	@CrossOrigin
	@PostMapping(path="/eliminar")
	public @ResponseBody String eliminarPuerta(@RequestParam int idPuerta){
		Puerta p = puertaRepo.findById(idPuerta).get();
		p.setBorrado(1);
		return "Eliminado";
	}
	/*
	@CrossOrigin
	@GetMapping(path="/listarPuertasAsignadas") Iterable<Puerta> listado(){
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://200.16.7.178/AGAPYTHON/agapython/listarPuertas";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		String jsonInput = response.getBody();

		ArrayList<Puerta> puertas= new ArrayList<Puerta> ();

		JSONObject outerObject = new JSONObject(jsonInput);
		JSONArray jsonArray = outerObject.getJSONArray("Puertas");


		ObjectMapper mapper = new ObjectMapper();
		
		for (int i = 0, size = jsonArray.length(); i < size; i++){
			JSONObject objectInArray = jsonArray.getJSONObject(i);
			String jasonString = objectInArray.toString();
			try {
				System.out.println(jasonString);
				Puerta p = mapper.readValue(jasonString, Puerta.class);
				puertas.add(p);
			}
			catch (IOException e) {
            	e.printStackTrace();
        	}
		}
		
		return puertas;

	}
	*/
	@CrossOrigin
	@GetMapping(path="/listarTodasPuertas")
	public @ResponseBody Iterable<Puerta> listarPuertasCompleto() {
        Iterable<Puerta> listaPuertas = puertaRepo.findAll();
        return listaPuertas;
    }
}