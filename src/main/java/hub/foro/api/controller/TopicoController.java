package hub.foro.api.controller;

import hub.foro.api.topico.DatosRegistroTopico;
import hub.foro.api.topico.Topico;
import hub.foro.api.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Topico> registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        Topico nuevoTopico = topicoService.registrarTopico(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTopico);
    }
}
