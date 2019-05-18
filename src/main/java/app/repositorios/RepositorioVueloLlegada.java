package app.repositorios;

import app.modelo.VueloLlegada;
import org.springframework.data.repository.CrudRepository;

// Spring AUTOMATICAMENTE implementa este repositorio como un bean
// CRUD se refiere a Create, Read, Update, Delete
public interface RepositorioVueloLlegada extends CrudRepository<VueloLlegada, Integer> {

}