package app.controlador;

import app.modelo.Puerta;

import app.repositorios.RepositorioPuerta;

public class PuertaController{

	public String agregarPuerta(RepositorioPuerta puertaRepo, int Tipo, double distanciaASalida, int flujoPersonas, int Estado){
		Puerta p = new Puerta();
		p.setTipo(Tipo);
		p.setDistanciaASalida(distanciaASalida);
		p.setFlujoPersonas(flujoPersonas);
		p.setEstado(Estado);
		puertaRepo.save(p);
		return "Guardado";
	}

	public Iterable<Puerta> listarUsuarios(RepositorioUsuario puertaRepo){
        Iterable<Puerta> listaPuertas = puertaRepo.findAll();
        ArrayList<Puerta> listaExistentes = new ArrayList<Puerta>();
        for(Puerta p: listaPuertas){
            if (p.getBorrado()==0){
                listaPuertas.add(p);
            }
        }
        return listaExistentes;
    }

	public String modificarPuerta(RepositorioPuerta puertaRepo, int Tipo, double distanciaASalida, int flujoPersonas, int Estado, int idPuerta){
		Puerta p = puertaRepo.findById(idPuerta).get();
		p.setTipo(Tipo);
		p.setDistanciaASalida(distanciaASalida);
		p.setFlujoPersonas(flujoPersonas);
		p.setEstado(Estado);
		puertaRepo.saveOrUpdate(p);
		return "Modificado";
	}

	public String eliminarPuerta(RepositorioPuerta puertaRepo, int idPuerta){
		Puerta p = puertaRepo.findById(idPuerta).get();
		p.setBorrado(1);
		return "Eliminado";
	}
}