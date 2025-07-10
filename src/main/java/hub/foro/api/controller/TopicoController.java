package hub.foro.api.controller;

import hub.foro.api.topico.DatosListadoTopicos;
import hub.foro.api.topico.DatosRegistroTopico;
import hub.foro.api.topico.Topico;
import hub.foro.api.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Topico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        Topico nuevoTopico = topicoService.registrarTopico(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listarTopicos(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {

        Page<DatosListadoTopicos> topicos = topicoService.listarTopicosPorCriterio(curso, anio, paginacion);
        return ResponseEntity.status(HttpStatus.OK).body(topicos);
    }
}
