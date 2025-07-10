package hub.foro.api.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findByCursoAndFechaCreacionBetween(
            String curso,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable paginacion
    );

    Page<Topico> findByCurso(String curso, Pageable paginacion);

    Page<Topico> findByFechaCreacionBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable paginacion
    );
}
