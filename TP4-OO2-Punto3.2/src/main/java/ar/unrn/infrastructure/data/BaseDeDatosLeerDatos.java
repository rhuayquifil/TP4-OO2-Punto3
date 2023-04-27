package ar.unrn.infrastructure.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ar.unrn.domain.portsout.Concurso;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.LeerDatos;
import ar.unrn.domain.portsout.Propiedades;

public class BaseDeDatosLeerDatos implements LeerDatos {

	private List<Concurso> listaConcursos;
	private Propiedades properties;

	public BaseDeDatosLeerDatos(Propiedades properties) {
		this.properties = properties;
	}

	@Override
	public List<Concurso> todosLosConcursos() throws InfrastructureExceptions {
		List<Concurso> listaConcursos = new ArrayList<Concurso>();

		try (Connection conn = DriverManager.getConnection(properties.get("url"), properties.get("usuario"),
				properties.get("contrasena"));
				java.sql.Statement sent = conn.createStatement();
				ResultSet resul = sent.executeQuery("SELECT * FROM concurso")) {

			leerResultado(listaConcursos, resul);

		} catch (SQLException e) {
			throw new InfrastructureExceptions("error al procesar consulta");
		}

		return listaConcursos;
	}

	private void leerResultado(List<Concurso> listaConcursos, ResultSet resul) throws SQLException {
		while (resul.next()) {
			Calendar calendar = Calendar.getInstance();

			// fecha inicio competencia
			Date dateInicioCompetencia = resul.getDate("fechaInicioCompetencia");

			calendar.setTime(dateInicioCompetencia);

			LocalDate fechaInicioCompetencia = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));

			// fecha fin competencia
			Date dateFinCompetencia = resul.getDate("fechaFinCompetencia");

			calendar.setTime(dateFinCompetencia);

			LocalDate fechaFinCompetencia = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));

			listaConcursos.add(new Concurso(String.valueOf(resul.getInt("id")), resul.getString("nombre"),
					fechaInicioCompetencia, fechaFinCompetencia));
		}
	}

	@Override
	public Concurso find(int idConcurso) throws InfrastructureExceptions {
		for (Concurso concurso : todosLosConcursos()) {
			if (Integer.valueOf(concurso.id()) == idConcurso) {
				return concurso;
			}
		}
		throw new InfrastructureExceptions("No se encontro concurso con id: " + idConcurso);
	}

}
