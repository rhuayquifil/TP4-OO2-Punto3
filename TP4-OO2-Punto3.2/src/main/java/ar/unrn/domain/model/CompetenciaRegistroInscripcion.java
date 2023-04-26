package ar.unrn.domain.model;

import java.util.ArrayList;
import java.util.List;

import ar.unrn.domain.portsin.DomainExceptions;
import ar.unrn.domain.portsin.RegistroInscripcion;
import ar.unrn.domain.portsout.Concurso;
import ar.unrn.domain.portsout.GuardarDatos;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.LeerDatos;
import ar.unrn.domain.portsout.Participante;

public class CompetenciaRegistroInscripcion implements RegistroInscripcion {

	private LeerDatos lectorDatos;
	private GuardarDatos guardaDatos;

	public CompetenciaRegistroInscripcion(LeerDatos lectorDatos, GuardarDatos guardaDatos) {
		super();
		this.lectorDatos = lectorDatos;
		this.guardaDatos = guardaDatos;
	}

	@Override
	public void inscribirACompeticion(String id, String apellido, String nombre, String celular, String email,
			int idConcurso) throws DomainExceptions {
		try {
			guardaDatos.inscribir(new Participante(id, apellido, nombre, celular, email, idConcurso + 1));
		} catch (InfrastructureExceptions e) {
			throw new DomainExceptions("CompetenciaRegistroInscripcion " + e.getMessage());
		}
	}

	@Override
	public List<String> listaNombreConcursos() throws DomainExceptions {
		List<String> listaNombreConcurso = new ArrayList<String>();

		try {
			agregarNombresALista(listaNombreConcurso);
		} catch (InfrastructureExceptions e) {
			throw new DomainExceptions("CompetenciaRegistroInscripcion " + e.getMessage());
		}

		return listaNombreConcurso;
	}

	private void agregarNombresALista(List<String> listaNombreConcurso) throws InfrastructureExceptions {
		for (Concurso concurso : lectorDatos.todosLosConcursos()) {
			listaNombreConcurso.add(concurso.nombre());
		}
	}
}
