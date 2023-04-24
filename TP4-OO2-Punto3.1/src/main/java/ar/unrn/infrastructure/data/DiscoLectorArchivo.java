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

	public DiscoLectorArchivo(String urlArchivo) throws DomainExceptions {
		try {
			String cadena;

			FileReader f = new FileReader(urlArchivo);

			BufferedReader b = new BufferedReader(f);

			// TENES QUE LEER LOS DATOS DEL ARCHIVO

			while ((cadena = b.readLine()) != null) {
				String[] parts = cadena.split(", ");
				System.out.println(parts);
			}

			b.close();
		} catch (FileNotFoundException e) {
			throw new DomainExceptions("DiscoLectorArchivo FileNotFoundException");
		} catch (IOException e) {
			throw new DomainExceptions("DiscoLectorArchivo IOException");
		} catch (NullPointerException e) {
			throw new DomainExceptions("DiscoLectorArchivo NullPointerException");
		}
	}

	@Override
	public List<Concurso> todosLosConcursos() {
		listaConcursos = new ArrayList<Concurso>();

		return null;
	}

}
