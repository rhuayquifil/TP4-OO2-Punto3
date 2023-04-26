package ar.unrn.infrastructure.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ar.unrn.domain.portsout.GuardarDatos;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.Participante;

public class DiscoGuardarDatos implements GuardarDatos {

	private String urlArchivo;

	public DiscoGuardarDatos(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}

	@Override
	public void inscribir(Participante participante) throws InfrastructureExceptions {
		try {
			FileWriter fileWriter = new FileWriter(urlArchivo, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(participante.id() + ", " + participante.lastName() + ", " + participante.name() + ", "
					+ participante.phone() + ", " + participante.email() + ", " + participante.idConcurso());
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			throw new InfrastructureExceptions("inscribir DiscoAlmacenDeDatos");
		}
	}

}
