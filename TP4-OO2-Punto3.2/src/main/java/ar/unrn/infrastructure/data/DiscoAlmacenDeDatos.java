package ar.unrn.infrastructure.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.domain.portsout.AlmacenDeDatos;
import ar.unrn.domain.portsout.Concurso;
import ar.unrn.domain.portsout.DomainExceptions;
import ar.unrn.domain.portsout.Participante;

public class DiscoAlmacenDeDatos implements AlmacenDeDatos {

	private List<Concurso> listaConcursos;
	private String urlArchivoConcursos;
	private String urlArchivoInscriptos;

	public DiscoAlmacenDeDatos(String urlArchivoConcursos, String urlArchivoInscriptos) throws DomainExceptions {
		super();
		this.urlArchivoConcursos = urlArchivoConcursos;
		this.urlArchivoInscriptos = urlArchivoInscriptos;

	}

	@Override
	public List<Concurso> todosLosConcursos() throws DomainExceptions {
		this.listaConcursos = new ArrayList<>();

		try {
			String cadena;

			FileReader f = new FileReader(urlArchivoConcursos);

			BufferedReader b = new BufferedReader(f);

			while ((cadena = b.readLine()) != null) {
				String[] parts = cadena.split(", ");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

				LocalDate fechaInicioCompetencia = LocalDate.parse(parts[2], formatter);
				LocalDate fechaFinCompetencia = LocalDate.parse(parts[3], formatter);

				listaConcursos.add(new Concurso(parts[0], parts[1], fechaInicioCompetencia, fechaFinCompetencia));
			}

			b.close();
		} catch (FileNotFoundException e) {
			throw new DomainExceptions("DiscoLectorArchivo FileNotFoundException");
		} catch (IOException e) {
			throw new DomainExceptions("DiscoLectorArchivo IOException");
		} catch (NullPointerException e) {
			throw new DomainExceptions("DiscoLectorArchivo NullPointerException");
		}

		return listaConcursos;
	}

	@Override
	public void inscribir(Participante participante) throws DomainExceptions {
		try {
			FileWriter fileWriter = new FileWriter(urlArchivoInscriptos, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(participante.id() + ", " + participante.lastName() + ", " + participante.name() + ", "
					+ participante.phone() + ", " + participante.email() + ", " + participante.idConcurso());
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			throw new DomainExceptions();
		}
	}
}
