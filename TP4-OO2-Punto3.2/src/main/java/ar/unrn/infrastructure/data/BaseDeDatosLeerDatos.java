package ar.unrn.infrastructure.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

			while (resul.next()) {
				Date dateInicioCompetencia = resul.getDate("fechaInicioCompetencia");

				// TENES QUE LEER CONVERTIR LOS DATE EN LOCALDATE

				Date dateFinCompetencia = resul.getDate("fechaFinCompetencia");
//				LocalDate fechaFinCompetencia = dateFinCompetencia.toInstant().atZone(ZoneId.systemDefault())
//						.toLocalDate();
				System.out.println(dateInicioCompetencia + " " + dateFinCompetencia);
//
//				listaConcursos.add(new Concurso(String.valueOf(resul.getInt("id")), resul.getString("nombre"),
//						fechaInicioCompetencia, fechaFinCompetencia));

//				System.out.println(listaConcursos);
			}

		} catch (SQLException e) {
			throw new InfrastructureExceptions("error al procesar consulta");
		}

		return listaConcursos;
	}

}
