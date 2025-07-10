package hub.foro.api.topico;

import hub.foro.api.exception.TopicoDuplicadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Topico registrarTopico(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new TopicoDuplicadoException("Ya existe un tópico con el mismo título y mensaje.");
        }
        return topicoRepository.save(new Topico(datos));
    }

    public Page<DatosListadoTopicos> listarTopicosPorCriterio(String curso, Integer anio, Pageable paginacion) {
        if (curso != null && anio != null) {
            LocalDateTime fechaInicio = LocalDateTime.of(anio, Month.JANUARY, 1, 0, 0, 0);
            LocalDateTime fechaFin = LocalDateTime.of(anio, Month.DECEMBER, 31, 23, 59, 59);
            return topicoRepository.findByCursoAndFechaCreacionBetween(curso, fechaInicio, fechaFin, paginacion)
                    .map(DatosListadoTopicos::new);
        } else if (curso != null) {
            return topicoRepository.findByCurso(curso, paginacion)
                    .map(DatosListadoTopicos::new);
        } else if (anio != null) {
            LocalDateTime fechaInicio = LocalDateTime.of(anio, Month.JANUARY, 1, 0, 0, 0);
            LocalDateTime fechaFin = LocalDateTime.of(anio, Month.DECEMBER, 31, 23, 59, 59);
            return topicoRepository.findByFechaCreacionBetween(fechaInicio, fechaFin, paginacion)
                    .map(DatosListadoTopicos::new);
        } else {
            return topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new);
        }
    }
}
