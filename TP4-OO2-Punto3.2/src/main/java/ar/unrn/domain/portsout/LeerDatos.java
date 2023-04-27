package ar.unrn.domain.portsout;

import java.util.List;

public interface LeerDatos {

	List<Concurso> todosLosConcursos() throws InfrastructureExceptions;

	Concurso find(int idConcurso) throws InfrastructureExceptions;
}
