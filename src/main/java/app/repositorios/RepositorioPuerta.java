package app.repositorios;

import app.modelo.Puerta;
import org.springframework.data.repository.CrudRepository;

// Spring AUTOMATICAMENTE implementa este repositorio como un bean
// CRUD se refiere a Create, Read, Update, Delete
public interface RepositorioPuerta extends CrudRepository<Puerta, Integer> {

}