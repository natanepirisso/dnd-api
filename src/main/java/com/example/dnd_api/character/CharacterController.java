package com.example.dnd_api.character;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService service;

    @GetMapping
    public List<CharacterResponseDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> getById(@PathVariable Long id) {
        Optional<CharacterResponseDTO> character = service.findById(id);

        if (character.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(character.get());
    }

    @PostMapping
    public ResponseEntity<CharacterResponseDTO> createCharacter(@RequestBody @Valid CharacterRequestDTO dto) {
        CharacterResponseDTO saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> updateCharacter(
            @PathVariable Long id,
            @RequestBody @Valid CharacterRequestDTO dto
    ) {
        Optional<CharacterResponseDTO> updated = service.update(id, dto);

        if (updated.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        if (!service.delete(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}