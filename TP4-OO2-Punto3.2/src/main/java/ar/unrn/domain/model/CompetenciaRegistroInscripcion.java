package ar.unrn.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
			validarDatos(id, apellido, nombre, celular, email, idConcurso);
		} catch (InfrastructureExceptions | NullPointerException e) {
			throw new DomainExceptions(e.getMessage());
		}
	}

	private void validarDatos(String id, String apellido, String nombre, String celular, String email, int idConcurso)
			throws DomainExceptions, InfrastructureExceptions {
		if (validations(id, apellido, nombre, celular, email)) {
			guardaDatos.inscribir(new Participante(id, apellido, nombre, celular, email, idConcurso + 1));
			throw new DomainExceptions("Inscripcion Exitosa");
		}
	}

	private boolean validations(String id, String apellido, String nombre, String celular, String email)
			throws DomainExceptions {

		Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
		if (nombre.isEmpty()) {
			throw new DomainExceptions("Nombre no puede ser vacio");
		}

		Objects.requireNonNull(apellido, "El Apellido no puede ser nulo");
		if (apellido.isEmpty()) {
			throw new DomainExceptions("Apellido no puede ser vacio");
		}

		Objects.requireNonNull(id, "El DNI no puede ser nulo");
		if (id.isEmpty()) {
			throw new DomainExceptions("Dni no puede ser vacio");
		}

		Objects.requireNonNull(email, "El Email no puede ser nulo");
		if (!checkEmail(email)) {
			throw new DomainExceptions("Email debe ser valido");
		}

		Objects.requireNonNull(celular, "El celular no puede ser nulo");
		if (!checkPhone(celular)) {
			throw new DomainExceptions("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
		}
//		if (this.comboBox.getSelectedIndex() <= 0) {
//			JOptionPane.showMessageDialog(this.contentPane, "Debe elegir un Concurso");
//			return false;
//		}
		return true;
	}

	private boolean checkEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	private boolean checkPhone(String telefono) {
		String regex = "\\d{4}-\\d{6}";
		return telefono.matches(regex);
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
