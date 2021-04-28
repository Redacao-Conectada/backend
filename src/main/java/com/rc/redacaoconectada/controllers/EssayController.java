package com.rc.redacaoconectada.controllers;

import com.rc.redacaoconectada.dtos.EssayDTO;
import com.rc.redacaoconectada.dtos.EssayInsertDTO;
import com.rc.redacaoconectada.services.EssayService;
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
@RequestMapping(value = "/essays")
public class EssayController {

    @Autowired
    private EssayService service;

    @GetMapping
    public ResponseEntity<Page<EssayDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "essayPerPage", defaultValue = "12") Integer essayPerPage,
                                                  @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                  @RequestParam(value = "orderBy", defaultValue = "id") String id) {

        PageRequest pageRequest = PageRequest.of(page, essayPerPage, Sort.Direction.valueOf(direction), id);
        Page<EssayDTO> essaydto = service.findAll(pageRequest);

        return ResponseEntity.ok().body(essaydto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EssayDTO> findEssayById(@PathVariable("id") Long id) {

        EssayDTO essaydto = service.findEssayById(id);

        return ResponseEntity.ok().body(essaydto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEssayById(@PathVariable("id") Long id) {

        service.deleteEssayById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EssayDTO> insert(@Valid @RequestBody EssayInsertDTO essayInsetDTO) {

        EssayDTO dto = service.insert(essayInsetDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}/upvote")
    public ResponseEntity<Void> upVote(@PathVariable("id") Long id) {

        service.upVoteEssay(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/downvote")
    public ResponseEntity<Void> downVote(@PathVariable("id") Long id) {

        service.downVoteEssay(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EssayDTO> update(@PathVariable("id") Long id, @Valid @RequestBody EssayInsertDTO essayInsertDTO) {

        EssayDTO dto = service.update(id, essayInsertDTO);

        return ResponseEntity.ok().body(dto);
    }

}
