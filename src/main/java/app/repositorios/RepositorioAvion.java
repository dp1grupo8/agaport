package app.repositorios;

import app.modelo.Avion;
import org.springframework.data.repository.CrudRepository;

// Spring AUTOMATICAMENTE implementa este repositorio como un bean
// CRUD se refiere a Create, Read, Update, Delete
public interface RepositorioAvion extends CrudRepository<Avion, Integer> {

}