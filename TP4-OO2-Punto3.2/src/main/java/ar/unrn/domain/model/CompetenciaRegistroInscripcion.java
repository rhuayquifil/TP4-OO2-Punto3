package ar.unrn.domain.model;

import java.util.ArrayList;
import java.util.List;

import ar.unrn.domain.portsin.RegistroInscripcion;
import ar.unrn.domain.portsout.AlmacenDeDatos;
import ar.unrn.domain.portsout.Concurso;
import ar.unrn.domain.portsout.DomainExceptions;
import ar.unrn.domain.portsout.Participante;

public class CompetenciaRegistroInscripcion implements RegistroInscripcion {

	private AlmacenDeDatos almacenDeDatos;

	public CompetenciaRegistroInscripcion(AlmacenDeDatos almacenDeDatos) {
		super();
		this.almacenDeDatos = almacenDeDatos;
	}

	@Override
	public void inscribirACompeticion(String id, String apellido, String nombre, String celular, String email,
			int idConcurso) throws DomainExceptions {
		almacenDeDatos.inscribir(new Participante(id, apellido, nombre, celular, email, idConcurso + 1));
	}

	@Override
	public List<String> listaNombreConcursos() throws DomainExceptions {
		List<String> listaNombreConcurso = new ArrayList<String>();

		for (Concurso concurso : almacenDeDatos.todosLosConcursos()) {
			listaNombreConcurso.add(concurso.nombre());
		}
		return listaNombreConcurso;
	}
}
