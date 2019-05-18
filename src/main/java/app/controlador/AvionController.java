package app.controlador;

import app.modelo.Avion;
import app.repositorios.RepositorioAvion;

public class AvionController {
    public String agregarAvion(RepositorioAvion avionRepo, String Placa, int CapacidadMax, int CargaMax, int CombustibleMax){
        Avion a = new Avion();
        a.setPlaca(Placa);
        a.setCapacidadMax(CapacidadMax);
        a.setCargaMax(CargaMax);
        a.setCombustibleMax(CombustibleMax);
        a.setBorrado(0);
		avionRepo.save(a);
        return "Guardado";
    }
    public String modificarAvion(RepositorioAvion avionRepo,int id, String Placa, int CapacidadMax, 
                                int CargaMax, int CombustibleMax){
        Avion a = avionRepo.findById(id).get();
        a.setPlaca(Placa);
        a.setCapacidadMax(CapacidadMax);
        a.setCargaMax(CargaMax);
        a.setCombustibleMax(CombustibleMax);
        return "Modificado";
    }    
    public String eliminarAvion(RepositorioAvion avionRepo,int idAvion){
        Avion a = avionRepo.findById(idAvion).get();
        a.setBorrado(1);
        return "Eliminado";
    }    
}
