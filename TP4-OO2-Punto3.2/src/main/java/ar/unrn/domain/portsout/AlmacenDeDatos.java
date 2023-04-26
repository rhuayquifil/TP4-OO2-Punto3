package ar.unrn.domain.portsout;

import java.util.List;

public interface AlmacenDeDatos {
	List<Concurso> todosLosConcursos() throws DomainExceptions;

	void inscribir(Participante participante) throws DomainExceptions;
}
