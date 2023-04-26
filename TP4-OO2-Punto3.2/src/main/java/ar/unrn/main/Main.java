package ar.unrn.main;

import javax.swing.SwingUtilities;

import ar.unrn.domain.model.CompetenciaRegistroInscripcion;
import ar.unrn.domain.portsout.DomainExceptions;
import ar.unrn.infrastructure.data.DiscoAlmacenDeDatos;
import ar.unrn.infrastructure.ui.RadioCompetition;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Main().start();
				} catch (Exception e) {
					// log exception...
					System.out.println(e);
				}
			}
		});
	}

	private void start() throws DomainExceptions {
		new RadioCompetition(new CompetenciaRegistroInscripcion(
				new DiscoAlmacenDeDatos("C:\\Users\\ezehu\\git\\TP4-OO2-Punto3\\concursos.txt",
						"C:\\Users\\ezehu\\git\\TP4-OO2-Punto3\\inscriptos.txt")));
	}
}
