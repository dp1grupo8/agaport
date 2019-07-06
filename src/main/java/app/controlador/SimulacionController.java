package app.controlador;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController    // Clase controlador
@RequestMapping(path="/simulacion") // URL del servicio comienza con /agaport
public class SimulacionController {

    @PostMapping(path="/iniciar") // Map SOLO GET 
	public @ResponseBody int iniciarSimulacion () {
        System.out.println("Inicio de Simulacion" );
        return 1;
    }
}
