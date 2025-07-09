package hub.foro.api.topico;

import hub.foro.api.exception.TopicoDuplicadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Topico registrarTopico(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new TopicoDuplicadoException("Ya existe un tópico con el mismo título y mensaje.");
        }
        Topico topico = new Topico(datos);
        return topicoRepository.save(topico);
    }
}
