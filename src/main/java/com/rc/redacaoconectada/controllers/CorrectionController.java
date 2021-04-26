package com.rc.redacaoconectada.controllers;

import com.rc.redacaoconectada.dtos.CorrectionDTO;
import com.rc.redacaoconectada.dtos.CorrectionInsertDTO;
import com.rc.redacaoconectada.services.CorrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/corrections")
public class CorrectionController {

    @Autowired
    private CorrectionService service;

    @GetMapping
    public ResponseEntity<Page<CorrectionDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "essayPerPage", defaultValue = "12") Integer correctionPerPage,
                                                  @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                  @RequestParam(value = "orderBy", defaultValue = "id") String id) {

        PageRequest pageRequest = PageRequest.of(page, correctionPerPage, Sort.Direction.valueOf(direction), id);
        Page<CorrectionDTO> corretionDTO = service.findAll(pageRequest);

        return ResponseEntity.ok().body(corretionDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CorrectionDTO> findCorrectionById(@PathVariable("id") Long id) {

        CorrectionDTO correctionDTO = service.findCorrectionById(id);

        return ResponseEntity.ok().body(correctionDTO);
    }

    @PostMapping
    public ResponseEntity<CorrectionDTO> insert(@Valid @RequestBody CorrectionInsertDTO correctionInsertDTO) {

        CorrectionDTO dto = service.insert(correctionInsertDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
