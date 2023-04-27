package ar.unrn.infrastructure.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.domain.portsout.Concurso;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.LeerDatos;

public class DiscoLeerDatos implements LeerDatos {

	private List<Concurso> listaConcursos;
	private String urlArchivo;

	public DiscoLeerDatos(String urlArchivo) throws InfrastructureExceptions {
		super();
		this.urlArchivo = urlArchivo;

	}

	@Override
	public List<Concurso> todosLosConcursos() throws InfrastructureExceptions {
		this.listaConcursos = new ArrayList<>();

		try {
			String cadena;

			FileReader f = new FileReader(urlArchivo);

			BufferedReader b = new BufferedReader(f);

			leerConcursos(b);

			b.close();
		} catch (FileNotFoundException e) {
			throw new InfrastructureExceptions("DiscoAlmacenDeDatos FileNotFoundException");
		} catch (IOException e) {
			throw new InfrastructureExceptions("DiscoAlmacenDeDatos IOException");
		} catch (NullPointerException e) {
			throw new InfrastructureExceptions("DiscoAlmacenDeDatos NullPointerException");
		}

		return listaConcursos;
	}

	private void leerConcursos(BufferedReader b) throws IOException {
		String cadena;
		while ((cadena = b.readLine()) != null) {
			String[] parts = cadena.split(", ");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

			LocalDate fechaInicioCompetencia = LocalDate.parse(parts[2], formatter);
			LocalDate fechaFinCompetencia = LocalDate.parse(parts[3], formatter);

			listaConcursos.add(new Concurso(parts[0], parts[1], fechaInicioCompetencia, fechaFinCompetencia));
		}
	}

	@Override
	public Concurso find(int idConcurso) throws InfrastructureExceptions {
		// TODO Auto-generated method stub
		return null;
	}
}
