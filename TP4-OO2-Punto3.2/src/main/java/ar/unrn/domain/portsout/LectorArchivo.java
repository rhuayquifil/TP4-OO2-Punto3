package ar.unrn.domain.portsout;

import java.util.List;

public interface LectorArchivo {
	List<Concurso> todosLosConcursos() throws DomainExceptions;
}
