package ar.unrn.main;

import javax.swing.SwingUtilities;

import ar.unrn.domain.model.CompetenciaRegistroInscripcion;
import ar.unrn.domain.portsin.DomainExceptions;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.infrastructure.data.DiscoGuardarDatos;
import ar.unrn.infrastructure.data.DiscoLeerDatos;
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

	private void start() throws DomainExceptions, InfrastructureExceptions {
		new RadioCompetition(new CompetenciaRegistroInscripcion(
				new DiscoLeerDatos("C:\\Users\\ezehu\\git\\TP4-OO2-Punto3\\concursos.txt"),
				new DiscoGuardarDatos("C:\\Users\\ezehu\\git\\TP4-OO2-Punto3\\inscriptos.txt")));
	}
}
