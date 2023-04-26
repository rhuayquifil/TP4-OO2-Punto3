package ar.unrn.domain.portsin;

import java.util.List;

import ar.unrn.domain.portsout.DomainExceptions;

public interface RegistroInscripcion {

	void inscribirACompeticion(String id, String apellido, String nombre, String celular, String email, int idConcurso)
			throws DomainExceptions;

	List<String> listaNombreConcursos() throws DomainExceptions;

}
