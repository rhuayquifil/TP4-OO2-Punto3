package ar.unrn.domain.portsin;

import java.util.List;

public interface RegistroInscripcion {

	void inscribirACompeticion(String id, String apellido, String nombre, String celular, String email, int idConcurso)
			throws DomainExceptions;

	// retorna los concursos a que estan activos para inscribir nuevos participantes
	List<String> listaNombreConcursosActivos() throws DomainExceptions;

}
