package app.repositorios;
import app.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;

// Spring AUTOMATICAMENTE implementa este repositorio como un bean
// CRUD se refiere a Create, Read, Update, Delete
public interface RepositorioUsuario extends CrudRepository<Usuario, Integer> {

}
