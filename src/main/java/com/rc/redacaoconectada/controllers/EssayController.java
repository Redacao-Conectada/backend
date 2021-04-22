package com.rc.redacaoconectada.controllers;

import com.rc.redacaoconectada.dtos.EssayDTO;
import com.rc.redacaoconectada.services.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/essays")
public class EssayController {

    @Autowired
    private EssayService service;

    @GetMapping
    @RequestMapping(value = "/listAll")
    public ResponseEntity<List<EssayDTO>> findAll() {

        List<EssayDTO> essaysdto = service.findAll();

        return ResponseEntity.ok().body(essaysdto);
    }

    @GetMapping
    @RequestMapping(value = "/search/{id}")
    public ResponseEntity<EssayDTO> findEssayById(@PathVariable("id") Long id) {

        EssayDTO essaydto = service.findEssayById(id);

        return ResponseEntity.ok().body(essaydto);
    }

    @DeleteMapping
    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteEssayById(@PathVariable("id") Long id) {

        service.deleteEssayById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EssayDTO> insert(@Valid @RequestBody EssayDTO essay) {

        EssayDTO dto = service.insert(essay);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(essay.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping
    @RequestMapping(value = "/upvote/{id}")
    public ResponseEntity<Void> insert(@PathVariable("id") Long id) {

        service.upVoteEssay(id);

        return ResponseEntity.noContent().build();
    }

}
