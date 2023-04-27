package ar.unrn.infrastructure.data;

import ar.unrn.domain.portsout.GuardarDatos;
import ar.unrn.domain.portsout.InfrastructureExceptions;
import ar.unrn.domain.portsout.Participante;
import ar.unrn.domain.portsout.Propiedades;

public class BaseDeDatosGuardarDatos implements GuardarDatos {

	private Propiedades properties;

	public BaseDeDatosGuardarDatos(BaseDeDatosPropiedades baseDeDatosPropiedades) {
		this.properties = properties;
	}

	@Override
	public void inscribir(Participante participante) throws InfrastructureExceptions {
		// TODO Auto-generated method stub

	}

}
