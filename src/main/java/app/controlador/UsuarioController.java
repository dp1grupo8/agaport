package app.controlador;

import app.modelo.Usuario;
import app.modelo.Permiso;

import app.repositorios.RepositorioPermiso;
import app.repositorios.RepositorioUsuario;

import java.util.ArrayList;

public class UsuarioController{

	public String agregarUsuario(RepositorioUsuario usuarioRepo, RepositorioPermiso permisosRepo, int DNI, String Password, String Nombres, int idPermiso){

		Permiso p= permisosRepo.findById(idPermiso).get();								
		Usuario u = new Usuario();
        u.setDNI(DNI);
        u.setNombres(Nombres);
		u.setPassword(Password);
		u.setPermiso(p);
		usuarioRepo.save(u);
		return "Guardado";
	}

	public Iterable<Usuario> listarUsuarios(RepositorioUsuario usuarioRepo){
        Iterable<Usuario> listaUsuarios = usuarioRepo.findAll();
        ArrayList<Usuario> listaExistentes = new ArrayList<Usuario>();
        for(Usuario u: listaUsuarios){
            if (u.getBorrado()==0){
                listaExistentes.add(u);
            }
        }
        return listaExistentes;
    }

	public String modificarUsuario(RepositorioUsuario usuarioRepo, RepositorioPermiso permisosRepo, int idUsuario, int DNI, String Password, String Nombres, int idPermiso){
		Usuario u = usuarioRepo.findById(idUsuario).get();
		Permiso p = permisosRepo.findbyId(idPermiso).get();
		u.setDNI(DNI);
        u.setNombres(Nombres);
		u.setPassword(Password);
		u.setPermiso(p);
		usuarioRepo.saveorUpdate(u);
		return "Modificado";
	}

	public String eliminarUsuario(RepositorioUsuario usuarioRepo, int idPermiso){
		Usuario u = usuarioRepo.findById(idUsuario).get();
		u.setBorrado(1);
		return "Eliminado";

	}

}

