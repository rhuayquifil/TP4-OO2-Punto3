package ar.unrn.domain.portsin;

import java.util.List;

import ar.unrn.domain.portsout.Concurso;
import ar.unrn.domain.portsout.DomainExceptions;

public interface RegistroInscripcion {

	List<Concurso> todosLosConcursos() throws DomainExceptions;

}
