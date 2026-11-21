package com.example.dnd_api.character;

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
    private CharacterRepository repository;
    //INICIANDO CRUD
    //Pra listrar todas as fichas (GET /characters)
    @GetMapping
    public List<CharacterSheet> getAll(){
        return repository.findAll();
    }
    //Pra buscar todas as fichas (GET /characters/1)

    @GetMapping("/{id}")
    public ResponseEntity<CharacterSheet> getById(@PathVariable Long id){
        Optional<CharacterSheet> character = repository.findById(id);

        if(character.isEmpty()){
            return ResponseEntity.notFound().build(); //famoso 404
        }
        return ResponseEntity.ok(character.get()); //200
    }
    //Criar a ficha (POST /characters
    @PostMapping
    public ResponseEntity<CharacterSheet> createCharacter(@RequestBody CharacterSheet character){
        CharacterSheet saved = repository.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); // 201 + ficha salva com o id da ficha
    }
    //Atualizar a ficha existente (PUT /characters/1
    @PutMapping("/{id}")
    public ResponseEntity<CharacterSheet> updateCharacter(
            @PathVariable Long id, @RequestBody CharacterSheet updatedData
    ){
        Optional<CharacterSheet> optionalCharacter = repository.findById(id);
        if (optionalCharacter.isEmpty()){
            return ResponseEntity.notFound().build(); //404
        }
        CharacterSheet character = optionalCharacter.get();
        character.setName(updatedData.getName());
        character.setRace(updatedData.getRace());
        character.setCharacterClass(updatedData.getCharacterClass());
        character.setLevel(updatedData.getLevel());
        character.setMaxHp(updatedData.getMaxHp());
        character.setCurrentHp(updatedData.getCurrentHp());
        character.setStrength(updatedData.getStrength());
        character.setDex(updatedData.getDex());
        character.setCons(updatedData.getCons());
        character.setInte(updatedData.getInte());
        character.setWis(updatedData.getWis());
        character.setCharisma(updatedData.getCharisma());

        CharacterSheet saved = repository.save(character);
        return ResponseEntity.ok(saved);
    }
    //Deletar a ficha (DELETE /characters/1)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter (@PathVariable Long id){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();   //404
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();  //204
    }
}
