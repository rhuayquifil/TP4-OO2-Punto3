package ar.unrn.infrastructure.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.domain.portsout.Concurso;
import ar.unrn.domain.portsout.DomainExceptions;
import ar.unrn.domain.portsout.LectorArchivo;

public class DiscoLectorArchivo implements LectorArchivo {

	private List<Concurso> listaConcursos;
	private String urlArchivoConcursos;
	private String urlArchivoInscriptos;

	public DiscoLectorArchivo(String urlArchivoConcursos, String urlArchivoInscriptos) throws DomainExceptions {
		super();
		this.urlArchivoConcursos = urlArchivoConcursos;
		this.urlArchivoInscriptos = urlArchivoInscriptos;

	}

	@Override
	public List<Concurso> todosLosConcursos() throws DomainExceptions {
		this.listaConcursos = new ArrayList<Concurso>();

		try {
			String cadena;

			FileReader f = new FileReader(urlArchivoConcursos);

			BufferedReader b = new BufferedReader(f);

			while ((cadena = b.readLine()) != null) {
				String[] parts = cadena.split(", ");
				listaConcursos.add(new Concurso(parts[1]));
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

}
