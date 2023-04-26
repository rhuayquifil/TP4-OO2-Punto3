package ar.unrn.domain.portsout;

import java.time.LocalDate;

public record Concurso(String id, String nombre, LocalDate fechaInicioCompetencia, LocalDate fechaFinCompetencia) {

}
