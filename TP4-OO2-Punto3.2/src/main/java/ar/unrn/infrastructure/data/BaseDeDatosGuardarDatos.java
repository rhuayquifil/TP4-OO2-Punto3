package ar.unrn.infrastructure.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ar.unrn.domain.portsout.Concurso;
import ar.unrn.domain.portsout.GuardarDatos;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.Participante;
import ar.unrn.domain.portsout.Propiedades;

public class BaseDeDatosGuardarDatos implements GuardarDatos {

	private Propiedades properties;
	private String sqlInsertInscripcion;

	public BaseDeDatosGuardarDatos(Propiedades properties, String sqlInsertInscripcion) {
		this.properties = properties;
		this.sqlInsertInscripcion = sqlInsertInscripcion;
	}

	@Override
	public void inscribir(Participante participante) throws InfrastructureExceptions {

		BaseDeDatosLeerDatos lectorDatos = new BaseDeDatosLeerDatos(properties);

		try (Connection conn = DriverManager.getConnection(properties.get("url"), properties.get("usuario"),
				properties.get("contrasena"));
				java.sql.PreparedStatement state = conn.prepareStatement(sqlInsertInscripcion)) {

//			"INSERT INTO participante (id, apellido, nombre, telefono, email, id_concurso)" + "VALUES (?, ?, ?, ?, ?, ?);"

			state.setInt(1, Integer.valueOf(participante.id()));

			state.setString(2, participante.lastName());

			state.setString(3, participante.name());

			state.setString(4, participante.phone());

			state.setString(5, participante.email());

			Concurso consurso = lectorDatos.find(participante.idConcurso());
			state.setInt(6, Integer.valueOf(consurso.id()));

			int cantidad = state.executeUpdate();

			if (cantidad <= 0) {
				throw new InfrastructureExceptions("error al ingresar Registro");
			}

		} catch (SQLException | NumberFormatException e) {
			throw new InfrastructureExceptions("error al prosesar consulta");
		}
	}

}
