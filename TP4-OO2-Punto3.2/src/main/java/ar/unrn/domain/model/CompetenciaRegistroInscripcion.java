package ar.unrn.domain.model;

import java.util.List;

import ar.unrn.domain.portsin.RegistroInscripcion;
import ar.unrn.domain.portsout.Concurso;
import ar.unrn.domain.portsout.DomainExceptions;
import ar.unrn.domain.portsout.LectorArchivo;

public class CompetenciaRegistroInscripcion implements RegistroInscripcion {

	private LectorArchivo discoLectorArchivo;

	public CompetenciaRegistroInscripcion(LectorArchivo discoLectorArchivo) {
		super();
		this.discoLectorArchivo = discoLectorArchivo;
	}

	@Override
	public List<Concurso> todosLosConcursos() throws DomainExceptions {
		// mejora 1
		return discoLectorArchivo.todosLosConcursos();
	}
}
