package app.repositorios;

import app.modelo.Prioridad;
import org.springframework.data.repository.CrudRepository;

// Spring AUTOMATICAMENTE implementa este repositorio como un bean
// CRUD se refiere a Create, Read, Update, Delete
public interface RepositorioPrioridad extends CrudRepository<Prioridad, Integer> {

}